package com.example.MiniRailway.domain.dto;

import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.domain.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDto {
    private UserEntity user;
    private String passenger;
    private SeatEntity seat;
}
