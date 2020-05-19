package com.tourism.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    @Query("SELECT u FROM Booking u WHERE u.touristId = ?1")
    List<Booking> findUsersBookings(Integer id);

    @Query("SELECT u FROM Booking u WHERE u.equipmentId = ?1")
    List<Booking> findEquipmentBookings(Integer id);
}