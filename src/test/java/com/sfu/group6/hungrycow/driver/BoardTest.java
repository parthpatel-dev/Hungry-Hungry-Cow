package com.sfu.group6.hungrycow.driver;

import com.google.common.annotations.VisibleForTesting;
import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.factory.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.factory.HungryCowBoardFactory;
import com.sfu.group6.hungrycow.factory.HungryCowInanimateFactory;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;
import com.sfu.group6.hungrycow.ui.MapLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    private final String boardDataFilePath = "/maps/map1.txt";
    private final MapLoader mapLoader = new MapLoader();
    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private final HungryCowBoardFactory boardFactory = new HungryCowBoardFactory();

    private Board fixture, newFixture, validEdgeFixture;
    private Set<Position> barriersTest;
    private List<Enemy> oneEnemyTest, manyEnemiesTest, oneEnemyTestForMovingRight;
    
    @BeforeEach
    void setup() {
    	barriersTest = Set.of(Position.builder()
                .x(0)
                .y(1)
                .build(),
                Position.builder()
                .x(2)
                .y(1)
                .build(),
                Position.builder()
                .x(1)
                .y(0)
                .build(),
                Position.builder()
                .x(1)
                .y(2)
                .build());
        oneEnemyTest = List.of(animateFactory.makeEnemy(10,
                10));
        manyEnemiesTest = List.of(animateFactory.makeEnemy(10,
                10), animateFactory.makeEnemy(9,
                        9));
        oneEnemyTestForMovingRight = new ArrayList<>();
        List<RegularReward> objectives = new ArrayList<>();
        List<BonusReward> bonusRewards = new ArrayList<>();
        List<Punishment> punishments = new ArrayList<>();
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
    
    
    //Using Player to test Abstract animate for validMove
    @ParameterizedTest
    @EnumSource(Direction.class)
	void shouldCheckForValidMoveAbstractAnimate(Direction direction) {
    	
    	//Test barriers
		fixture = createTestBoard(10,10,barriersTest,Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),1,1);
		assertThat(fixture.validMove(fixture.getPlayer(), direction)).isFalse();
		
		//Test if returns true
		newFixture = createTestBoard(10,10,Collections.emptySet(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),1,1);
		assertThat(newFixture.validMove(newFixture.getPlayer(), direction)).isTrue();
		
		//Test if edge of map is reached
		validEdgeFixture = createTestBoard(0,0,Collections.emptySet(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),0,0);
		assertThat(validEdgeFixture.validMove(validEdgeFixture.getPlayer(), direction)).isFalse();
    }
    
    @Test
    void shouldMovePlayer() {
    	fixture = createTestBoard(10,10,Collections.emptySet(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),1,1);
    	fixture.movePlayer(Direction.RIGHT);
		assertThat(fixture.getPlayer().getPosition().getX()).isEqualTo(2);
		assertThat(fixture.getPlayer().getPosition().getY()).isEqualTo(1);
		fixture.movePlayer(Direction.LEFT);
		assertThat(fixture.getPlayer().getPosition().getX()).isEqualTo(1);
		assertThat(fixture.getPlayer().getPosition().getY()).isEqualTo(1);
		fixture.movePlayer(Direction.UP);
		assertThat(fixture.getPlayer().getPosition().getX()).isEqualTo(1);
		assertThat(fixture.getPlayer().getPosition().getY()).isEqualTo(0);
		fixture.movePlayer(Direction.DOWN);
		assertThat(fixture.getPlayer().getPosition().getX()).isEqualTo(1);
		assertThat(fixture.getPlayer().getPosition().getY()).isEqualTo(1);
    }
    
    @Test
    void shouldMoveEnemies() {
    	//One enemy 
    	fixture = createTestBoard(10,10,Collections.emptySet(),oneEnemyTest,Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),0,0);
    	fixture.moveEnemies();
    	List<Enemy> enemyListTest1 = fixture.getEnemies();
    	assertThat(enemyListTest1.get(0).getPosition().getX()).isEqualTo(10);
    	assertThat(enemyListTest1.get(0).getPosition().getY()).isEqualTo(9);
    	
    	//Many enemy
    	fixture = createTestBoard(10,10,Collections.emptySet(),manyEnemiesTest,Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),0,0);
    	fixture.moveEnemies();
    	List<Enemy> enemyListTest2 = fixture.getEnemies();
    	assertThat(enemyListTest2.get(0).getPosition().getX()).isEqualTo(10);
    	assertThat(enemyListTest2.get(0).getPosition().getY()).isEqualTo(9);
    	assertThat(enemyListTest2.get(1).getPosition().getX()).isEqualTo(9);
    	assertThat(enemyListTest2.get(1).getPosition().getY()).isEqualTo(8);
    	
    }
    
    @Test
    void shouldGenerateManhattanDistancesForUpAndDown() {
    	Enemy oneEnemy = animateFactory.makeEnemy(5, 5);
    	
    	Map<Integer, Direction> expected = Map.of(9, Direction.UP, 11, Direction.DOWN);
    	
    	fixture = createTestBoard(10,10,Collections.emptySet(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),0,0);
    	
    	Map<Integer, Direction> actual = fixture.generateManhattanDistances(oneEnemy);
    	
    	assertThat(actual).isEqualTo(expected);
    	
    }
    
    @Test
    void shouldGenerateManhattanDistancesForRightAndLeft() {
    	Enemy oneEnemy = animateFactory.makeEnemy(5, 5);
    	barriersTest = Set.of(Position.builder()
                .x(5)
                .y(6)
                .build(),
                Position.builder()
                .x(5)
                .y(4)
                .build());
    	
    	Map<Integer, Direction> expected = Map.of(9, Direction.LEFT, 11, Direction.RIGHT);
    	
    	fixture = createTestBoard(10,10,barriersTest,Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),0,0);
    	
    	Map<Integer, Direction> actual = fixture.generateManhattanDistances(oneEnemy);
    	
    	assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void shouldGenerateManhattanDistancesNoDirection() {
    	Enemy oneEnemy = animateFactory.makeEnemy(5, 5);
    	barriersTest = Set.of(Position.builder()
                .x(5)
                .y(6)
                .build(),
                Position.builder()
                .x(5)
                .y(4)
                .build(),
                Position.builder()
                .x(4)
                .y(5)
                .build(),
                Position.builder()
                .x(6)
                .y(5)
                .build());
    	
    	Map<Integer, Direction> expected = Map.of();
    	
    	fixture = createTestBoard(10,10,barriersTest,Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),0,0);
    	
    	Map<Integer, Direction> actual = fixture.generateManhattanDistances(oneEnemy);
    	
    	assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void checkIfPlayerWon() {
    	
    }
    
    private Board createTestBoard() {
        return createTestBoard(10,10,Collections.emptySet()
        					  ,Collections.emptyList()
        					  ,Collections.emptyList()
        					  ,Collections.emptyList()
        					  ,Collections.emptyList()
        					  ,0,0);
    }

    private Board createTestBoard(int width, int height, Set<Position> barriers
    											, List<Enemy> enemies
    											, List<RegularReward> objectives 
    											, List<Punishment> punishments
    											, List<BonusReward> bonus, int x, int y) {
        return Board.builder()
                    .width(width)
                    .height(height)
                    .startSpace(Position.builder()
                                        .x(0)
                                        .y(0)
                                        .build())
                    .endSpace(Position.builder()
                                      .x(width)
                                      .y(height)
                                      .build())
                    .barriers(barriers)
                    .player(animateFactory.makePlayer(x,
                                                      y))
                    .enemies(enemies)
                    .objectives(objectives)
                    .punishments(punishments)
                    .bonus(bonus)
                    .build();
    }
}