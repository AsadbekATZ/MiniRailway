package com.example.MiniRailway.service.seat;

import com.example.MiniRailway.domain.dto.SeatDto;
import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SeatService extends BaseService<SeatDto, SeatEntity> {
    List<SeatEntity> emptySeats(UUID trainId);
}
