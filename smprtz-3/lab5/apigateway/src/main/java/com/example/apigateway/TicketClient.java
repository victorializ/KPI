package com.example.apigateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

@FeignClient(value = "ticket-service")
public interface TicketClient {
    @RequestMapping(path = "/config", method = RequestMethod.GET)
    ResponseEntity<?> GetConfig();

    @RequestMapping(path="/ticket", method = RequestMethod.GET)
    ResponseEntity<?> GetAll();

    @RequestMapping(path="/ticket", method = RequestMethod.POST)
    ResponseEntity<?> Add(@RequestBody Ticket game) throws CustomException;

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.GET)
    ResponseEntity<?> GetById(@PathVariable Integer id);

    @RequestMapping(path="/ticket/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> Update(@PathVariable Integer id,
                                             @RequestBody Ticket ticket) throws ParseException;

    @RequestMapping(path="/ticket/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable Integer id);
}
