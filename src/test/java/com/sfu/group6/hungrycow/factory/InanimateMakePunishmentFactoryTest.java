package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InanimateMakePunishmentFactoryTest {

    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private static final int PUNISHMENT_VALUE = 10;
    private Punishment fixture;
    private Punishment expected = Punishment.builder().position(Position.builder().x(0).y(0).build()).value(PUNISHMENT_VALUE).build();

    @BeforeEach
    void setup()
    {
        fixture = inanimateFactory.makePunishment(0,0);
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
