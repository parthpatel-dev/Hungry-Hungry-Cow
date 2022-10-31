package com.sfu.group6.hungrycow.model.inanimate;

public class HungryCowInanimateFactory {
    public static final int REGULAR_REWARD_VALUE = 10;
    public static final int BONUS_REWARD_VALUE = 20;
    public static final int PUNISHMENT_VALUE = 10;

    public RegularReward makeRegularReward() {
        return RegularReward.builder()
                            .value(REGULAR_REWARD_VALUE)
                            .build();
    }

    public BonusReward makeBonusReward() {
        return BonusReward.builder()
                          .value(BONUS_REWARD_VALUE)
                          .build();
    }

    public Punishment makePunishment() {
        return Punishment.builder()
                         .value(PUNISHMENT_VALUE)
                         .build();
    }
}
