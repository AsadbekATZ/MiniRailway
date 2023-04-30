package com.example.MiniRailway.domain.entity.ticket;

import com.example.MiniRailway.domain.entity.BaseEntity;
import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.domain.entity.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity(name = "tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TicketEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @OneToOne(mappedBy = "ticket")
    private SeatEntity seat;
}
