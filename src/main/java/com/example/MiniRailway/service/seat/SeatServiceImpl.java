package com.example.MiniRailway.service.seat;

import com.example.MiniRailway.domain.dto.SeatDto;
import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.exception.NotFoundException;
import com.example.MiniRailway.repository.SeatRepository;
import com.example.MiniRailway.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements BaseService<SeatDto, SeatEntity> {

    private final SeatRepository seatRepository;

    private final ModelMapper modelMapper;

    @Override
    public void save(SeatDto createDto) {
        seatRepository.save(modelMapper.map(createDto,SeatEntity.class));
    }

    @Override
    public void delete(UUID id) {
    }

    @Override
    public void delete(SeatEntity seatEntity) {
       seatRepository.delete(seatEntity);
    }

    @Override
    public void update(SeatDto createDto, UUID id) {
    }

    @Override
    public SeatEntity getById(UUID id) {
        if (seatRepository.findById(id).isPresent()){
            return seatRepository.findById(id).get();
        } else {
            throw new NotFoundException("Seat not found");
        }

    }

    @Override
    public List<SeatEntity> getAll() {
        return seatRepository.findAll();
    }

    public List<SeatEntity> emptySeats(UUID trainId) {
        return null;
    }

    public void updateSeatPrice(Double price, UUID seatId) {
        try {
            Optional<SeatEntity> seat = seatRepository.findById(seatId);
            seat.get().setPrice(price);
            seatRepository.save(seat.get());
        } catch (Exception e){
            throw new NotFoundException("This seat does not exists!");
        }
    }
}
