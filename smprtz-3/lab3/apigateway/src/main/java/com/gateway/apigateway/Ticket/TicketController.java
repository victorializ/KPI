package com.gateway.apigateway.Ticket;

import com.gateway.apigateway.ConfigClientAppConfiguration;
import com.gateway.apigateway.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RefreshScope
@Controller
public class TicketController {
    @Autowired
    TicketClient client;

    @Autowired
    ConfigClientAppConfiguration configClientAppConfiguration;

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("gateway prop1", configClientAppConfiguration.getProp1());
        configmap.put("gateway prop2", configClientAppConfiguration.getProp2());
        configmap.put("gateway prop3", configClientAppConfiguration.getProp3());
        configmap.put("gateway prop4", configClientAppConfiguration.getProp4());
        configmap.putAll(client.getConfig());
        return configmap;
    }

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
