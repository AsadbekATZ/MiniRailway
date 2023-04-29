package com.example.MiniRailway.service.ticket;

import com.example.MiniRailway.domain.dto.TicketDto;
import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import com.example.MiniRailway.domain.entity.train.DestinationPoint;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.service.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TicketService extends BaseService<TicketDto, TicketEntity> {
    List<TrainEntity> findByDestination(DestinationPoint pontA, DestinationPoint pontB);
    List<TrainEntity> findByTime(LocalDateTime date);
}
