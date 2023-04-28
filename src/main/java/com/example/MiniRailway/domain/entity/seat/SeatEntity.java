package com.example.MiniRailway.domain.entity.seat;

import com.example.MiniRailway.domain.entity.BaseEntity;
import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity(name = "seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SeatEntity extends BaseEntity {
    @Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "seat")
    private TicketEntity ticket;

    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false)
    @Positive()
    private Double price;

    @Column(nullable = false, unique = true)
    @ManyToOne(cascade = CascadeType.ALL)
    private TrainEntity train;
}
