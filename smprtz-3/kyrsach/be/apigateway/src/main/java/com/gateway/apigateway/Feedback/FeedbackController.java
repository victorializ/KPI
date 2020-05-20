package com.gateway.apigateway.Feedback;

import com.gateway.apigateway.Booking.Booking;
import com.gateway.apigateway.Booking.BookingClient;
import com.gateway.apigateway.CustomException;
import com.gateway.apigateway.Equipment.Equipment;
import com.gateway.apigateway.Equipment.EquipmentClient;
import com.gateway.apigateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/feedback")
public class FeedbackController {
    @Autowired
    FeedbackClient client;

    @Autowired
    EquipmentClient equipmentClient;

    @Autowired
    BookingClient bookingClient;

    @Autowired
    UserClient userClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Feedback add(@RequestBody Feedback feedback,
                                      @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isClient(token);
        Integer equipmentId = bookingClient.getById(feedback.getBookingId()).getEquipmentId();
        Equipment equipment = equipmentClient.getById(equipmentId);
        equipment.setRating((equipment.getRating() + feedback.getRating())/2);
        equipmentClient.update(equipmentId, equipment);
        return client.add(feedback);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getAll() throws CustomException {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Feedback getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    }

    @RequestMapping(path = "/booking/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getByOrderId(@PathVariable Integer id) {
        return client.getByOrderId(id);
    }

    @RequestMapping(path = "/equipment/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getByEquipmentId(@PathVariable Integer id) throws CustomException {
        return client.getAll();
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.delete(id);
    }
}
