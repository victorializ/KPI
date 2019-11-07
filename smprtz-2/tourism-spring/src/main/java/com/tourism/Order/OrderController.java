package com.tourism.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.tourism.Equipment.EquipmentModel;
import com.tourism.Equipment.Equipment;
import com.tourism.Tourist.Tourist;
import com.tourism.Tourist.TouristModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RestController
public class OrderController {
    public Order dao;

    public OrderController() {
        this.dao = new Order();
    }

    @RequestMapping(path = "/order", method = RequestMethod.GET)
    public List<OrderModel> GetAll() throws SQLException {
        return this.dao.getAll();
    }

    @RequestMapping(path = "/order/{id}", method = RequestMethod.GET)
    public OrderModel GetById(@PathVariable long id) throws SQLException {
        return this.dao.getById(id);
    }

    @RequestMapping(path="/order", method = RequestMethod.POST)
    String Add(@RequestBody OrderModel order) throws SQLException {
        return this.dao.add(order);
    }
}