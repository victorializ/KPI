package com.tourism.service;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer touristId;
    private Integer equipmentId;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) throws ParseException {
        this.date = date;
    }

    public Integer getTouristId() {
        return touristId;
    }

    public void setTouristId(Integer tourist) {
        this.touristId = tourist;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public String toString() {
        return String.format("Order: id=%d touristId=%d equipmentId=%d",
                id, touristId, equipmentId);
    }

}