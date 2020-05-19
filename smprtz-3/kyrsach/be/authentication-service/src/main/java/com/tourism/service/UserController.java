package com.tourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @RequestMapping(path="/register",  method = RequestMethod.POST)
    public @ResponseBody String register(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.register(user);
    }

    @RequestMapping(path="/signin", method = RequestMethod.GET)
    public @ResponseBody String signin(@RequestBody User user) {
        return userService.signin(user.getEmail(), user.getPassword());
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody User getById(@PathVariable int id) {
        return repository.find(id);
    }

    @RequestMapping(path="/find/{email}", method = RequestMethod.GET)
    public @ResponseBody User getIdByEmail(@PathVariable String email) {
        return repository.findByEmail(email);
    }
}
