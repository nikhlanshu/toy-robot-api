package com.caterpillar.toyrobotapi.translator;

import com.caterpillar.toyrobotapi.dto.Position;
import com.caterpillar.toyrobotapi.service.CommandPatternMatchingUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandToPositionTranslator implements ToyRobotTranslator<String, Position> {

    @Override
    public Position of(String positionCommand) {
        String trimmedCommand = positionCommand.trim();
        List<String> placeTokensList = CommandPatternMatchingUtil.tokenize(trimmedCommand,
                "( +)|,", placeToken -> !("PLACE".equals(placeToken) || "".equals(placeToken.trim())));
        Integer xCo = Integer.parseInt(placeTokensList.get(0));
        Integer yCo = Integer.parseInt(placeTokensList.get(1));
        String face = placeTokensList.get(2);
        return new Position(xCo, yCo, face);
    }
}
