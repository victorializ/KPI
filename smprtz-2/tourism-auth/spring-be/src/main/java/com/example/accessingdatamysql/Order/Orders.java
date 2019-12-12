package com.example.accessingdatamysql.Order;

import com.example.accessingdatamysql.Equipment.Equipment;
import com.example.accessingdatamysql.Tourist.Tourist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tourist")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tourist tourist;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Equipment equipment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Order: id=%d tourist=%s equipment=%s", id, tourist.toString(), equipment.toString());
    }
}
