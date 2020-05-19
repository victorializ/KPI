package com.gateway.apigateway.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@FeignClient(value="authentication-service")
public interface UserClient {
    @RequestMapping(path="/user/register",  method = RequestMethod.POST)
    public @ResponseBody String register(@RequestBody User user) throws NoSuchAlgorithmException;

    @RequestMapping(path="/user/signin", method = RequestMethod.GET)
    public @ResponseBody String signin(@RequestBody User user);

    @RequestMapping(path="/user", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAll();

    @RequestMapping(path="/user/{id}", method = RequestMethod.GET)
    public @ResponseBody User getById(@PathVariable int id);

    @RequestMapping(path="/user/find/{email}", method = RequestMethod.GET)
    public @ResponseBody User getIdByEmail(@PathVariable String email);
}