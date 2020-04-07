package com.concert.eurekaclient.Ticket;

import com.concert.eurekaclient.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Optional;

@RefreshScope
@Controller
public class TicketController {
    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Value("${test.prop5}")
    private String prop5;

    @Value("${test.prop6}")
    private String prop6;

    @Autowired
    private TicketRepository repository;

    @RequestMapping(path="/config", method = RequestMethod.GET)
    public @ResponseBody HashMap<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("ticket service prop5", prop5);
        configmap.put("ticket service prop6", prop6);
        return configmap;
    }

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody String add (@RequestBody Ticket ticket) throws ParseException {
        repository.save(ticket);
        return "Saved";
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Signature<Iterable<Ticket>> getAll() {
        return new Signature<Iterable<Ticket>>(repository.findAll(), instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Signature<Optional<Ticket>> getById(@PathVariable int id) {
        return new Signature<Optional<Ticket>>(repository.findById(id), instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody String update(@PathVariable Integer id,
                                       @RequestBody Ticket ticket) throws ParseException {
        repository.customUpdate(id, ticket.getEvent(),
                (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(ticket.getDate()),
                ticket.getLocation(), ticket.getZone(),
                ticket.getPlace(), ticket.getPrice());
        return "Updated";
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) {
        Ticket instance  = repository.find(id);
        repository.delete(instance);
        return "Deleted";
    }
}
