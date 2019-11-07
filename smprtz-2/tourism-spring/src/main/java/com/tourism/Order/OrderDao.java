package com.tourism.Order;

import com.tourism.CrudDao;

import java.util.List;

public interface OrderDao extends CrudDao<OrderModel> {

    String add(OrderModel order);
    void delete(OrderModel order);
    void update(OrderModel order);

    List<OrderModel> getAll();
    OrderModel getById(Long id);
}
