package com.example.eurekaclient;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer id;
    private String event;
    private Date date;
    private String location;
    private String zone;
    private Integer place;
    private Float price;
    private boolean is_deleted;

    public Ticket(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(date);
    }

    public void setDate(String date) throws ParseException {
        this.date = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(date);
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public boolean isDeleted() { return is_deleted; }
    public void setDeleted(boolean deleted) { is_deleted = deleted; }

    @Override
    public String toString() {
        return String.format("Ticket event: %s, location: %s, date: %s, zone: %s, place: %d",
                event, location, date, zone, place);
    }
}

