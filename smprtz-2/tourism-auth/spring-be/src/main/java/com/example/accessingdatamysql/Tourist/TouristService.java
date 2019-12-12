package com.example.accessingdatamysql.Tourist;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.accessingdatamysql.CustomException;
import com.example.accessingdatamysql.Tourist.Tourist;
import com.example.accessingdatamysql.Tourist.TouristRepository;
import com.example.accessingdatamysql.sequrity.JwtTokenProvider;

import java.util.List;

@Service
public class TouristService {

    @Autowired
    private TouristRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByEmail(username).getRole());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(Tourist user) {
        //Tourist tourist = userRepository.findByEmail(user.getEmail());
        if (!userExists(user.getEmail(), userRepository.findAll())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private boolean userExists(
            String email, List<Tourist> users) {

        for (Tourist tourist : users) {
            if (tourist.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

}