package com.tourism.service;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tourism.service.sequrity.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    private String userInfo(User user, String token) {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        json.addProperty("token", token);
        json.add("user", gson.toJsonTree(user));
        return json.toString();
    }

    public String login(User u) {
        String username = u.getEmail();
        String password = u.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user =  userRepository.findByEmail(username);
            String token = jwtTokenProvider.createToken(username, user.getRole());
            return userInfo(user, token);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String register(User user) {
        if (!userExists(user.getEmail(), userRepository.findAll())) {
            if(user.getEmail().equals("admin@mail.com") &&
                    user.getName().equals("admin") &&
                    user.getPassword().equals("admin")) {
                List<Role> role = new ArrayList<Role>() ;
                user.setRole(Role.ROLE_ADMIN);
            } else {
                System.out.println("CLIENT");
                user.setRole(Role.ROLE_CLIENT);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return userInfo(user, jwtTokenProvider.createToken(user.getEmail(), user.getRole()));
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private boolean userExists(
        String email, Iterable<User> users) {

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

}