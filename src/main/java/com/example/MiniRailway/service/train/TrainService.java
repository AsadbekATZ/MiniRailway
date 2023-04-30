package com.example.MiniRailway.service.train;

import com.example.MiniRailway.domain.dto.TrainDto;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.exception.NotFoundException;
import com.example.MiniRailway.repository.TrainRepository;
import com.example.MiniRailway.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainService implements BaseService<TrainDto, TrainEntity> {

    private final TrainRepository trainRepository;

    private final ModelMapper modelMapper;

    @Override
    public void save(TrainDto createDto) {
        trainRepository.save(modelMapper.map(createDto, TrainEntity.class));
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void delete(TrainEntity trainEntity) {
        trainRepository.delete(trainEntity);
    }

    @Override
    public void update(TrainDto createDto, UUID id) {
        Optional<TrainEntity> trainEntity = trainRepository.findById(id);
        if (trainEntity.isEmpty()){
            System.out.println("Don`t found");
        }
        trainRepository.save(modelMapper.map(createDto, TrainEntity.class));       //trainEntity=modelMapper.map(createDto,TrainEntity.class);
                                                                                   //trainRepository.save(trainEntity);
    }

    @Override
    public TrainEntity getById(UUID id) {
        if (trainRepository.findById(id).isPresent()){
            return trainRepository.findById(id).get();
        } else {
            throw new NotFoundException("Train not found!");
        }
    }

    @Override
    public List<TrainEntity> getAll() {
        return trainRepository.findAll();
    }
}
