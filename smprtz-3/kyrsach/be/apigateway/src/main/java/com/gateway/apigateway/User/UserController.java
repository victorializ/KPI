package com.gateway.apigateway.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(path="/signin", method = RequestMethod.GET)
    public @ResponseBody String signin(@RequestBody User user) {
        return client.signin(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAll() {
        return client.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody User getById(@PathVariable int id) {
        return client.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="/find/{email}", method = RequestMethod.GET)
    public @ResponseBody User getIdByEmail(@PathVariable String email) {
        return client.getIdByEmail(email);
    }
}
