package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, UUID> {
    Optional<SeatEntity> findBySeatNumber(Integer seatNumber);
    @Modifying
    @Transactional
    @Query("update seats s set s.ticket.id=:ticketId where s.id=:seatId")
    void setTicketId(UUID seatId, UUID ticketId);
    List<SeatEntity> findByTrainName(String trainNumber);
    SeatEntity findByTrainIdAndSeatNumber(UUID trainId, int seatNumber);
    List<SeatEntity> findByTrainId(UUID trainId);
}
