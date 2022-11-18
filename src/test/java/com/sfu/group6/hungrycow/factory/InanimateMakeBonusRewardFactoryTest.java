package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InanimateMakeBonusRewardFactoryTest {

    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private static final int BONUS_REWARD_VALUE = 20;

    private BonusReward fixture;
    private BonusReward expected = BonusReward.builder().position(Position.builder().x(0).y(0).build()).value(BONUS_REWARD_VALUE).build();

    @BeforeEach
    void setup()
    {
        fixture = inanimateFactory.makeBonusReward(0,0);
    }

    @Test
    void shouldMakePositionFromInanimateFactory()
    {
        assertThat(fixture.getPosition()).isEqualTo(expected.getPosition());
    }

    @Test
    void shouldMakeValueFromInanimateFactory()
    {
        assertThat(fixture.getValue()).isEqualTo(expected.getValue());
    }
}