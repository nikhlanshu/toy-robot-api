package com.caterpillar.toyrobotapi.dto;

public class ToyRobotResponse {
    private Position position;
    private transient boolean isReport;

    public ToyRobotResponse(Position position) {
        this.position = position;
    }

    public ToyRobotResponse() {
    }

    public boolean isReport() {
        return isReport;
    }

    public void setReport(boolean report) {
        isReport = report;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
