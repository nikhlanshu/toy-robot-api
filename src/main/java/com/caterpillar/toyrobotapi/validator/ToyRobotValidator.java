package com.caterpillar.toyrobotapi.validator;

@FunctionalInterface
public interface ToyRobotValidator<T> {
    public void validate(T model);
}
