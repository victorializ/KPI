package com.tourism.database.Order;

import com.tourism.database.DataModel;

public class OrderModel implements DataModel {

    private Long id;
    private Long touristId;
    private Long equipmentId;

    public OrderModel(Long id, Long touristId, Long equipmentId) {
        this.id = id;
        this.touristId = touristId;
        this.equipmentId = equipmentId;
    }

    public OrderModel () { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getTouristId() {
        return touristId;
    }

    public void setTouristId(Long touristId) {
        this.touristId = touristId;
    }

    @Override
    public String toString() {
        return String.format("Order: id=%d touristId=%d equipmentId=%d", id, touristId, equipmentId);
    }
}
