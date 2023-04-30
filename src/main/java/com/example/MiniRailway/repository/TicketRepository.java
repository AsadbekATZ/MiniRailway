package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
}
