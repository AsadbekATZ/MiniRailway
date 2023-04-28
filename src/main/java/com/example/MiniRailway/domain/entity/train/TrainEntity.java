package com.example.MiniRailway.domain.entity.train;

import com.example.MiniRailway.domain.entity.BaseEntity;
import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "trains")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TrainEntity extends BaseEntity {
    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "train")
    private List<SeatEntity> seats;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime departure;

    @Column(nullable = false)
    private LocalDateTime arrival;

    @Enumerated(EnumType.STRING)
    private DestinationPoint startPoint;

    @Enumerated(EnumType.STRING)
    private DestinationPoint endPoint;
}
