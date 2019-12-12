package com.example.accessingdatamysql.Order;

import com.example.accessingdatamysql.Tourist.Tourist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT u FROM Orders  u WHERE u.tourist.id = ?1")
    List<Orders> findUsersOrders(Integer id);

}
