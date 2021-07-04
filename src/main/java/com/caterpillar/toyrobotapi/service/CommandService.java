package com.caterpillar.toyrobotapi.service;

import com.caterpillar.toyrobotapi.dto.ToyRobotResponse;

@FunctionalInterface
public interface CommandService {
    ToyRobotResponse performCommand(String command);
}
