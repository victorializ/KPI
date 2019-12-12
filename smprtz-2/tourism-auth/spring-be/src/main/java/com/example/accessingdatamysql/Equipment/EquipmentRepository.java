package com.example.accessingdatamysql.Equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Query("SELECT u FROM Equipment u WHERE u.name = ?1")
    Equipment findByName(String name);
    @Query("SELECT u FROM Equipment u WHERE u.id = ?1")
    Equipment find(Integer id);
    @Query("Update Equipment SET u.name = ?1, u.price = ?2, u.type = ?3")
    Equipment update(String name, Float price, String type);
}