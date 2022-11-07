package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;

/**
 * HungryCowInanimateFactory creates inanimate entities for the Hungry Cow game.
 */
public class HungryCowInanimateFactory {
    /**
     * The regular reward value is set to 10.
     */
    public static final int REGULAR_REWARD_VALUE = 10;
    /**
     * The bonus reward value is set to 20.
     */
    public static final int BONUS_REWARD_VALUE = 20;
    /**
     * The punishment value is set to 10.
     */
    public static final int PUNISHMENT_VALUE = 10;

    /**
     * Creates a RegularReward object using its builder method.
     *
     * @param x the initial x coordinate of the RegularReward
     * @param y the initial y coordinate of the RegularReward
     * @return a new RegularReward object
     */
    public RegularReward makeRegularReward(int x,
                                           int y) {
        Position position = Position.builder()
                                    .x(x)
                                    .y(y)
                                    .build();

        return RegularReward.builder()
                            .position(position)
                            .value(REGULAR_REWARD_VALUE)
                            .build();
    }

    /**
     * Creates a BonusReward object using its builder method.
     *
     * @param x the initial x coordinate of the BonusReward
     * @param y the initial y coordinate of the BonusReward
     * @return a new BonusReward object
     */
    public BonusReward makeBonusReward(int x,
                                       int y) {
        Position position = Position.builder()
                                    .x(x)
                                    .y(y)
                                    .build();

        return BonusReward.builder()
                          .value(BONUS_REWARD_VALUE)
                          .position(position)
                          .build();
    }

    /**
     * Creates a Punishment object using its builder method.
     *
     * @param x the initial x coordinate of the Punishment
     * @param y the initial y coordinate of the Punishment
     * @return a new Punishment object
     */
    public Punishment makePunishment(int x,
                                     int y) {
        Position position = Position.builder()
                                    .x(x)
                                    .y(y)
                                    .build();

        return Punishment.builder()
                         .value(PUNISHMENT_VALUE)
                         .position(position)
                         .build();
    }
}
