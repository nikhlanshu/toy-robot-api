package com.caterpillar.toyrobotapi.dto;

public class Position {
    static final Integer MAX = 5;
    static final Integer MIN = 0;
    private Integer xCoOrdinate;
    private Integer yCoOrdinate;
    private String face;

    public Position(Integer xCoOrdinate, Integer yCoOrdinate, String face) {
        this.xCoOrdinate = xCoOrdinate;
        this.yCoOrdinate = yCoOrdinate;
        this.face = face;
    }

    public Integer getxCoOrdinate() {
        return xCoOrdinate;
    }

    public Integer getyCoOrdinate() {
        return yCoOrdinate;
    }

    public String getFace() {
        return face;
    }

    public void setxCoOrdinate(Integer xCoOrdinate) {
        this.xCoOrdinate = xCoOrdinate;
    }

    public void setyCoOrdinate(Integer yCoOrdinate) {
        this.yCoOrdinate = yCoOrdinate;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void resetToInitialPosition(Position initialPosition) {
        this.xCoOrdinate = initialPosition.xCoOrdinate;
        this.yCoOrdinate = initialPosition.yCoOrdinate;
        this.face = initialPosition.face;
    }

    public boolean isValidPosition(Integer currentX, Integer currentÝ) {
        if (currentX < MIN || currentX > MAX || currentÝ < MIN || currentÝ > MAX) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public String toString() {
        return xCoOrdinate + "," + yCoOrdinate + "," + face;
    }
}
