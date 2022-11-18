package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnimateEnemyFactoryTest {

    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();

    private Enemy fixture;
    private final Enemy expected = Enemy.builder().position(Position.builder().x(0).y(0).build()).facingDirection(Direction.RIGHT).build();

    @BeforeEach
    void setup()
    {
        fixture = animateFactory.makeEnemy(0,0);
    }

//    @Test
//    void shouldMakeEnemyObjectFromFactory()
//    {
//
//        assert(fixture == expected);
//    }
    @Test
    void shouldMakeEnemyPositionFromFactory()
    {
        assertThat(fixture.getPosition()).isEqualTo(expected.getPosition());
    }

    @Test
    void shouldMakeEnemyDirectionFromFactory()
    {
        assertThat(fixture.getFacingDirection()).isEqualTo(expected.getFacingDirection());
    }


}

