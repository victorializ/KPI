package com.example.accessingdatamysql.Equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {
    @Query("SELECT u FROM Equipment u WHERE u.name = ?1")
    Equipment findByName(String name);
    @Query("SELECT u FROM Equipment u WHERE u.id = ?1")
    Equipment find(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Equipment u SET u.name = :name, u.price = :price, u.type = :type WHERE id = :id")
    Integer customUpdate(@Param("name") String name,
                           @Param("price") Float price,
                           @Param("type") String  type,
                           @Param("id") Integer id);

}