package com.sfu.group6.hungrycow.model;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.AbstractAnimate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractAnimateTest {
    private TestAnimate fixture;

    @BeforeEach
    void setup() {
        fixture = createTestAnimate(0,
                                    0);
    }

    @Test
    void shouldMoveAnimateRightByOnePositionAndChangeFacingDirectionToRight() {
        fixture.move(Direction.RIGHT);
        assertThat(fixture.getPosition()
                          .getX()).isEqualTo(1);
        assertThat(fixture.getPosition()
                          .getY()).isEqualTo(0);
        assertThat(fixture.getFacingDirection()).isEqualTo(Direction.RIGHT);
    }

    @Test
    void shouldMoveAnimateLeftByOnePositionAndChangeFacingDirectionToLeft() {
        fixture.move(Direction.LEFT);
        assertThat(fixture.getPosition()
                          .getX()).isEqualTo(-1);
        assertThat(fixture.getPosition()
                          .getY()).isEqualTo(0);
        assertThat(fixture.getFacingDirection()).isEqualTo(Direction.LEFT);
    }

    @Test
    void shouldMoveAnimateUpByOnePositionAndChangeFacingDirectionToUp() {
        fixture.move(Direction.UP);
        assertThat(fixture.getPosition()
                          .getX()).isEqualTo(0);
        assertThat(fixture.getPosition()
                          .getY()).isEqualTo(-1);
        assertThat(fixture.getFacingDirection()).isEqualTo(Direction.UP);
    }

    @Test
    void shouldMoveAnimateDownByOnePositionAndChangeFacingDirectionToDown() {
        fixture.move(Direction.DOWN);
        assertThat(fixture.getPosition()
                          .getX()).isEqualTo(0);
        assertThat(fixture.getPosition()
                          .getY()).isEqualTo(1);
        assertThat(fixture.getFacingDirection()).isEqualTo(Direction.DOWN);
    }

    @SuperBuilder
    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    private static class TestAnimate extends AbstractAnimate {
    }

    private static TestAnimate createTestAnimate(int x,
                                                 int y) {
        Position initialPosition = Position.builder()
                                           .x(x)
                                           .y(y)
                                           .build();

        return TestAnimate.builder()
                          .position(initialPosition)
                          .facingDirection(Direction.RIGHT)
                          .build();
    }
}
