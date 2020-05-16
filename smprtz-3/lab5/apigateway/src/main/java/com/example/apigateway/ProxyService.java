package com.example.apigateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;


@Component
public class ProxyService {
    private static final String BACKEND_A = "ticket-service";

    @Autowired
    private TicketClient _client;

    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    public ResponseEntity<?> GetAll() {
        return _client.GetAll();
    }

    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    public ResponseEntity<?> GetById(@PathVariable Integer id) {
        return _client.GetById(id);
    }

    @Retry(name = BACKEND_A)
    public ResponseEntity<?> Add(@RequestBody Ticket game) throws CustomException {
        try {
            return _client.Add(game);
        } catch (CustomException e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable int id) {
        try {
            return _client.DeleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Ticket wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    @Retry(name = BACKEND_A)
    ResponseEntity<?> Update(@PathVariable int id, @RequestBody Ticket ticket) {
        try {
            return _client.Update(id, ticket);
        } catch (ParseException e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> fallback(Throwable e) {
        return new ResponseEntity<>(new ArrayList<Ticket>(),HttpStatus.OK);
    }
}
