package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    private final HungryCowBoardFactory boardFactory = new HungryCowBoardFactory();
    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private final int[][] emptyArray2D = {
            {0, 1, 0, 0},
            {0, 0, 17, 0},
            {0, 0, 0, 18},
            {0, 0, 0, 0}
    };

    private final int[][] filledArray2D = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 0, 0}
    };

    private Board fixture;


    @BeforeEach
    void setup() {
    }

    @Test
    void shouldMakeBoardWidthFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getWidth()).isEqualTo(4);
    }

    @Test
    void shouldMakeBoardHeightFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getHeight()).isEqualTo(4);
    }

    @Test
    void shouldMakeBoardStartSpaceFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getStartSpace()
                          .getX()).isEqualTo(2);
        assertThat(fixture.getStartSpace()
                          .getY()).isEqualTo(1);
    }

    @Test
    void shouldMakeBoardEndSpaceFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getEndSpace()
                          .getX()).isEqualTo(3);
        assertThat(fixture.getEndSpace()
                          .getY()).isEqualTo(2);
    }

    @Test
    void shouldMakeBoardNoBarriersFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getBarriers()).isEqualTo(Collections.emptySet());
    }

    @Test
    void shouldMakeBoardPlayerFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getX()).isEqualTo(1);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getY()).isEqualTo(0);
        assertThat(fixture.getPlayer()
                          .getFacingDirection()).isEqualTo(Direction.RIGHT);
        assertThat(fixture.getPlayer()
                          .getScore()).isEqualTo(HungryCowAnimateFactory.INITIAL_PLAYER_SCORE);
    }

    @Test
    void shouldMakeBoardNoEnemiesFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getEnemies()).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldMakeBoardNoObjectivesFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getObjectives()).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldMakeBoardNoPunishmentsFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getPunishments()).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldMakeBoardNoBonusFromFactory() {
        fixture = boardFactory.createBoard(emptyArray2D,
                                           animateFactory,
                                           inanimateFactory);
        assertThat(fixture.getBonus()).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldMakeBoardWithBarriersFromFactory() {
        fixture = boardFactory.createBoard(filledArray2D,
                                           animateFactory,
                                           inanimateFactory);

        Position position = Position.builder()
                                    .x(2)
                                    .y(0)
                                    .build();

        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(3);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(4);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(0);
        position.setY(1);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(1);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(2);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(3);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(4);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(0);
        position.setY(2);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(1);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
        position.setX(2);
        assertThat(fixture.getBarriers()
                          .contains(position)).isTrue();
    }

    @Test
    void shouldMakeBoardWithObjectiveFromFactory()
    {
        fixture = boardFactory.createBoard(filledArray2D,
                                           animateFactory,
                                           inanimateFactory);

        List<RegularReward> objectives = fixture.getObjectives();
        assertThat(objectives.get(0).getPosition().getX()).isEqualTo(3);
        assertThat(objectives.get(0).getPosition().getY()).isEqualTo(2);
    }

    @Test
    void shouldMakeBoardWithBonusFromFactory()
    {
        fixture = boardFactory.createBoard(filledArray2D,
                                           animateFactory,
                                           inanimateFactory);

        List<BonusReward> bonus = fixture.getBonus();
        assertThat(bonus.get(0).getPosition().getX()).isEqualTo(4);
        assertThat(bonus.get(0).getPosition().getY()).isEqualTo(2);
    }

    @Test
    void shouldMakeBoardWithPunishmentFromFactory()
    {
        fixture = boardFactory.createBoard(filledArray2D,
                                           animateFactory,
                                           inanimateFactory);

        List<Punishment> punishments = fixture.getPunishments();
        assertThat(punishments.get(0).getPosition().getX()).isEqualTo(0);
        assertThat(punishments.get(0).getPosition().getY()).isEqualTo(3);
    }


}
