package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InanimateFactoryTest {

    private HungryCowInanimateFactory fixture;

    @BeforeEach
    void setup() {
        fixture = new HungryCowInanimateFactory();

    }

    @Test
    void shouldMakeRegularRewardFromFactory() {
        RegularReward factoryRegularReward = fixture.makeRegularReward(0,
                                                                       0);
        assertThat(factoryRegularReward.getPosition()
                                       .getX()).isEqualTo(0);
        assertThat(factoryRegularReward.getPosition()
                                       .getY()).isEqualTo(0);
        assertThat(factoryRegularReward.getValue()).isEqualTo(HungryCowInanimateFactory.REGULAR_REWARD_VALUE);
    }

    @Test
    void shouldMakeBonusRewardFromFactory() {
        BonusReward factoryBonusReward = fixture.makeBonusReward(0,
                                                                 0);

        assertThat(factoryBonusReward.getPosition()
                                     .getX()).isEqualTo(0);
        assertThat(factoryBonusReward.getPosition()
                                     .getY()).isEqualTo(0);
        assertThat(factoryBonusReward.getValue()).isEqualTo(HungryCowInanimateFactory.BONUS_REWARD_VALUE);
    }

    @Test
    void shouldMakePunishmentFromFactory() {
        Punishment factoryPunishment = fixture.makePunishment(0,
                                                              0);
        assertThat(factoryPunishment.getPosition()
                                    .getX()).isEqualTo(0);
        assertThat(factoryPunishment.getPosition()
                                    .getY()).isEqualTo(0);
        assertThat(factoryPunishment.getValue()).isEqualTo(HungryCowInanimateFactory.PUNISHMENT_VALUE);
    }
}
