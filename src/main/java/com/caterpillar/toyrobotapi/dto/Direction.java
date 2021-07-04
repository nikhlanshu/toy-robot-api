/**
 * This enum class provides the direction info after rotating to the specified direction.
 * Valid rotation is to LEFT or RIGHT.
 */

package com.caterpillar.toyrobotapi.dto;

public enum Direction {
    EAST() {
        @Override
        public Direction rotate90(String direction) {
            if ("LEFT".equals(direction)) {
                return NORTH;
            }
            return SOUTH;
        }
    },
    WEST() {
        @Override
        public Direction rotate90(String direction) {
            if ("LEFT".equals(direction)) {
                return SOUTH;
            }
            return NORTH;
        }
    },
    NORTH() {
        @Override
        public Direction rotate90(String direction) {
            if ("LEFT".equals(direction)) {
                return WEST;
            }
            return EAST;
        }
    },
    SOUTH() {
        @Override
        public Direction rotate90(String direction) {
            if ("LEFT".equals(direction)) {
                return EAST;
            }
            return WEST;
        }
    };

    public abstract Direction rotate90(String direction);
}
