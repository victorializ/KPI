package com.gateway.apigateway.Feedback;

import com.gateway.apigateway.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="feedback-service")
public interface FeedbackClient {
    @RequestMapping(path="/feedback", method = RequestMethod.POST)
    public @ResponseBody Feedback add(@RequestBody Feedback feedback);

    @RequestMapping(path="/feedback", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getAll();

    @RequestMapping(path="/feedback/{id}", method = RequestMethod.GET)
    public @ResponseBody Feedback getById(@PathVariable int id) throws CustomException;

    @RequestMapping(path = "/feedback/booking/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getByOrderId(@PathVariable Integer id);

    @RequestMapping(path="/feedback/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException;

    @RequestMapping(path="/feedback/equipment/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteByEquipmentId(@PathVariable Integer id) throws CustomException;
}