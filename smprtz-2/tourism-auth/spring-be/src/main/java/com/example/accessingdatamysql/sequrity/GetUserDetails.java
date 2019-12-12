package com.example.accessingdatamysql.sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.accessingdatamysql.Tourist.Tourist;
import com.example.accessingdatamysql.Tourist.TouristRepository;

@Service
public class GetUserDetails implements UserDetailsService {

    @Autowired
    private TouristRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Tourist user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + email + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(email)//
                .password(user.getPassword())//
                .authorities(user.getRole())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
