package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeatRepository extends JpaRepository<SeatEntity, UUID> {
}
