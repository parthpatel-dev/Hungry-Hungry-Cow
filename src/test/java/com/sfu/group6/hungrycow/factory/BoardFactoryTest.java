package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.driver.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    private final HungryCowBoardFactory boardFactory = new HungryCowBoardFactory();
    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private int[][] array2D = new int[11][11];



    private Board fixture;

    private final Board expected = Board.builder()
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
            .barriers(Collections.emptySet())
            .player(animateFactory.makePlayer(0,
                    0))
            .enemies(Collections.emptyList())
            .objectives(Collections.emptyList())
            .punishments(Collections.emptyList())
            .bonus(Collections.emptyList())
            .build();

    @BeforeEach
    void setup()
    {
        // Should set all spaces to 0
        for (int y = 0; y  < array2D.length; y++)
        {
            for (int x = 0; x < array2D[y].length; x++)
            {
                array2D[x][y] = 0;
            }
        }
        // Only sets the start and end space
        array2D[0][0] = 17;
        array2D[10][10] = 18;
        fixture = boardFactory.createBoard(array2D,animateFactory,inanimateFactory);
    }

//    @Test
//    void shouldMakeBoardFromFactory()
//    {
//        assert (fixture == expected);
//    }

    @Test
    void shouldMakeBoardWidthFromFactory()
    {
        assertThat(fixture.getWidth()).isEqualTo(expected.getWidth());
    }

    @Test
    void shouldMakeBoardHeightFromFactory()
    {
        assertThat(fixture.getHeight()).isEqualTo(expected.getHeight());
    }

    @Test
    void shouldMakeBoardStartSpaceFromFactory()
    {
        assertThat(fixture.getStartSpace()).isEqualTo(expected.getStartSpace());
    }

    @Test
    void shouldMakeBoardEndSpaceFromFactory()
    {
        assertThat(fixture.getEndSpace()).isEqualTo(expected.getEndSpace());
    }

    @Test
    void shouldMakeBoardBarriersFromFactory()
    {
        assertThat(fixture.getBarriers()).isEqualTo(expected.getBarriers());
    }

    @Test
    void shouldMakeBoardPlayerFromFactory()
    {
        assertThat(fixture.getPlayer().getPosition()).isEqualTo(expected.getPlayer().getPosition());
        assertThat(fixture.getPlayer().getFacingDirection()).isEqualTo(expected.getPlayer().getFacingDirection());
        assertThat(fixture.getPlayer().getScore()).isEqualTo(expected.getPlayer().getScore());
    }

    @Test
    void shouldMakeBoardEnemiesFromFactory()
    {
        assertThat(fixture.getEnemies()).isEqualTo(expected.getEnemies());
    }

    @Test
    void shouldMakeBoardObjectivesFromFactory()
    {
        assertThat(fixture.getObjectives()).isEqualTo(expected.getObjectives());
    }

    @Test
    void shouldMakeBoardPunishmentsFromFactory()
    {
        assertThat(fixture.getPunishments()).isEqualTo(expected.getPunishments());
    }

    @Test
    void shouldMakeBoardBonusFromFactory()
    {
        assertThat(fixture.getBonus()).isEqualTo(expected.getBonus());
    }

}
