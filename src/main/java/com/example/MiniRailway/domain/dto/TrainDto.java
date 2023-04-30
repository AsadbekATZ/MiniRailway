package com.example.MiniRailway.domain.dto;

import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.domain.entity.train.DestinationPoint;
import com.example.MiniRailway.domain.entity.train.TrainClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainDto {
    private String name;
    private List<SeatEntity> seats;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private DestinationPoint startPoint;
    private DestinationPoint endPoint;
    private TrainClass trainClass;
}
