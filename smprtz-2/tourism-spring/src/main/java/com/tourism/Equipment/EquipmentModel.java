package com.tourism.Equipment;

import com.tourism.DataModel;

public class EquipmentModel implements DataModel {

    private Long id;
    private String name;
    private Float price;
    private String type;

    public EquipmentModel(Long id, String name, Float price, String  type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public EquipmentModel() { }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return String.format("Equipment: id = %d name = %s type = %s price = %f", id, name, type, price);
    }
}