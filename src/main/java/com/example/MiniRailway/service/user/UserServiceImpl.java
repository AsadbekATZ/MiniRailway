package com.example.MiniRailway.service.user;

import com.example.MiniRailway.domain.dto.UserDto;
import com.example.MiniRailway.domain.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Override
    public void save(UserDto createDto) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(UserDto createDto, UUID id) {

    }

    @Override
    public UserEntity getById(UUID id) {
        return null;
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    public UserEntity login(String username, String password) {
        return null;
    }

    @Override
    public void fillBalance(Double amount, UUID userId) {

    }
}
