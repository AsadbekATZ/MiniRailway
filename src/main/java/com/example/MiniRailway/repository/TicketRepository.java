package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
}
