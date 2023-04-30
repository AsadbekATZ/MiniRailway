package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import com.example.MiniRailway.domain.entity.train.DestinationPoint;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {

    @Query("select t from tickets t where t.seat.train.startPoint=:pontA and t.seat.train.endPoint=:pontB")
    List<TrainEntity> findByDestination(DestinationPoint pontA, DestinationPoint pontB);

    @Query("select t from tickets t where t.seat.train.departure=:date")
    List<TrainEntity> findByTime(LocalDateTime date);

}
