package com.example.MiniRailway.service.user;

import com.example.MiniRailway.domain.dto.UserDto;
import com.example.MiniRailway.domain.entity.user.UserEntity;
import com.example.MiniRailway.exception.WrongCredentialsException;
import com.example.MiniRailway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public void save(UserDto createDto) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(createDto.getUsername());
        if (userEntity.isEmpty()){
            System.out.println("This user is already exists");
        }
        userRepository.save(modelMapper.map(createDto,UserEntity.class));
        System.out.println("Success");
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
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
    public Optional<UserEntity> getById(UUID id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isEmpty()){
            return userEntity;
        }
        return Optional.empty();
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity login(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new WrongCredentialsException("wrong username and/or password");
        });
        if (Objects.equals(userEntity.getPassword(),password)){
            return userEntity;
        }
        throw new WrongCredentialsException("wrong username and/or password");
    }

    @Override
    public void fillBalance(Double amount, UUID userId) {
        userRepository.fillBalance(amount, userId);
    }
}
