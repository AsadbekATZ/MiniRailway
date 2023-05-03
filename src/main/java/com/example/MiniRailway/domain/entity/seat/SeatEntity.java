package com.example.MiniRailway.domain.entity.seat;

import com.example.MiniRailway.domain.entity.BaseEntity;
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

@Entity(name = "seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SeatEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity user;

    private String passengerName;

    @Column(nullable = false)
    private int seatNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private TrainEntity train;
}
