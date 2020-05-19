package com.tourism.service;

import javax.persistence.*;

@Entity
public class User  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public void setPassword(String password) {this.password = password; }

    public String getPassword() {return this.password; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("Tourist: id=%d name=%s email=%s role=%s",
                id, name, email, role);
    }
}