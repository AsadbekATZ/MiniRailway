package com.example.MiniRailway.service.train;

import com.example.MiniRailway.domain.dto.TrainDto;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public interface TrainService extends BaseService<TrainDto, TrainEntity> {

}
