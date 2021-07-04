package com.caterpillar.toyrobotapi.exception;

public class RobotMissingException extends RuntimeException {
    public RobotMissingException() {
        super("ROBOT MISSING");
    }
}
