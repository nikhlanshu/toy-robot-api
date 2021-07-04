/**
 * Service impl class does provide logic to the command coming in the request.
 * A command can be divided into two parts
 *  1. Position Ex - PLACE 0,0,NORTH
 *  2. Subsequent Command . Ex - MOVE LEFT REPORT
 *
 *  This calls Position command service/translator, Subsequent Command Service to their part.
 */

package com.caterpillar.toyrobotapi.service;

import com.caterpillar.toyrobotapi.dto.Position;
import com.caterpillar.toyrobotapi.dto.ToyRobotResponse;
import com.caterpillar.toyrobotapi.translator.ToyRobotTranslator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCommandServiceImpl implements CommandService {
    static Logger LOGGER = LoggerFactory.getLogger(DefaultToyRobotServiceImpl.class);

    private final ToyRobotTranslator<String, Position> commandToPositionTranslator;
    private final SubsequentCommandService subsequentCommandService;

    @Autowired
    public DefaultCommandServiceImpl(ToyRobotTranslator<String, Position> commandToPositionTranslator, SubsequentCommandService subsequentCommandService) {
        this.commandToPositionTranslator = commandToPositionTranslator;
        this.subsequentCommandService = subsequentCommandService;
    }

    @Override
    public ToyRobotResponse performCommand(String command) {
        String positionCommand = CommandPatternMatchingUtil.extractPositionCommand(command);
        LOGGER.info(String.format("Position command %s", positionCommand));
        Position initialPosition = commandToPositionTranslator.of(positionCommand);
        LOGGER.info(String.format("Initial Position command translated %s", initialPosition));
        String subsequentCommand = CommandPatternMatchingUtil.extractMoveCommand(command);
        LOGGER.info(String.format("Subsequent Command %s", subsequentCommand));
        if (StringUtils.isNotEmpty(subsequentCommand)) {
            return subsequentCommandService.performCommand(initialPosition, subsequentCommand);
        }
        return new ToyRobotResponse(initialPosition);
    }
}
