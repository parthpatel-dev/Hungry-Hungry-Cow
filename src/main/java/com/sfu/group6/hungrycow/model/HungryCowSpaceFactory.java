package com.sfu.group6.hungrycow.model;

import com.sfu.group6.hungrycow.control.Position;

public class HungryCowSpaceFactory {
    public Space makeBarrierSpace(int x,
                             int y) {
        Position position = Position.builder()
                                    .x(x)
                                    .y(y)
                                    .build();

        return Space.builder()
                    .position(position)
                    .barrierSpace(true)
                    .build();
    }

    public Space makeStartingSpace(int x,
                                   int y) {
        Position position = Position.builder()
                                    .x(x)
                                    .y(y)
                                    .build();

        return Space.builder()
                    .position(position)
                    .startingSpace(true)
                    .build();
    }

    public Space makeEndSpace(int x,
                              int y) {
        Position position = Position.builder()
                                    .x(x)
                                    .y(y)
                                    .build();

        return Space.builder()
                    .position(position)
                    .endSpace(true)
                    .build();
    }
}
