/**
 * This class provides the service to the {@link com.caterpillar.toyrobotapi.controller.ToyRobotController}
 * The job is to
 *  1. Validate incoming request
 *  2. On successful validation handovers the request to commandService to process the command action.
 */

package com.caterpillar.toyrobotapi.service;

import com.caterpillar.toyrobotapi.dto.ToyRobotRequest;
import com.caterpillar.toyrobotapi.dto.ToyRobotResponse;
import com.caterpillar.toyrobotapi.validator.ToyRobotValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DefaultToyRobotServiceImpl implements ToyRobotService {
    static Logger LOGGER = LoggerFactory.getLogger(DefaultToyRobotServiceImpl.class);
    private final ToyRobotValidator<ToyRobotRequest> toyRobotValidator;
    private final CommandService commandService;

    @Autowired
    public DefaultToyRobotServiceImpl(ToyRobotValidator<ToyRobotRequest> toyRobotValidator, CommandService commandService) {
        this.toyRobotValidator = toyRobotValidator;
        this.commandService = commandService;
    }


    @Override
    public String moveToy(ToyRobotRequest toyRobotRequest) {
        LOGGER.info(String.format("ToyRobot request %s",toyRobotRequest));
        toyRobotValidator.validate(toyRobotRequest);
        ToyRobotResponse toyRobotResponse = commandService.performCommand(toyRobotRequest.getCommand());
        if (toyRobotResponse.isReport()) {
            return toyRobotResponse.toString();
        }
        return StringUtils.EMPTY;
    }
}
