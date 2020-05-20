package com.tourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository repository;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Feedback add(@RequestBody Feedback feedback) {
        repository.save(feedback);
        return feedback;
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Feedback getById(@PathVariable int id) throws CustomException {
        return repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
    }

    @RequestMapping(path = "/booking/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getByOrderId(@PathVariable Integer id) {
        return repository.findOrderFeedback(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException {
        Feedback existing = repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.delete(existing);
        return "Deleted";
    }

    @RequestMapping(path="/equipment/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteByEquipmentId(@PathVariable Integer id) throws CustomException {
        repository.deleteByEquipmentId(id);
        return "Deleted";
    }
}
