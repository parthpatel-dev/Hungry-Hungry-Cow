package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InanimateFactoryTest {

    private HungryCowInanimateFactory fixture;

    @BeforeEach
    void setup()
    {
        fixture = new HungryCowInanimateFactory();

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
        BonusReward factoryBonusReward = fixture.makeBonusReward(0,0);

        assertThat(factoryBonusReward.getPosition().getX()).isEqualTo(0);
        assertThat(factoryBonusReward.getPosition().getY()).isEqualTo(0);
        assertThat(factoryBonusReward.getValue()).isEqualTo(10); //change this to defined number like in InanimateFactory
    }

    @Test
    void shouldMakePunishmentFromFactory()
    {
        fixture.makePunishment(0,0);
    }
}
