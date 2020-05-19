package com.gateway.apigateway.Equipment;

import com.gateway.apigateway.CustomException;
import com.gateway.apigateway.Feedback.Feedback;
import com.gateway.apigateway.Feedback.FeedbackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/equipment")
public class EquipmentController {
    @Autowired
    EquipmentClient client;

    @Autowired
    FeedbackClient feedbackClient;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Equipment add(@RequestBody Equipment equipment) {
        return client.add(equipment);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Equipment> getAll() {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Equipment getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    }

    @RequestMapping(path="/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Equipment getIdByName(@PathVariable String name) {
        return client.getIdByName(name);
    }

    @RequestMapping(path="/filter/{type}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Equipment> getByFilter(@PathVariable String type) {
        return client.getByFilter(type);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody Equipment update(@PathVariable Integer id,
                                          @RequestBody Equipment equipment) throws CustomException {
        return client.update(id, equipment);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException {
        return client.delete(id);
    }
}
