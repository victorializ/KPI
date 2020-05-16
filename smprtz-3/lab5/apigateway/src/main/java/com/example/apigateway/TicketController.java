package com.example.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
public class TicketController {

    @Autowired
    ProxyService _client;

    @Autowired
    ConfigClientAppConfiguration configClientAppConfiguration;

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public ResponseEntity<?> GetConfig() {
        HashMap<String, String> configmap = new HashMap<>();
        configmap.put("gateway prop1", configClientAppConfiguration.getProp1());
        configmap.put("gateway prop2", configClientAppConfiguration.getProp2());
        configmap.put("gateway prop3", configClientAppConfiguration.getProp3());
        configmap.put("gateway prop4", configClientAppConfiguration.getProp4());
        return ResponseEntity.ok(configmap);
    }

    @RequestMapping(path = "/ticket", method = RequestMethod.GET)
    public ResponseEntity<?> GetAll() {
        try {
            return _client.GetAll();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> GetById(@PathVariable Integer id) {
        try {
            return _client.GetById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Ticket wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/ticket", method = RequestMethod.POST)
    public ResponseEntity<?> Add(@RequestBody Ticket ticket) throws CustomException {
        try {
            return _client.Add(ticket);
        } catch (CustomException e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable int id) {
        try {
            return _client.DeleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Ticket wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> Update(@RequestBody Ticket ticket, @PathVariable int id) throws CustomException {
        try {
            return _client.Update(id, ticket);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
    }

}
