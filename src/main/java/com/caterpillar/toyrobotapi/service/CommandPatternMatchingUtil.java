/**
 * This calls does the pattern matching for the input command to position and move the toy robot.
 * All the utility methods to do the pattern matching using java's regex api.
 */

package com.caterpillar.toyrobotapi.service;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommandPatternMatchingUtil {
    static String PLACE_REGEX = "^PLACE\\s*[0-5]\\s*,\\s*[0-5]\\s*,\\s*((EAST)|(WEST)|(NORTH)|(SOUTH))";
    static String MOVEMENT_REGEX = "((MOVE)\\s*|\\s*(LEFT)\\s*|\\s*(RIGHT)\\s*|\\s*(REPORT)\\s*)*";
    static String COMMAND_REGEX = PLACE_REGEX.concat("\\s*").concat(MOVEMENT_REGEX);
    static String PLACE_REGEX_ANY = "^PLACE\\s*[0-5]\\s*,\\s*[0-5]\\s*,\\s*((EAST)|(WEST)|(NORTH)|(SOUTH)).*";

    /**
     * It checks if Robot initial position is provided in the input command.
     * It does not check the subsequent command. There may be invalid chars after the PLACE command.
     * The entire command is validated in {@link #isValidCommand(String)}
     * @param command
     * @return
     */
    public static boolean isRobotMissing(String command) {
        if (StringUtils.isNotEmpty(command)) {
            String trimmedCommand = command.trim();
            Pattern commandCompiler = Pattern.compile(PLACE_REGEX_ANY);
            Matcher matcher = commandCompiler.matcher(trimmedCommand);
            return !matcher.matches();
        }
        return true;
    }

    /**
     * This take a nonEmpty command and validate the command against the regex.
     *
     * @param command
     * @return
     */
    public static boolean isValidCommand(String command) {
        if (StringUtils.isNotEmpty(command)) {
            String trimmedCommand = command.trim();
            Pattern commandCompiler = Pattern.compile(COMMAND_REGEX);
            Matcher matcher = commandCompiler.matcher(trimmedCommand);
            return matcher.matches();
        }
        return false;
    }

    /**
     * This extracts the PLACE command from a valid input command.
     * The command must have validated using {@link #isValidCommand(String)} before coming to this method.
     * @param command
     * @return
     */
    public static String extractPositionCommand(String command) {
        Pattern positionCompiler = Pattern.compile(MOVEMENT_REGEX);
        String[] tokens = positionCompiler.split(command);
        return String.join("", tokens);
    }

    /**
     * This extracts Subsequent commands after PLACE command from a valid input command.
     * The command must have validated using {@link #isValidCommand(String)} before coming to this method.
     * @param command
     * @return
     */
    public static String extractMoveCommand(String command) {
        Pattern moveCompiler = Pattern.compile(PLACE_REGEX);
        String[] tokens = moveCompiler.split(command);
        return Arrays.stream(tokens)
                .filter(StringUtils::isNotEmpty)
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }

    /**
     * Tokenize the using regular expression and filter out unused tokens using the
     * supplied predicate.
     * @param command
     * @param regEx
     * @param predicate
     * @return
     */

    public static List<String> tokenize(String command, String regEx, Predicate<String> predicate) {
        if (StringUtils.isNotEmpty(command)) {
            String[] commands = command.split(regEx);
            return Arrays.stream(commands)
                    .filter(predicate)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
