package com.caterpillar.toyrobotapi.service;

import com.caterpillar.toyrobotapi.dto.Direction;
import com.caterpillar.toyrobotapi.dto.Position;
import com.caterpillar.toyrobotapi.dto.ToyRobotResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsequentCommandServiceImpl implements SubsequentCommandService{

    public ToyRobotResponse performCommand(Position initialPosition, String moveCommand) {
        List<String> commands = CommandPatternMatchingUtil.tokenize(moveCommand, "( +)", token -> !"".equals(token.trim()));
        ToyRobotResponse toyRobotResponse = new ToyRobotResponse(new Position(initialPosition.getxCoOrdinate(),
                initialPosition.getyCoOrdinate(), initialPosition.getFace()));
        commands.forEach(command -> moveTheToy(command, toyRobotResponse, initialPosition));
        return toyRobotResponse;
    }

    private void moveTheToy(String command, ToyRobotResponse toyRobotResponse, Position initialPosition) {
        switch (command) {
            case "MOVE":
                move(toyRobotResponse, initialPosition);
                break;
            case "LEFT":
                rotate(toyRobotResponse.getPosition(), "LEFT");
                break;
            case "RIGHT":
                rotate(toyRobotResponse.getPosition(), "RIGHT");
                break;
            case "REPORT":
                report(toyRobotResponse);
                break;
            default:
                break;
        }
    }

    private void move(ToyRobotResponse toyRobotResponse, Position initialPosition) {
        Position position = toyRobotResponse.getPosition();
        Integer currentX = position.getxCoOrdinate();
        Integer currentÝ = position.getyCoOrdinate();
        String face = position.getFace();
        switch (face) {
            case "EAST":
                ++currentX;
                break;
            case "WEST":
                --currentX;
                break;
            case "NORTH":
                ++currentÝ;
                break;
            case "SOUTH":
                --currentÝ;
                break;

        }
        if (!position.isValidPosition(currentX, currentÝ)) {
            position.resetToInitialPosition(initialPosition);
            return;
        }
        position.setxCoOrdinate(currentX);
        position.setyCoOrdinate(currentÝ);
    }

    private void report(ToyRobotResponse toyRobotResponse) {
        toyRobotResponse.setReport(Boolean.TRUE);
    }

    private void rotate(Position position, String direction) {
        String changedDir = Direction.valueOf(position.getFace()).rotate90(direction).name();
        position.setFace(changedDir);
    }
}
