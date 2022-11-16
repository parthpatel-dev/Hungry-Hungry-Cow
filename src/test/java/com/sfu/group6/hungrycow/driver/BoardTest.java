package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.factory.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.factory.HungryCowBoardFactory;
import com.sfu.group6.hungrycow.factory.HungryCowInanimateFactory;
import com.sfu.group6.hungrycow.ui.MapLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    private final String boardDataFilePath = "/maps/map1.txt";
    private final MapLoader mapLoader = new MapLoader();
    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private final HungryCowBoardFactory boardFactory = new HungryCowBoardFactory();

    private Board fixture;

    @BeforeEach
    void setup() {
    }

    @Test
    void shouldUseDefaultsForNewBoardInstance() {
        fixture = createTestBoard();

        assertThat(fixture.isGameOver()).isFalse();
        assertThat(fixture.isPlayerWin()).isFalse();
        assertThat(fixture.getTickCounter()).isZero();
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void shouldIncrementTickCounterForEachDirectionInput(Direction direction) {
        fixture = createTestBoard();

        fixture.tickBoardState(direction);
        assertThat(fixture.getTickCounter()).isEqualTo(1);
    }

    private Board createTestBoard() {
        return createTestBoard(Collections.emptySet());
    }

    private Board createTestBoard(Set<Position> barriers) {
        return Board.builder()
                    .width(10)
                    .height(10)
                    .startSpace(Position.builder()
                                        .x(0)
                                        .y(0)
                                        .build())
                    .endSpace(Position.builder()
                                      .x(10)
                                      .y(10)
                                      .build())
                    .barriers(barriers)
                    .player(animateFactory.makePlayer(0,
                                                      0))
                    .enemies(Collections.emptyList())
                    .objectives(Collections.emptyList())
                    .punishments(Collections.emptyList())
                    .bonus(Collections.emptyList())
                    .build();
    }
}
