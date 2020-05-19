package com.gateway.apigateway.Booking;

import com.gateway.apigateway.CustomException;
import com.gateway.apigateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    BookingClient client;

    @Autowired
    UserClient userClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking,
                                     @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return client.add(booking);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll(@RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    };

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id,
                                                          @RequestHeader(value = "Authorization") String token) {
        userClient.isClient(token);
        return client.getUsersOrders(id);
    }

    @RequestMapping(path = "/equipment/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getEquipmentOrders(@PathVariable Integer id,
                                                              @RequestHeader(value = "Authorization") String token) {
        userClient.isClient(token);
        return client.getEquipmentOrders(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.delete(id);
    }
}
