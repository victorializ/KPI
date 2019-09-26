package com.tourism.database;

import com.tourism.database.Equipment.Equipment;
import com.tourism.database.Equipment.EquipmentModel;
import com.tourism.database.Tourist.Tourist;
import com.tourism.database.Tourist.TouristModel;
import com.tourism.database.Order.OrderModel;
import com.tourism.database.Order.Order;
import com.tourism.database.TourismFacade;

public class Main {
    public static void  main(String[] args) {
        EquipmentModel tent = new EquipmentModel((long) 1, "tent1", (float) 10, "tent");
        EquipmentModel sleepingBag = new EquipmentModel((long) 2, "sleeping bag1", (float) 20, "sleeping bag");
        TouristModel tourist = new TouristModel((long) 1, "vika", "vika@gmail.com");


        TourismFacade tourismFacade = new TourismFacade();
        tourismFacade.initializeDB();
        tourismFacade.addItem(tent);
        tourismFacade.addItem(sleepingBag);
        tourismFacade.addItem(tourist);
        for (EquipmentModel value: tourismFacade.getEquipment().getAll()) {
            System.out.println(value.toString());
        }
        tourismFacade.clearDB();

        /*
        System.out.println("Test equipment");
        Equipment equipment = new Equipment();
        equipment.deleteTable();
        equipment.newTable();
        equipment.add(tent);
        equipment.add(sleepingBag);
        for (EquipmentModel value: equipment.getAll()) {
         System.out.println(value.toString());
        }
        System.out.println(equipment.getById(tent.getId()));
        tent.setName("tent3");
        equipment.update(tent);
        for (EquipmentModel value: equipment.getAll()) {
            System.out.println(value.toString());
        }
        equipment.delete(tent);
        for (EquipmentModel value: equipment.getAll()) {
            System.out.println(value.toString());
        }

        System.out.println("Test tourists");
        Tourist tourists = new Tourist();
        TouristModel anotherTourist = new TouristModel((long)2, "alice", "alice@gmail.com");
        tourists.deleteTable();
        tourists.newTable();
        tourists.add(tourist);
        tourists.add(anotherTourist);
        for(TouristModel value: tourists.getAll()) {
            System.out.println(value.toString());
        }
        tourists.delete(tourist);
        for(TouristModel value: tourists.getAll()) {
            System.out.println(value.toString());
        }
        anotherTourist.setName("stasyan");
        tourists.update(anotherTourist);
        System.out.println(tourists.getById(anotherTourist.getId()));

        System.out.println("Test order");
        Order orders = new Order();
        orders.deleteTable();
        orders.newTable();
        OrderModel order = new OrderModel((long)1, anotherTourist.getId(), sleepingBag.getId());
        orders.add(order);
        System.out.println(orders.getAll());

    */
    }
}
