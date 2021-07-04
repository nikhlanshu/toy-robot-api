package com.caterpillar.toyrobotapi.service;

import com.caterpillar.toyrobotapi.dto.ToyRobotRequest;

@FunctionalInterface
public interface ToyRobotService {
    String moveToy(ToyRobotRequest toyRobotRequest);
}
