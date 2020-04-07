package com.gateway.apigateway.Ticket;

import com.gateway.apigateway.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.Optional;

@Controller
@RequestMapping(path="/ticket")
public class TicketController {
    @Autowired
    TicketClient client;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody String add (@RequestBody Ticket ticket) throws ParseException {
        return client.add(ticket);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Signature<Iterable<Ticket>> getAll() {
        return client.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Signature<Optional<Ticket>> getById(@PathVariable int id) {
        return client.getById(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody String update(@PathVariable Integer id,
                                       @RequestBody Ticket ticket) throws ParseException {
        return client.customUpdate(id, ticket);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) {
        return client.delete(id);
    }
}
