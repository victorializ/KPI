package com.gateway.apigateway.sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//
                .antMatchers("/**").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/equipment").permitAll()
                .antMatchers( "/equipment/{id}").permitAll()
                .antMatchers("/equipment/find/{name}").permitAll()
                .antMatchers("/equipment/filter/{type}").permitAll()
                .antMatchers("/booking").permitAll()
                .antMatchers("/booking/{id}").permitAll()
                .antMatchers("/booking/user/{id}").permitAll()
                .antMatchers("/booking/equipment/{id}").permitAll()
                // Disallow everything else..
                .anyRequest().authenticated();

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
        http.cors();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
