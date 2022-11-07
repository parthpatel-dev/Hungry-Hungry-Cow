package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import com.sfu.group6.hungrycow.model.animate.Player;

/**
 * HungryCowAnimateFactory creates animates for the Hungry Cow game.
 * Initial player score starts at 0.
 */
public class HungryCowAnimateFactory {
    /**
     * The initial player score for the Hungry Cow game is 0.
     */
    public static final int INITIAL_PLAYER_SCORE = 0;

    /**
     * Creates a Player object using its builder method.
     *
     * @param x the initial x coordinate of the Player
     * @param y the initial y coordinate of the Player
     * @return a new Player object
     */
    public Player makePlayer(int x,
                             int y) {
        Position initialPosition = Position.builder()
                                           .x(x)
                                           .y(y)
                                           .build();

        return Player.builder()
                     .score(INITIAL_PLAYER_SCORE)
                     .position(initialPosition)
                     .facingDirection(Direction.RIGHT)
                     .build();
    }

    /**
     * Creates an Enemy object using its builder method.
     *
     * @param x the initial x coordinate of the Enemy
     * @param y the initial y coordinate of the Enemy
     * @return a new Enemy object
     */
    public Enemy makeEnemy(int x,
                           int y) {
        Position initialPosition = Position.builder()
                                           .x(x)
                                           .y(y)
                                           .build();

        return Enemy.builder()
                    .position(initialPosition)
                    .facingDirection(Direction.RIGHT)
                    .build();
    }
}
