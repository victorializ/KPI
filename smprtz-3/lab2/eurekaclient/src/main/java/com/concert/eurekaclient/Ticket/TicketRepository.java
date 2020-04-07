package com.concert.eurekaclient.Ticket;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import org.springframework.data.jpa.repository.Modifying;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    @Query("SELECT u FROM Ticket u WHERE u.id = ?1")
    Ticket find(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Ticket u SET u.event = :event, u.date = :date, u.location = :location, u.zone = :zone, u.place = :place, u.price = :price WHERE u.id = :id")
    Integer customUpdate(@Param("id") Integer id, @Param("event") String event,
                         @Param("date") Date date, @Param("location") String location,
                         @Param("zone") String zone, @Param("place") Integer place,
                         @Param("price") Float price);
}
