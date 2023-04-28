package com.example.MiniRailway.repository;

import com.example.MiniRailway.domain.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
