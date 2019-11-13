package com.example.accessingdatamysql.Tourist;

import com.example.accessingdatamysql.Tourist.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.Tourist.Tourist;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TouristRepository extends JpaRepository<Tourist, Integer> {
    @Query("SELECT u FROM Tourist  u WHERE u.name = ?1")
    Tourist findByName(String name);
    @Query("SELECT u FROM Tourist  u WHERE u.id = ?1")
    Tourist find(Integer id);
}