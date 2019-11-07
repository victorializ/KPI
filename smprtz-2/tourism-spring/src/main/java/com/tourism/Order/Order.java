package com.tourism.Order;

import com.tourism.Config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Order implements OrderDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public String add(OrderModel order) {
        String query = "INSERT INTO ORDERS (ID, TOURIST_ID, EQUIPMENT_ID) VALUES("+ order.getId() + "," + order.getTouristId() + "," + order.getEquipmentId() + ")";
        jdbcTemplate.update(query);
        return "Added!";
    }

    public List<OrderModel> getAll() {
        String query = "SELECT ID, TOURIST_ID, EQUIPMENT_ID FROM ORDERS";
        return jdbcTemplate.query(query, new OrderRowMapper());
    }

    public OrderModel getById(Long id) {
        String query = "SELECT * FROM ORDERS WHERE ID=?";
        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new OrderRowMapper());
    }


    public void update(OrderModel order) {
        String query = String.format("UPDATE ORDERS SET TOURIST_ID=%f, EQUIPMENT_ID=%f", order.getTouristId(), order.getEquipmentId());
        this.jdbcTemplate.update(query);
    }

    public void delete(OrderModel order) {
        String query = String.format("DELETE FROM ORDER WHERE ID=%f", order.getId());
        this.jdbcTemplate.update(query);
    }

    public Order() {
        jdbcTemplate = new AppConfig().jdbcTemplate();
    }

}
