package com.sfu.group6.hungrycow.model.inanimate;

import com.sfu.group6.hungrycow.control.Position;

public class HungryCowInanimateFactory {
    public static final int REGULAR_REWARD_VALUE = 10;
    public static final int BONUS_REWARD_VALUE = 20;
    public static final int PUNISHMENT_VALUE = 10;

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
