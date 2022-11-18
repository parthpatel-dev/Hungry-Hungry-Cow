package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnimatePlayerFactoryTest {
    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
    private Player fixture;
    private final Player expected = Player.builder().score(0).position(Position.builder().x(0).y(0).build()).facingDirection(Direction.RIGHT).build();

    @BeforeEach
    void setup()
    {
        fixture = animateFactory.makePlayer(0,0);
    }

//    @Test
//    void shouldMakePlayerObjectFromFactory()
//    {
//        assert(fixture == expected);
//    }

    @Test
    void shouldMakePlayerScoreFromFactory()
    {
        assertThat(fixture.getScore()).isEqualTo(expected.getScore());
    }

    @Test
    void shouldMakePlayerPositionFromFactory()
    {
        assertThat(fixture.getPosition()).isEqualTo(expected.getPosition());
    }

    @Test
    void shouldMakePlayerDirectionFromFactory()
    {
        assertThat(fixture.getFacingDirection()).isEqualTo(expected.getFacingDirection());
    }



}

