package com.gateway.apigateway.Booking;

import com.gateway.apigateway.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    BookingClient client;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking) {
        return client.add(booking);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll() {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    };

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id) {
        return client.getUsersOrders(id);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(path = "/equipment/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getEquipmentOrders(@PathVariable Integer id) {
        return client.getEquipmentOrders(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException {
        return client.delete(id);
    }
}
