package com.gateway.apigateway.Booking;

import com.gateway.apigateway.CustomException;
import com.gateway.apigateway.Equipment.Equipment;
import com.gateway.apigateway.Equipment.EquipmentClient;
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

    @Autowired
    EquipmentClient equipmentClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking,
                                     @RequestHeader(value = "Authorization") String token) {
        userClient.isClient(token);
        return client.add(booking);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll(@RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        Iterable<Booking> bookings = client.getAll();
        for(Booking b: bookings) {
            b.setEq(equipmentClient.getById(b.getEquipmentId()));
        }
        return bookings;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException {
        Booking booking = client.getById(id);
        booking.setEq(equipmentClient.getById(booking.getEquipmentId()));
        return client.getById(id);
    };

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id,
                                                          @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isClient(token);
        Iterable<Booking> bookings = client.getUsersOrders(id);
        for(Booking b: bookings) {
            b.setEq(equipmentClient.getById(b.getEquipmentId()));
        }
        return bookings;
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
