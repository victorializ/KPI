package com.gateway.apigateway.Ticket;

import com.gateway.apigateway.GetResponse;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

@FeignClient(value = "ticket-service")
public interface TicketClient {

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getConfig();

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public @ResponseBody
    GetResponse<Iterable<Ticket>> getAll();

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    GetResponse<Optional<Ticket>> getById(@PathVariable int id);

    @RequestMapping(path = "/find/{event}", method = RequestMethod.GET)
    public @ResponseBody
    GetResponse<Ticket> getByEvent(@PathVariable String event);

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public @ResponseBody
    String add(@RequestParam String event,
               @RequestParam String date,
               @RequestParam String location,
               @RequestParam String zone,
               @RequestParam Integer place,
               @RequestParam Float price) throws ParseException;

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    void delete(@PathVariable Integer id);

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@RequestParam String event,
                  @RequestParam String date,
                  @RequestParam String location,
                  @RequestParam String zone,
                  @RequestParam Integer place,
                  @RequestParam Float price) throws ParseException;
}