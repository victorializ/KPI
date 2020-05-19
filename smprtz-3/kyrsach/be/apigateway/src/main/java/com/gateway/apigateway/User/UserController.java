package com.gateway.apigateway.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserClient client;

    @RequestMapping(path="/register", method = RequestMethod.POST)
    public @ResponseBody String register(@RequestBody User user) throws NoSuchAlgorithmException {
        return client.register(user);
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody User user) {
        return client.login(user);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAll(@RequestHeader(value = "Authorization") String token) {
        client.isAdmin(token);
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody User getById(@PathVariable int id,
                                      @RequestHeader(value = "Authorization") String token) {
        client.isAdmin(token);
        return client.getById(id);
    }

    @RequestMapping(path="/find/{email}", method = RequestMethod.GET)
    public @ResponseBody User getIdByEmail(@PathVariable String email,
                                           @RequestHeader(value = "Authorization") String token) {
        client.isAdmin(token);
        return client.getIdByEmail(email);
    }
}
