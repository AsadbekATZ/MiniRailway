package com.example.MiniRailway.service.seat;

import com.example.MiniRailway.domain.dto.SeatDto;
import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.exception.AlreadyExistsException;
import com.example.MiniRailway.exception.NotFoundException;
import com.example.MiniRailway.repository.SeatRepository;
import com.example.MiniRailway.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SeatService implements BaseService<SeatDto, SeatEntity> {

    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;
    @Override
    public void save(SeatDto createDto) {
        SeatEntity seat=modelMapper.map(createDto, SeatEntity.class);
        try {
            seatRepository.save(seat);
        } catch (Exception e) {
            throw new AlreadyExistsException("Seat already exists");
        }
    }

    public void setTicketId(UUID seatId, UUID ticketId){
        SeatEntity seat = seatRepository.findById(seatId).orElse(null);
        if (seat != null) {
            seatRepository.setTicketId(seatId, ticketId);
        } else throw new NotFoundException("This seat was not found");
    }

    @Override
    public void delete(UUID id) {
        Optional<SeatEntity> byId = seatRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("This id was not found");
        } else seatRepository.deleteById(id);
    }

    @Override
    public void delete(SeatEntity seatToDelete) {
        if (seatToDelete != null) {
            seatRepository.delete(seatToDelete);
        } else throw new NotFoundException("This seat was not found");
    }

    @Override
    public void update(SeatDto seatDto, UUID id) {
        SeatEntity existingSeat = seatRepository.findById(id).orElse(null);
        if (existingSeat != null && seatDto != null) {
            existingSeat.setTrain(seatDto.getTrain());
            existingSeat.setSeatNumber(seatDto.getSeatNumber());
            existingSeat.setTicket(seatDto.getTicket());
            seatRepository.save(existingSeat);
        } else throw new NotFoundException("This seat was not found");
    }

    @Override
    public SeatEntity getById(UUID id) {
        if (seatRepository.findById(id).isPresent()){
            return seatRepository.findById(id).get();
        } else {
            throw new NotFoundException("Seat not found!");
        }
    }

    @Override
    public List<SeatEntity> getAll() {
        return seatRepository.findAll();
    }

    public List<SeatEntity> getAllByTrainName(String trainName) {
        return seatRepository.findByTrainName(trainName);
    }

    public SeatEntity getByTrainIdAndSeatNumber(UUID trainId, int seatNum) {
        SeatEntity seat = seatRepository.findByTrainIdAndSeatNumber(trainId, seatNum);
        if (seat != null) {
            return seat;
        } else {
            throw new NotFoundException("Seat not found!");
        }
    }
    public List<SeatEntity> emptySeats(UUID trainId) {
        Iterator<SeatEntity> iterator = seatRepository.findByTrainId(trainId).iterator();
        List<SeatEntity> seats = new ArrayList<>();
        while (iterator.hasNext()) {
            SeatEntity emptySeat = iterator.next();
            if (emptySeat.getTicket() == null) {
                seats.add(emptySeat);
            }
        }
        return seats;
    }
    public List<SeatEntity> reservedSeats(UUID trainId) {
        Iterator<SeatEntity> iterator = seatRepository.findByTrainId(trainId).iterator();
        List<SeatEntity> seats = new ArrayList<>();
        while (iterator.hasNext()) {
            SeatEntity reservedSeat = iterator.next();
            if (reservedSeat.getTicket() != null) {
                seats.add(reservedSeat);
            }
        }
        return seats;
    }
}
