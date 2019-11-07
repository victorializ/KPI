package com.tourism.Tourist;

import com.tourism.DataModel;

public class TouristModel implements DataModel {

    private Long id;
    private String name;
    private String email;

    public TouristModel(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public TouristModel () {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return String.format("Tourist: id=%d name=%s email=%s", id, name, email);
    }
}
