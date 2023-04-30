package com.example.MiniRailway.service.seat;

import com.example.MiniRailway.domain.dto.SeatDto;
import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService{

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
        Optional<SeatEntity> seatEntity = seatRepository.findById(id);
        if (seatEntity.isEmpty()){
            System.out.println("Don`t found");
        }
        seatRepository.save(modelMapper.map(createDto,SeatEntity.class));
    }

    @Override
    public Optional<SeatEntity> getById(UUID id) {
        Optional<SeatEntity> seatEntity = seatRepository.findById(id);
        if (seatEntity.isPresent()){
            return seatEntity;
        }
        return Optional.empty();
    }

    @Override
    public List<SeatEntity> getAll() {
        return seatRepository.findAll();
    }

    @Override
    public List<SeatEntity> emptySeats(UUID trainId) {
        return null;
    }
}
