package com.example.MiniRailway.domain.dto;

import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatDto {
    private TicketEntity ticket;
    private int seatNumber;
    private TrainEntity train;
}
