package com.caterpillar.toyrobotapi.dto;

public class ToyRobotRequest {
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "ToyRobotRequest{" +
                "command='" + command + '\'' +
                '}';
    }
}
