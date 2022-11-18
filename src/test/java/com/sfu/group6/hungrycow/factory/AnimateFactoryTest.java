package com.sfu.group6.hungrycow.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnimateFactoryTest {

    private HungryCowAnimateFactory fixture;
    @BeforeEach
    void setup()
    {

    }

    @Test
    void shouldMakePlayerFromFactory()
    {

        fixture.makePlayer(0,0);
        // Assert player pos, direction, score

    }

    @Test
    void shouldMakeEnemyFromFactory()
    {
        fixture.makeEnemy(0,0);
        // Assert same thing as player
    }
}
