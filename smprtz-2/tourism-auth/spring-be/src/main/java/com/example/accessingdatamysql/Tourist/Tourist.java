package com.example.accessingdatamysql.Tourist;

import javax.persistence.*;
import java.util.List;


@Entity // This tells Hibernate to make a table out of this class
public class Tourist  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;

    //@ElementCollection(fetch = FetchType.EAGER)
    Role role;

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
        return String.format("Tourist: id=%d name=%s email=%s", id, name, email, role);
    }
}