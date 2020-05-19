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
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(date);
    }

    public void setDate(String date) throws ParseException {
        this.date = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(date);
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