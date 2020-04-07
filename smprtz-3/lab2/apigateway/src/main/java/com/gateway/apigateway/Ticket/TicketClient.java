package com.gateway.apigateway.Ticket;

import com.gateway.apigateway.Signature;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@FeignClient(value = "ticket-service")
public interface TicketClient {
    @RequestMapping(path="/ticket", method = RequestMethod.POST)
    public @ResponseBody String add (@RequestBody Ticket ticket) throws ParseException;

    @RequestMapping(path="/ticket", method = RequestMethod.GET)
    public @ResponseBody Signature<Iterable<Ticket>> getAll();

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.GET)
    public @ResponseBody Signature<Optional<Ticket>> getById(@PathVariable int id);

    @RequestMapping(path="/ticket/{id}", method = RequestMethod.PUT)
    public @ResponseBody String customUpdate(@PathVariable Integer id,
                                       @RequestBody Ticket ticket) throws ParseException;

    @RequestMapping(path="/ticket/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String  delete(@PathVariable Integer id);
}