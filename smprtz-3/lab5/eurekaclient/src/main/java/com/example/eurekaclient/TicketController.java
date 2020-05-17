package com.example.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RefreshScope
public class TicketController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Value("${test.prop5:}")
    private String prop5;

    @Value("${test.prop6:}")
    private String prop6;

    @Autowired
    TicketService service;

    @Autowired
    KafkaProducerDemo kafkaProducer;

    @RequestMapping(path="/config", method = RequestMethod.GET)
    public @ResponseBody HashMap<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("ticket service prop5", prop5);
        configmap.put("ticket service prop6", prop6);
        return configmap;
    }

    @RequestMapping(path = "/ticket", method = RequestMethod.GET)
    ResponseEntity<?> GetAll() {
        try {
            return new ResponseEntity<>(service.GetAll(instanceId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.GET)
    ResponseEntity<?> GetById(@PathVariable Integer id) {
        try {
            ResponseModel<Ticket> res = service.GetById(id, instanceId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<String>(e.mes, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/ticket", method = RequestMethod.POST)
    ResponseEntity<?> Add(@RequestBody Ticket ticket) {
        try {
            kafkaProducer.sendMessage("create.entity", ticket);
            return new ResponseEntity<>(service.Add(ticket), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.mes, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect data types", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable int id) {
        try {
            service.DeleteById(id);
            return new ResponseEntity<>("Item was deleted", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.mes, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> Update(@PathVariable Integer id, @RequestBody Ticket ticket) {
        try {
            kafkaProducer.sendMessage("update.entity", ticket);
            Ticket res = service.Update(id, ticket);
            res.setId(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.mes, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect data types", HttpStatus.BAD_REQUEST);
        }
    }

}
