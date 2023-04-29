package com.example.MiniRailway.service.ticket;

import com.example.MiniRailway.domain.dto.TicketDto;
import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import com.example.MiniRailway.domain.entity.train.DestinationPoint;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final ModelMapper modelMapper;

    @Override
    public void save(TicketDto createDto) {
        ticketRepository.save(modelMapper.map(createDto, TicketEntity.class));
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void delete(TicketEntity ticketEntity) {
        ticketRepository.delete(ticketEntity);
    }

    @Override
    public void update(TicketDto createDto, UUID id) {
        Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);
        if (ticketEntity.isEmpty()){
            System.out.println("Don`t found");
        }
        ticketRepository.save(modelMapper.map(createDto,TicketEntity.class));
    }

    @Override
    public Optional<TicketEntity> getById(UUID id) {
        Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);
        if (!ticketEntity.isEmpty()){
            return ticketEntity;
        }
        return Optional.empty();
    }

    @Override
    public List<TicketEntity> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<TrainEntity> findByDestination(DestinationPoint pontA, DestinationPoint pontB) {
        return ticketRepository.findByDestination(pontA, pontB);
    }

    @Override
    public List<TrainEntity> findByTime(LocalDateTime date) {
        return ticketRepository.findByTime(date);
    }
}