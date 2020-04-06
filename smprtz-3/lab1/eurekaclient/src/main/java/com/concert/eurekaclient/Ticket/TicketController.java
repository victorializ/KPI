package com.concert.eurekaclient.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
@RequestMapping(path="/ticket")
public class TicketController {

    @Autowired
    private TicketRepository repository;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody String add (@RequestBody Ticket ticket) throws ParseException {
        ticket.setPurchaseDate();
        repository.save(ticket);
        return "Saved";
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Ticket> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Ticket> getById(@PathVariable int id) {
        return repository.findById(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody String update(@PathVariable Integer id,
                                       @RequestBody Ticket ticket) throws ParseException {
        repository.customUpdate(id, ticket.getEvent(),
                (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(ticket.getDate()),
                ticket.getLocation(), ticket.getZone(),
                ticket.getPlace(), ticket.getPrice());
        return "Updated";
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable Integer id) {
        Ticket instance  = repository.find(id);
        repository.delete(instance);
    }

    @RequestMapping(path="/find/{event}", method = RequestMethod.GET)
    public @ResponseBody Ticket getByEvent(@PathVariable String event) {
        return repository.findByEvent(event);
    }
}
