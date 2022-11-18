package com.sfu.group6.hungrycow.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InanimateFactoryTest {

    private HungryCowInanimateFactory fixture;

    @BeforeEach
    void setup()
    {

    }

    @Test
    void shouldMakeRegularRewardFromFactory()
    {
        fixture.makeRegularReward(0,0);
        // assert stuff, copy paste from what i've done
    }

    @Test
    void shouldMakeBonusRewardFromFactory()
    {
        fixture.makeBonusReward(0,0);

    }

    @Test
    void shouldMakePunishmentFromFactory()
    {
        fixture.makePunishment(0,0);
    }
}
