package com.sfu.group6.hungrycow.model.animate;

import com.sfu.group6.hungrycow.control.Position;

public class HungryCowAnimateFactory {
    public static final int INITIAL_PLAYER_SCORE = 0;

    public Player makePlayer(int x,
                             int y) {
        Position initialPosition = Position.builder()
                                           .x(x)
                                           .y(y)
                                           .build();

        return Player.builder()
                     .score(INITIAL_PLAYER_SCORE)
                     .position(initialPosition)
                     .build();
    }

    public Enemy makeEnemy(int x,
                           int y) {
        Position initialPosition = Position.builder()
                                           .x(x)
                                           .y(y)
                                           .build();

        return Enemy.builder()
                    .position(initialPosition)
                    .build();
    }
}
