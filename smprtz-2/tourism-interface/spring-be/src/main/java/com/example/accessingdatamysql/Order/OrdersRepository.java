package com.example.accessingdatamysql.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

}
