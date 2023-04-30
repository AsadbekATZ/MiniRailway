package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.train.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainRepository extends JpaRepository<TrainEntity, UUID> {
}
