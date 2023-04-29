package com.example.MiniRailway.service.user;

import com.example.MiniRailway.domain.dto.UserDto;
import com.example.MiniRailway.domain.entity.user.UserEntity;
import com.example.MiniRailway.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService extends BaseService<UserDto, UserEntity> {
    UserEntity login(String username, String password);
    void fillBalance(Double amount, UUID userId);
    void delete(UserDto userDto);
}
