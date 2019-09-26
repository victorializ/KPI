package com.tourism.database;

import com.tourism.database.Equipment.Equipment;
import com.tourism.database.Tourist.Tourist;
import com.tourism.database.Order.Order;
import com.tourism.database.Tourist.TouristModel;
import com.tourism.database.Order.OrderModel;
import com.tourism.database.Equipment.EquipmentModel;

public class TourismFacade {

    private Equipment equipment;
    private Tourist tourist;
    private Order order;

    public Equipment getEquipment() {
        return equipment;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public Order getOrder() {
        return order;
    }

    public TourismFacade() {
        this.equipment = new Equipment();
        this.tourist = new Tourist();
        this.order = new Order();
    }

    public void clearDB() {
        this.equipment.deleteTable();
        this.order.deleteTable();
        this.tourist.deleteTable();
    }

    public void initializeDB() {
        this.tourist.newTable();
        this.order.newTable();
        this.equipment.newTable();
    }

    public void addItem(DataModel item) {
        if(item instanceof TouristModel) {
            this.tourist.add((TouristModel) item);
        } else if(item instanceof EquipmentModel) {
            this.equipment.add((EquipmentModel) item);
        } else {
            this.order.add((OrderModel) item);
        }
        System.out.println(item);
    }


}
