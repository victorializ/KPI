package com.tourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping(path="/booking")
public class BookingController {

    @Autowired
    private BookingRepository repository;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking) throws ParseException {
        Booking b = new Booking();
        b.setDate(booking.getDate());
        b.setEquipmentId(booking.getEquipmentId());
        b.setTouristId(booking.getTouristId());
        return repository.save(b);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException {
        return repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id) {
        return repository.findUsersBookings(id);
    }

    @RequestMapping(path = "/equipment/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getEquipmentOrders(@PathVariable Integer id) {
        return repository.findEquipmentBookings(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException {
        Booking existing = repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.delete(existing);
        return "Deleted";
    }

    @RequestMapping(path="/equipment/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteByEquipmentId(@PathVariable Integer id) throws CustomException {
        repository.deleteByEquipmentId(id);
        return "Deleted";
    }
}
