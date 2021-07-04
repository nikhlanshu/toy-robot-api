/**
 * Validates the input payload.
 * Does null and Empty check of the incoming command.
 * Exception thrown is handled by ExceptionHandler {@link com.caterpillar.toyrobotapi.advice.ToyRobotExceptionHandler}
 */

package com.caterpillar.toyrobotapi.validator;

import com.caterpillar.toyrobotapi.dto.ToyRobotRequest;
import com.caterpillar.toyrobotapi.exception.InvalidCommandException;
import com.caterpillar.toyrobotapi.exception.RobotMissingException;
import com.caterpillar.toyrobotapi.service.CommandPatternMatchingUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ToyRobotRequestValidator implements ToyRobotValidator<ToyRobotRequest> {

    @Override
    public void validate(ToyRobotRequest model) {
        String command = Optional.ofNullable(model)
                .map(ToyRobotRequest::getCommand)
                .filter(StringUtils::isNotEmpty)
                .orElseThrow(InvalidCommandException::new);
        if (CommandPatternMatchingUtil.isRobotMissing(command)) {
            throw new RobotMissingException();
        }
        if (!CommandPatternMatchingUtil.isValidCommand(command)) {
            throw new InvalidCommandException();
        }
    }
}
