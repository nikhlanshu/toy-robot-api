package com.caterpillar.toyrobotapi.translator;

public interface ToyRobotTranslator<I, O> {
    O of(I input);
}
