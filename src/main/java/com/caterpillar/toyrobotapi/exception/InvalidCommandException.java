package com.caterpillar.toyrobotapi.exception;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("INVALID COMMAND");
    }
}
