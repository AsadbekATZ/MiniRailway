package com.example.MiniRailway.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface BaseService<CD, E> {
    void save(CD createDto);
    void delete(UUID id);
    void update(CD createDto, UUID id);
    Optional<E> getById(UUID id);
    List<E> getAll();
}