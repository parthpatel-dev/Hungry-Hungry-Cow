package com.sfu.group6.hungrycow.model;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.AbstractAnimate;
import com.sfu.group6.hungrycow.model.inanimate.AbstractInanimate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractInanimateTest {

    private TestInanimate fixture;

    @BeforeEach
    void setup() {
        fixture = createTestInanimate(0,
                                      0);
    }

    @Test
    void shouldMakeInanimatePositionAtZero() {
        assertThat(fixture.getPosition()
                          .getX()).isEqualTo(0);
        assertThat(fixture.getPosition()
                          .getY()).isEqualTo(0);
    }

    @Test
    void shouldMakeInanimateWithValueZero() {
        assertThat(fixture.getValue()).isEqualTo(0);
    }

    @SuperBuilder
    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    private static class TestInanimate extends AbstractInanimate {
    }

    private static TestInanimate createTestInanimate(int x,
                                                     int y) {
        Position initialPosition = Position.builder()
                                           .x(x)
                                           .y(y)
                                           .build();

        return TestInanimate.builder()
                            .position(initialPosition)
                            .value(0)
                            .build();

    }
}
