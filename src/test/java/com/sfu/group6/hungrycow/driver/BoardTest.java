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
    private List<Enemy> oneEnemyTest, manyEnemiesTest;
    
    @BeforeEach
    void setup() {
    	barriersTest = new HashSet<>();
        oneEnemyTest = new ArrayList<>();
        manyEnemiesTest = new ArrayList<>();
        List<RegularReward> objectives = new ArrayList<>();
        List<BonusReward> bonusRewards = new ArrayList<>();
        List<Punishment> punishments = new ArrayList<>();
    	barriersTest.add(Position.builder()
                 .x(0)
                 .y(1)
                 .build());
    	barriersTest.add(Position.builder()
                 .x(2)
                 .y(1)
                 .build());
    	barriersTest.add(Position.builder()
                 .x(1)
                 .y(0)
                 .build());
    	barriersTest.add(Position.builder()
                 .x(1)
                 .y(2)
                 .build());
    	oneEnemyTest.add(
                animateFactory.makeEnemy(10,
                                         10));
    	manyEnemiesTest.add(
                animateFactory.makeEnemy(10,
                        10));
    	manyEnemiesTest.add(
                animateFactory.makeEnemy(9,
                        9));
    	
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
    
    @ParameterizedTest
    @EnumSource(Direction.class)
	void shouldCheckForValidMovePlayer(Direction direction) {
    	
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
    
    void shouldCheckForValidMoveEnemy(Direction direction) {
    	//Test barriers
		fixture = createTestBoard(10,10,barriersTest,Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),1,1);
    	List<Enemy> enemyListTest = fixture.getEnemies();
		assertThat(fixture.validMove(enemyListTest.get(0), direction)).isFalse();
		
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
    	for (var enemy : fixture.getEnemies()) {
    		assertThat(enemy.getPosition().getX()).isEqualTo(10);
    		assertThat(enemy.getPosition().getY()).isEqualTo(9);
    	};
    	
    	//Many enemy
    	fixture = createTestBoard(10,10,Collections.emptySet(),manyEnemiesTest,Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),0,0);
    	fixture.moveEnemies();
    	List<Enemy> enemyListTest = fixture.getEnemies();
    	assertThat(enemyListTest.get(0).getPosition().getX()).isEqualTo(10);
    	assertThat(enemyListTest.get(0).getPosition().getY()).isEqualTo(9);
    	assertThat(enemyListTest.get(1).getPosition().getX()).isEqualTo(9);
    	assertThat(enemyListTest.get(1).getPosition().getY()).isEqualTo(8);
    	
    	//No enemy (Note doesn't really work since we can't get anything since no enemy exist)
	//    	fixture = createTestBoard(10,10,Collections.emptySet(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),0,0);
	//    	fixture.moveEnemies();
	//    	List<Enemy> enemyListTest1 = fixture.getEnemies();
	//    	assertThat(enemyListTest1.get(0).getPosition().getX()).isEqualTo(null);
	//    	assertThat(enemyListTest1.get(0).getPosition().getY()).isEqualTo(null);
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