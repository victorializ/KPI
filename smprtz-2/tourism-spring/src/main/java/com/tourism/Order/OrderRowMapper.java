package com.tourism.Order;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderModel> {
    public OrderModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderModel order = new OrderModel();
        order.setId(resultSet.getLong("ID"));
        order.setTouristId(resultSet.getLong("TOURIST_ID"));
        order.setEquipmentId(resultSet.getLong("EQUIPMENT_ID"));
        return order;
    }
}
