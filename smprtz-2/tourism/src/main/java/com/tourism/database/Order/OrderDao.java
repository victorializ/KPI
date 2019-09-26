package com.tourism.database.Order;

import com.tourism.database.CrudDao;
import java.util.List;

public interface OrderDao extends CrudDao<OrderModel> {
    void newTable();
    void deleteTable();

    void add(OrderModel order);
    void delete(OrderModel order);
    void update(OrderModel order);

    List<OrderModel> getAll();
    OrderModel getById(Long id);
}
