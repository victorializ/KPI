package com.gateway.apigateway.Ticket;

import com.gateway.apigateway.ConfigClientAppConfiguration;
import com.gateway.apigateway.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RefreshScope
@Controller
public class TicketController {

    @Autowired
    TicketClient ticketClient;

    @Autowired
    ConfigClientAppConfiguration configClientAppConfiguration;

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("firstprop", configClientAppConfiguration.getFirstprop());
        configmap.put("secondprop", configClientAppConfiguration.getSecondprop());
        configmap.put("thirdprop", configClientAppConfiguration.getThirdprop());
        configmap.put("fourhprop", configClientAppConfiguration.getFourthprop());
        configmap.putAll(ticketClient.getConfig());
        return configmap;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public @ResponseBody
    GetResponse<Iterable<Ticket>> getAll() {
        return ticketClient.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody GetResponse<Optional<Ticket>> getById(@PathVariable int id) {
        return ticketClient.getById(id);
    }

    @RequestMapping(path = "/find/{event}", method = RequestMethod.GET)
    public @ResponseBody GetResponse<Ticket> getByEvent(@PathVariable String event) {
        return ticketClient.getByEvent(event);
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public @ResponseBody String add(@RequestParam String event,
                                   @RequestParam String date,
                                   @RequestParam String location,
                                   @RequestParam String zone,
                                   @RequestParam Integer place,
                                   @RequestParam Float price) throws ParseException {
        return ticketClient.add(event, date, location, zone,place, price);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public @ResponseBody void delete(@PathVariable Integer id) {
        ticketClient.delete(id);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public @ResponseBody String update(@RequestParam String event,
                                      @RequestParam String date,
                                      @RequestParam String location,
                                      @RequestParam String zone,
                                      @RequestParam Integer place,
                                      @RequestParam Float price) throws ParseException {
        return ticketClient.update(event, date, location, zone, place, price);
    }
}
