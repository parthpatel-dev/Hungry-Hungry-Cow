package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import com.sfu.group6.hungrycow.model.animate.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnimateFactoryTest {

    private HungryCowAnimateFactory fixture;

    @BeforeEach
    void setup() {
        fixture = new HungryCowAnimateFactory();
    }

    @Test
    void shouldMakePlayerFromFactory() {

        Player factoryPlayer = fixture.makePlayer(0,
                                                  0);
        assertThat(factoryPlayer.getScore()).isEqualTo(HungryCowAnimateFactory.INITIAL_PLAYER_SCORE);
        assertThat(factoryPlayer.getPosition()
                                .getX()).isEqualTo(0);
        assertThat(factoryPlayer.getPosition()
                                .getY()).isEqualTo(0);
        assertThat(factoryPlayer.getFacingDirection()).isEqualTo(Direction.RIGHT);

    }

    @Test
    void shouldMakeEnemyFromFactory() {
        Enemy factoryEnemy = fixture.makeEnemy(0,
                                               0);

        assertThat(factoryEnemy.getPosition()
                               .getX()).isEqualTo(0);
        assertThat(factoryEnemy.getPosition()
                               .getY()).isEqualTo(0);
        assertThat(factoryEnemy.getFacingDirection()).isEqualTo(Direction.RIGHT);
    }
}
