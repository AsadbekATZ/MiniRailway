package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, UUID> {

    Optional<SeatEntity> findBySeatNumber(Integer seatNumber);

    List<SeatEntity> findByTrainName(String trainNumber);
    SeatEntity findByTrainIdAndSeatNumber(UUID trainId, int seatNumber);
    List<SeatEntity> findByTrainId(UUID trainId);
}
