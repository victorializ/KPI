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

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Orders> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Orders> getById(@PathVariable int id) {
        // This returns a JSON or XML with the users
        return repository.findById(id);
    }

    @RequestMapping(path="/new", method = RequestMethod.POST)
    String add(@RequestParam String touristId, @RequestParam Integer equipmentId) {
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
/*
package com.example.accessingdatamysql.Order;

import com.example.accessingdatamysql.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping(path="/order")
public class OrderController {
    @Autowired
    private OrderRepository repository;

    @RequestMapping(path="/list", method = RequestMethod.GET)
    public @ResponseBody Iterable<Order> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Order> getById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @RequestMapping(path="/new", method = RequestMethod.POST)
    public @ResponseBody String add
            (@RequestParam int tourist, @RequestParam int equipment) {
        Order order = new Order();
        order.setTourist(tourist);
        order.setEquipment(equipment);
        return order.toString();
    }


}
*/