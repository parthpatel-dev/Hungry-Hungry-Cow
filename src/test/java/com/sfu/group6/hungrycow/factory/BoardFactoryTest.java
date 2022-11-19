package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.driver.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Dictionary;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    private final HungryCowBoardFactory boardFactory = new HungryCowBoardFactory();
    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private int[][] array2D = new int[11][11];

    private Board fixture;


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
        array2D[0][1] = 1;
        array2D[10][10] = 18;
        fixture = boardFactory.createBoard(array2D,animateFactory,inanimateFactory);

    }

    @Test
    void shouldMakeBoardWidthFromFactory()
    {
        assertThat(fixture.getWidth()).isEqualTo(11);
    }

    @Test
    void shouldMakeBoardHeightFromFactory()
    {
        assertThat(fixture.getHeight()).isEqualTo(11);
    }

    @Test
    void shouldMakeBoardStartSpaceFromFactory()
    {
        assertThat(fixture.getStartSpace().getX()).isEqualTo(0);
        assertThat(fixture.getStartSpace().getY()).isEqualTo(0);
    }

    @Test
    void shouldMakeBoardEndSpaceFromFactory()
    {
        assertThat(fixture.getEndSpace().getX()).isEqualTo(10);
        assertThat(fixture.getEndSpace().getY()).isEqualTo(10);
    }

    @Test
    void shouldMakeBoardBarriersFromFactory()
    {
        assertThat(fixture.getBarriers()).isEqualTo(Collections.emptySet());
    }

    @Test
    void shouldMakeBoardPlayerFromFactory()
    {
        assertThat(fixture.getPlayer().getPosition().getX()).isEqualTo(1);
        assertThat(fixture.getPlayer().getPosition().getY()).isEqualTo(0);
        assertThat(fixture.getPlayer().getFacingDirection()).isEqualTo(Direction.RIGHT);
        assertThat(fixture.getPlayer().getScore()).isEqualTo(HungryCowAnimateFactory.INITIAL_PLAYER_SCORE);
    }

    @Test
    void shouldMakeBoardEnemiesFromFactory()
    {
        assertThat(fixture.getEnemies()).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldMakeBoardObjectivesFromFactory()
    {
        assertThat(fixture.getObjectives()).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldMakeBoardPunishmentsFromFactory()
    {
        assertThat(fixture.getPunishments()).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldMakeBoardBonusFromFactory()
    {
        assertThat(fixture.getBonus()).isEqualTo(Collections.emptyList());
    }

}
