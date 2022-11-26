package com.sfu.group6.hungrycow.model;

import com.sfu.group6.hungrycow.factory.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.factory.HungryCowInanimateFactory;
import com.sfu.group6.hungrycow.model.animate.Player;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private Player fixture;

    @BeforeEach
    void setup() {
        fixture = animateFactory.makePlayer(0,
                                            0);
    }

    @Test
    void shouldPunishPlayerByPunishmentValue() {
        Punishment punishment = inanimateFactory.makePunishment(0,
                                                                0);
        fixture.punishPlayer(punishment);
        assertThat(fixture.getScore()).isEqualTo(-punishment.getValue());
    }

    @Test
    void shouldRewardPlayerByRegularRewardValue() {
        RegularReward regularReward = inanimateFactory.makeRegularReward(0,
                                                                         0);
        fixture.rewardPlayer(regularReward);
        assertThat(fixture.getScore()).isEqualTo(regularReward.getValue());
    }

    @Test
    void shouldRewardPlayerByBonusRewardValue() {
        BonusReward bonusReward = inanimateFactory.makeBonusReward(0,
                                                                   0);
        fixture.rewardPlayer(bonusReward);
        assertThat(fixture.getScore()).isEqualTo(bonusReward.getValue());
    }
}
