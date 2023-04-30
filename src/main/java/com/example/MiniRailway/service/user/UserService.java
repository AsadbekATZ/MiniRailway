package com.example.MiniRailway.service.user;

import com.example.MiniRailway.domain.dto.UserDto;
import com.example.MiniRailway.domain.entity.user.UserEntity;
import com.example.MiniRailway.exception.NotFoundException;
import com.example.MiniRailway.exception.AlreadyExistsException;
import com.example.MiniRailway.repository.UserRepository;
import com.example.MiniRailway.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserDto, UserEntity> {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public void save(UserDto createDto) {
        UserEntity user = modelMapper.map(createDto, UserEntity.class);
        try {
            userRepository.save(user);
        } catch (Exception e){
            throw new AlreadyExistsException("Same username already exists!");
        }
    }

    @Override
    public void delete(UUID id) {

    }

    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    @Override
    public void update(UserDto createDto, UUID id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            System.out.println("Don`t found");
        }
        userRepository.save(modelMapper.map(createDto, UserEntity.class));
    }

    @Override
    public UserEntity getById(UUID id) {
        if (userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        } else {
            throw new NotFoundException("User Not Found!");
        }
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
    public UserEntity login(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new NotFoundException("Wrong username and/or password");
        });
        if (Objects.equals(userEntity.getPassword(),password)){
            return userEntity;
        }
        throw new NotFoundException("Wrong username and/or password");
    }
    public void fillBalance(Double amount, UUID userId) {
        userRepository.fillBalance(amount, userId);
    }
}
