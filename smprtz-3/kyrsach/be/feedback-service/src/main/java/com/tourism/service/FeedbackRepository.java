package com.tourism.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
    @Query("SELECT u FROM Feedback u WHERE u.bookingId = ?1")
    List<Feedback> findOrderFeedback(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Feedback u WHERE u.equipmentId = ?1")
    void deleteByEquipmentId(Integer id);
}