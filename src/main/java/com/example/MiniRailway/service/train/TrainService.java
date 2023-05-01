package com.example.MiniRailway.service.train;
import com.example.MiniRailway.domain.dto.TrainDto;
import com.example.MiniRailway.domain.entity.train.DestinationPoint;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.exception.AlreadyExistsException;
import com.example.MiniRailway.exception.NotFoundException;
import com.example.MiniRailway.repository.TrainRepository;
import com.example.MiniRailway.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        TrainEntity train = modelMapper.map(createDto, TrainEntity.class);
        try {
            trainRepository.save(train);
        }catch (Exception e){
            throw new AlreadyExistsException("Same name already exists!");
        }
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

    }

    //
    public void update(String name, Double price, UUID trainId){
        try{
            Optional<TrainEntity> train = trainRepository.findById(trainId);
            train.get().setName(name);
            train.get().setPrice(price);
            trainRepository.save(train.get());
        }catch (Exception e){
            throw new NotFoundException("This train does not exists");
        }
    }

    @Override
    public TrainEntity getById(UUID id) {
        if (trainRepository.findById(id).isPresent()){
            return trainRepository.findById(id).get();
        } else {
            throw new NotFoundException("Train not found!");
        }
    }

    public List<TrainEntity> forwardDestinationTrains(LocalDateTime time,DestinationPoint start, DestinationPoint end){
        List<TrainEntity> forwardDestinationTrains = new ArrayList<>();
        for (TrainEntity train : trainRepository.trainByTime(time)) {
            if (train.getStartPoint().getValue() < start.getValue() && train.getEndPoint().getValue() > end.getValue()){
                forwardDestinationTrains.add(train);
            }
        }
        return forwardDestinationTrains;
    }

    public List<TrainEntity> reverseDestinationTrains(LocalDateTime time,DestinationPoint start, DestinationPoint end){
        List<TrainEntity> reverseDestinationTrains = new ArrayList<>();
        for (TrainEntity train : trainRepository.trainByTime(time)) {
            if (train.getStartPoint().getValue() > start.getValue() && train.getEndPoint().getValue() < end.getValue()){
                reverseDestinationTrains.add(train);
            }
        }
        return reverseDestinationTrains;
    }



    @Override
    public List<TrainEntity> getAll() {
        return trainRepository.findAll();
    }
}
