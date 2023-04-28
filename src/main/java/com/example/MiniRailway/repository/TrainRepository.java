package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.train.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, UUID> {
}
