package com.caterpillar.toyrobotapi.service;

import com.caterpillar.toyrobotapi.dto.Position;
import com.caterpillar.toyrobotapi.dto.ToyRobotResponse;

@FunctionalInterface
public interface SubsequentCommandService {
    ToyRobotResponse performCommand(Position position, String subsequentCommand);
}
