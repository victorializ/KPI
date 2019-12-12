package com.example.accessingdatamysql.Order;

import com.example.accessingdatamysql.Equipment.Equipment;
import com.example.accessingdatamysql.Equipment.EquipmentRepository;
import com.example.accessingdatamysql.Tourist.Tourist;
import com.example.accessingdatamysql.Tourist.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

@Controller
@RequestMapping(path="/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdersController {
    @Autowired
    private OrdersRepository repository;
    @Autowired
    private TouristRepository touristRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/list/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Orders> getUsersOrders(@PathVariable Integer id) {
        return repository.findUsersOrders(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Orders> getById(@PathVariable int id) {
        // This returns a JSON or XML with the users
        return repository.findById(id);
    }

    @RequestMapping(path="/new", method = RequestMethod.POST)
    public @ResponseBody String add(@RequestParam String touristId, @RequestParam Integer equipmentId) {
        Tourist tourist = touristRepository.find(Integer.parseInt(touristId));
        Equipment equipment = equipmentRepository.find(equipmentId);
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Orders order = new Orders();
        order.setTourist(tourist);
        order.setEquipment(equipment);
        order.setDate(date);
        repository.save(order);
        return "Saved!\n";
    }

}