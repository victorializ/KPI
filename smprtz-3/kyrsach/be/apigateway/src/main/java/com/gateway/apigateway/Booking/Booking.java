package com.gateway.apigateway.Booking;

import com.gateway.apigateway.Equipment.Equipment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    private Integer id;
    private Integer touristId;
    private Integer equipmentId;
    private Date date;
    private Equipment eq;

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

    public Equipment getEq() { return eq; }

    public void setEq(Equipment eq) { this.eq = eq; }
}