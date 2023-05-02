package com.example.MiniRailway.service.ticket;
import com.example.MiniRailway.domain.dto.TicketDto;
import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import com.example.MiniRailway.exception.AlreadyExistsException;
import com.example.MiniRailway.exception.NotFoundException;
import com.example.MiniRailway.repository.TicketRepository;
import com.example.MiniRailway.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService implements BaseService<TicketDto, TicketEntity> {

    private final TicketRepository ticketRepository;

    private final ModelMapper modelMapper;

    @Override
    public void save(TicketDto createDto) {
        TicketEntity ticket=modelMapper.map(createDto, TicketEntity.class);
        try {
            ticketRepository.save(ticket);
        } catch (Exception e) {
            throw new AlreadyExistsException("Ticket already exists");
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<TicketEntity> byId = ticketRepository.findById(id);
        if(byId.isEmpty()){
            throw new NotFoundException("This Id was not found");
        }
        ticketRepository.deleteById(id);
    }

    @Override
    public void delete(TicketEntity ticketEntity) {
        ticketRepository.delete(ticketEntity);
    }

    @Override
    public void update(TicketDto createDto, UUID id) {
        TicketEntity  ticket=ticketRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("no ticket this id");
        });
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(createDto, ticket);
        ticketRepository.save(ticket);
    }

    @Override
    public TicketEntity getById(UUID id) {
        if (ticketRepository.findById(id).isPresent()){
            return ticketRepository.findById(id).get();
        } else {
            throw new NotFoundException("Ticket not found!");
        }
    }

    @Override
    public List<TicketEntity> getAll() {
        return ticketRepository.findAll();
    }


//    public List<TrainEntity> findByDestination(DestinationPoint pontA, DestinationPoint pontB) {
//        return ticketRepository.findByDestination(pontA, pontB);
//    }
//
//
//    public List<TrainEntity> findByTime(LocalDateTime date) {
//        return ticketRepository.findByTime(date);
//    }
}
