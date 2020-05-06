package com.example.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    public Ticket Add(Ticket ticket) throws CustomException {
        SanityChecks(ticket);
        return repository.save(ticket);
    }

    public void DeleteById(Integer id) throws CustomException {
        Ticket existing = repository.findById(id).orElseThrow(() -> new CustomException("Ticket wasn't found"));
        if (existing.isDeleted()) throw new CustomException("Ticket was deleted");
        existing.setDeleted(true);
        repository.save(existing);
    }

    public Ticket Update(Integer id, Ticket item) throws CustomException, ParseException {
        Ticket ticket = repository.findById(item.getId()).orElseThrow(() -> new CustomException("Ticket wasn't found"));
        if (ticket.isDeleted()) throw new CustomException("Ticket wasn't found");
        SanityChecks(item);

        repository.customUpdate(id, ticket.getEvent(),
                (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(ticket.getDate()),
                ticket.getLocation(), ticket.getZone(),
                ticket.getPlace(), ticket.getPrice());
        return ticket;
    }

    private void SanityChecks(Ticket item) throws CustomException {
        if (item.getEvent() == null || item.getLocation() == null || item.getPlace() == null ||
                item.getDate() == null || item.getZone()== null || item.getPrice() == 0.0)
            throw new CustomException("Incorrect data");
    }

    public ResponseModel<List<Ticket>> GetAll(String id) {
        List<Ticket> list = new ArrayList<>();
        repository.findAll().forEach(list::add);

        List<Ticket> filtered = new ArrayList<>();
        for (Ticket item : list) {
            if (!item.isDeleted()) {
                filtered.add(item);
            }
        }
        return new ResponseModel<>(filtered, id);
    }

    public ResponseModel<Ticket> GetById(Integer id, String str) throws CustomException {
        Ticket res = repository.findById(id).orElseThrow(() -> new CustomException("Ticket wasn't found"));
        if (res == null | res.isDeleted()) throw new CustomException("Ticket was deleted");
        return new ResponseModel<>(res, str);
    }
}