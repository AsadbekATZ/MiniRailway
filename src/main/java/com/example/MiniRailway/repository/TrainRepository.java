package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.train.DestinationPoint;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, UUID> {
    @Query("delete from trains t where t.id=:id")
    @Modifying
    @Transactional
    void deleteByTrainId(UUID id);
}
