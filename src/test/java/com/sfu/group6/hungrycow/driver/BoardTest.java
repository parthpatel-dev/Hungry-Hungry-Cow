package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.factory.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.factory.HungryCowInanimateFactory;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.withSettings;

public class BoardTest {
    private final HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
    private final HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
    private Board fixture;

    @ParameterizedTest
    @EnumSource(Direction.class)
    void shouldTickBoardStateForNormalSequence(Direction direction) {
    	 
    	Position barrierPosition = Position.builder()
                .x(4)
                .y(5)
                .build();
    	
    	Position barriersTest1 = Position.builder()
                .x(9)
                .y(10)
                .build();
    	
    	Position barriersTest2 = Position.builder()
                .x(10)
                .y(9)
                .build();
    	
    	List<Enemy> oneEnemyTest = List.of(animateFactory.makeEnemy(10,
                10));
    	
    	List<RegularReward> singleObjectivesTest = new ArrayList<>();
        singleObjectivesTest.add(inanimateFactory.makeRegularReward(0,
                                                                    8));
    	
        List<Punishment> singlePunishmentTest = new ArrayList<>();
        singlePunishmentTest.add(inanimateFactory.makePunishment(0,
                                                                 7));
    	
        List<BonusReward> testBonusRewards = List.of(inanimateFactory.makeBonusReward(3,
                3));
        
         Random random = Mockito.mock(Random.class,
                 withSettings().withoutAnnotations());
         
         fixture = createTestBoard(10,
                 10,
                 Set.of(barrierPosition, barriersTest1, barriersTest2),
                 oneEnemyTest,
                 singleObjectivesTest,
                 singlePunishmentTest,
                 testBonusRewards,
                 0,
                 0);
         
		
		fixture.setMockRandom(random);
		
		// Tick the board state right before new positions are generated
		for (int i = 0; i < Board.BONUS_REWARD_RANDOM_PERIOD - 1; i++) {
		// Alternate between RIGHT and LEFT move for the player to wait for new position
		Direction directions = (i % 2 == 0) ? Direction.RIGHT : Direction.LEFT;
		fixture.tickBoardState(directions);
		}
		
		Position expectedNewPosition = Position.builder()
		                           .x(6)
		                           .y(7)
		                           .build();
		
		given(random.nextInt(fixture.getWidth())).willReturn(barrierPosition.getX(),
		                                         barrierPosition.getY(),
		                                         expectedNewPosition.getX(),
		                                         expectedNewPosition.getY());
		
		fixture.tickBoardState(Direction.LEFT);
		
		then(random).should(times(4))
		.nextInt(fixture.getWidth());
		assertThat(fixture.getBonus()
		      .get(0)
		      .getPosition()).isEqualTo(expectedNewPosition);
		
       	assertThat(fixture.getPlayer()
                    .getPosition()
                    .getX()).isEqualTo(0);
        assertThat(fixture.getPlayer()
                    .getPosition()
                    .getY()).isEqualTo(0);
        assertThat(fixture.checkIfPlayerEncounterEnemy()).isFalse();
        assertThat(fixture.isGameOver()).isFalse();
        assertThat(fixture.checkIfPlayerWon()).isFalse();
        assertThat(fixture.isPlayerWin()).isFalse();
        List<Enemy> enemyListTest1 = fixture.getEnemies();
        assertThat(enemyListTest1.get(0)
                                 .getPosition()
                                 .getX()).isEqualTo(10);
        assertThat(enemyListTest1.get(0)
                                 .getPosition()
                                 .getY()).isEqualTo(10);
        
        assertThat(fixture.getObjectives()).isNotEmpty();
        assertThat(fixture.getBonus()).isNotEmpty();
        int oldScore = fixture.getPlayer().getScore();
        assertThat(fixture.getPunishments()).isNotEmpty();
        assertThat(fixture.getPlayer().getScore()).isEqualTo(oldScore);
        assertThat(fixture.getTickCounter()).isEqualTo(10);
    }
    
    @Test
    void shouldTickBoardStateForEdgeCase1() {
    	//Does the player collect the objective when a player touches an enemy (expected result is player does not collect objective)

    	List<Enemy> oneEnemyTest = List.of(animateFactory.makeEnemy(2,
               0));
    	
    	List<RegularReward> singleObjectivesTest = new ArrayList<>();
        singleObjectivesTest.add(inanimateFactory.makeRegularReward(1,
                                                                    0));
        fixture = createTestBoard(10,
                10,
                Collections.emptySet(),
                oneEnemyTest,
                singleObjectivesTest,
                Collections.emptyList(),
                Collections.emptyList(),
                0,
                0);
         
        fixture.tickBoardState(Direction.LEFT);
        fixture.tickBoardState(Direction.RIGHT);
        assertThat(fixture.checkIfPlayerEncounterEnemy()).isTrue();
        assertThat(fixture.isGameOver()).isTrue();
        assertThat(fixture.checkIfPlayerWon()).isFalse();
        assertThat(fixture.isPlayerWin()).isFalse();
        assertThat(fixture.getObjectives()).isNotEmpty();
        assertThat(fixture.getPunishments()).isEmpty();
        assertThat(fixture.getPlayer().getScore()).isEqualTo(0);
        assertThat(fixture.getTickCounter()).isEqualTo(2);
        assertThat(fixture.getBonus()).isEmpty();
    }
    
    @Test
    void shouldTickBoardStateForEdgeCase2() {
    	//What happens when both a punishment and a bonus is collected (expected result is score is 20 - 10 = 10)

        List<Punishment> singlePunishmentTest = new ArrayList<>();
        singlePunishmentTest.add(inanimateFactory.makePunishment(1,
                                                                 0));
        List<BonusReward> testBonusRewards = new ArrayList<>();
        testBonusRewards.add(inanimateFactory.makeBonusReward(1,
                0));
        
        fixture = createTestBoard(10,
                10,
                Collections.emptySet(),
                Collections.emptyList(),
                Collections.emptyList(),
                singlePunishmentTest,
                testBonusRewards,
                0,
                0);
         
        fixture.tickBoardState(Direction.RIGHT);
        assertThat(fixture.checkIfPlayerEncounterEnemy()).isFalse();
        assertThat(fixture.isGameOver()).isFalse();
        assertThat(fixture.checkIfPlayerWon()).isFalse();
        assertThat(fixture.isPlayerWin()).isFalse();
        assertThat(fixture.getObjectives()).isEmpty();
        assertThat(fixture.getPunishments()).isNotEmpty();
        assertThat(fixture.getPlayer().getScore()).isEqualTo(10);
        assertThat(fixture.getTickCounter()).isEqualTo(1);
        assertThat(fixture.getBonus()).isEmpty();
    }
    
    @Test
    void shouldTickBoardStateForEdgeCase3() {
    	//What happens when a player touches openedExitGate with an enemy (expected result player losses)
    	
    	List<Enemy> oneEnemyTest = List.of(animateFactory.makeEnemy(10,
                9));

        fixture = createTestBoard(10,
                10,
                Collections.emptySet(),
                oneEnemyTest,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                9,
                10);
         
        fixture.tickBoardState(Direction.RIGHT);
        assertThat(fixture.checkIfPlayerEncounterEnemy()).isTrue();
        assertThat(fixture.isGameOver()).isTrue();
        assertThat(fixture.isPlayerWin()).isFalse();
        assertThat(fixture.getObjectives()).isEmpty();
        assertThat(fixture.getPunishments()).isEmpty();
        assertThat(fixture.getPlayer().getScore()).isEqualTo(0);
        assertThat(fixture.getTickCounter()).isEqualTo(1);
        assertThat(fixture.getBonus()).isEmpty();
    }
    
    @Test
    void shouldTickBoardStateForEdgeCase4() {
    	//What happens when both a enemy and a bonus is collected (expected result is player losses)

    	List<Enemy> oneEnemyTest = List.of(animateFactory.makeEnemy(2,
               0));
    	
    	List<BonusReward> testBonusRewards = new ArrayList<>();
        testBonusRewards.add(inanimateFactory.makeBonusReward(1,
                0));
        
        fixture = createTestBoard(10,
                10,
                Collections.emptySet(),
                oneEnemyTest,
                Collections.emptyList(),
                Collections.emptyList(),
                testBonusRewards,
                0,
                0);
         
        fixture.tickBoardState(Direction.LEFT);
        fixture.tickBoardState(Direction.RIGHT);
        assertThat(fixture.checkIfPlayerEncounterEnemy()).isTrue();
        assertThat(fixture.isGameOver()).isTrue();
        assertThat(fixture.checkIfPlayerWon()).isFalse();
        assertThat(fixture.isPlayerWin()).isFalse();
        assertThat(fixture.getObjectives()).isEmpty();
        assertThat(fixture.getPunishments()).isEmpty();
        assertThat(fixture.getPlayer().getScore()).isEqualTo(0);
        assertThat(fixture.getTickCounter()).isEqualTo(2);
        assertThat(fixture.getBonus()).isNotEmpty();
    }
    
    @Test
    void shouldTickBoardStateForEdgeCase5() {
    	//What happens when closedExitGate is reached by player (expected result is player moves to position and nothing happens)

    	List<Enemy> oneEnemyTest = List.of(animateFactory.makeEnemy(10,
                9));

    	List<RegularReward> singleObjectivesTest = new ArrayList<>();
        singleObjectivesTest.add(inanimateFactory.makeRegularReward(1,
                                                                    0));
    	
        fixture = createTestBoard(10,
                10,
                Collections.emptySet(),
                oneEnemyTest,
                singleObjectivesTest,
                Collections.emptyList(),
                Collections.emptyList(),
                9,
                10);
         
        fixture.tickBoardState(Direction.RIGHT);
        assertThat(fixture.checkIfPlayerEncounterEnemy()).isTrue();
        assertThat(fixture.isGameOver()).isTrue();
        assertThat(fixture.checkIfPlayerWon()).isFalse();
        assertThat(fixture.isPlayerWin()).isFalse();
        assertThat(fixture.getObjectives()).isNotEmpty();
        assertThat(fixture.getPunishments()).isEmpty();
        assertThat(fixture.getPlayer().getScore()).isEqualTo(0);
        assertThat(fixture.getTickCounter()).isEqualTo(1);
        assertThat(fixture.getBonus()).isEmpty();
    }

    @Test
    void shouldUseDefaultsForNewBoardInstance() {
        fixture = createTestBoard();

        assertThat(fixture.isGameOver()).isFalse();
        assertThat(fixture.isPlayerWin()).isFalse();
        assertThat(fixture.getTickCounter()).isZero();
        assertThat(fixture.getRandom()).isInstanceOf(Random.class);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void shouldIncrementTickCounterForEachDirectionInput(Direction direction) {
        fixture = createTestBoard();

        fixture.tickBoardState(direction);
        assertThat(fixture.getTickCounter()).isEqualTo(1);
    }

    // Using Player to test Abstract animate for validMove
    @ParameterizedTest
    @EnumSource(Direction.class)
    void shouldCheckForValidMoveAbstractAnimate(Direction direction) {
        Set<Position> barriersTest = Set.of(Position.builder()
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
        // Test barriers
        fixture = createTestBoard(10,
                                  10,
                                  barriersTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  1,
                                  1);

        assertThat(fixture.validMove(fixture.getPlayer(),
                                     direction)).isFalse();

        // Test if returns true
        Board newFixture = createTestBoard(10,
                                           10,
                                           Collections.emptySet(),
                                           Collections.emptyList(),
                                           Collections.emptyList(),
                                           Collections.emptyList(),
                                           Collections.emptyList(),
                                           1,
                                           1);
        assertThat(newFixture.validMove(newFixture.getPlayer(),
                                        direction)).isTrue();

        //Test if edge of map is reached
        Board validEdgeFixture = createTestBoard(0,
                                                 0,
                                                 Collections.emptySet(),
                                                 Collections.emptyList(),
                                                 Collections.emptyList(),
                                                 Collections.emptyList(),
                                                 Collections.emptyList(),
                                                 0,
                                                 0);
        assertThat(validEdgeFixture.validMove(validEdgeFixture.getPlayer(),
                                              direction)).isFalse();
    }

    @Test
    void shouldMovePlayer() {
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  1,
                                  1);
        fixture.movePlayer(Direction.RIGHT);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getX()).isEqualTo(2);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getY()).isEqualTo(1);
        fixture.movePlayer(Direction.LEFT);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getX()).isEqualTo(1);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getY()).isEqualTo(1);
        fixture.movePlayer(Direction.UP);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getX()).isEqualTo(1);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getY()).isEqualTo(0);
        fixture.movePlayer(Direction.DOWN);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getX()).isEqualTo(1);
        assertThat(fixture.getPlayer()
                          .getPosition()
                          .getY()).isEqualTo(1);
    }

    @Test
    void shouldMoveEnemies() {
        List<Enemy> oneEnemyTest = List.of(animateFactory.makeEnemy(10,
                                                                    10));
        List<Enemy> manyEnemiesTest = List.of(animateFactory.makeEnemy(10,
                                                                       10),
                                              animateFactory.makeEnemy(9,
                                                                       9));
        // One enemy
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  oneEnemyTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);
        fixture.moveEnemies();
        List<Enemy> enemyListTest1 = fixture.getEnemies();
        assertThat(enemyListTest1.get(0)
                                 .getPosition()
                                 .getX()).isEqualTo(10);
        assertThat(enemyListTest1.get(0)
                                 .getPosition()
                                 .getY()).isEqualTo(9);

        // Many enemy
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  manyEnemiesTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);
        fixture.moveEnemies();
        List<Enemy> enemyListTest2 = fixture.getEnemies();
        assertThat(enemyListTest2.get(0)
                                 .getPosition()
                                 .getX()).isEqualTo(10);
        assertThat(enemyListTest2.get(0)
                                 .getPosition()
                                 .getY()).isEqualTo(9);
        assertThat(enemyListTest2.get(1)
                                 .getPosition()
                                 .getX()).isEqualTo(9);
        assertThat(enemyListTest2.get(1)
                                 .getPosition()
                                 .getY()).isEqualTo(8);

    }

    @Test
    void shouldGenerateManhattanDistancesForUpAndDown() {
        Enemy oneEnemy = animateFactory.makeEnemy(5,
                                                  5);

        Map<Integer, Direction> expected = Map.of(9,
                                                  Direction.UP,
                                                  11,
                                                  Direction.DOWN);

        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);

        Map<Integer, Direction> actual = fixture.generateManhattanDistances(oneEnemy);

        assertThat(actual).isEqualTo(expected);

    }


    @Test
    void shouldGenerateManhattanDistancesForRightAndLeft() {
        Enemy oneEnemy = animateFactory.makeEnemy(5,
                                                  5);
        Set<Position> barriersTest = Set.of(Position.builder()
                                                    .x(5)
                                                    .y(6)
                                                    .build(),
                                            Position.builder()
                                                    .x(5)
                                                    .y(4)
                                                    .build());

        Map<Integer, Direction> expected = Map.of(9,
                                                  Direction.LEFT,
                                                  11,
                                                  Direction.RIGHT);

        fixture = createTestBoard(10,
                                  10,
                                  barriersTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);

        Map<Integer, Direction> actual = fixture.generateManhattanDistances(oneEnemy);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGenerateManhattanDistancesNoDirection() {
        Enemy oneEnemy = animateFactory.makeEnemy(5,
                                                  5);
        Set<Position> barriersTest = Set.of(Position.builder()
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

        fixture = createTestBoard(10,
                                  10,
                                  barriersTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);

        Map<Integer, Direction> actual = fixture.generateManhattanDistances(oneEnemy);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCheckIfPlayerWon() {
        // On endSpace and empty objective list
        fixture = createTestBoard(0,
                                  0,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);
        assertThat(fixture.checkIfPlayerWon()).isTrue();

        // Not on endSpace
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);
        assertThat(fixture.checkIfPlayerWon()).isFalse();

        // Not empty objective list
        List<RegularReward> objectives = List.of(inanimateFactory.makeRegularReward(1,
                                                                                    1));
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  objectives,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);
        assertThat(fixture.checkIfPlayerWon()).isFalse();
    }

    @Test
    void shouldPlayerScoreIsNegative() {
        fixture = createTestBoard(0,
                                  0,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);

        fixture.getPlayer()
               .setScore(-1);
        assertThat(fixture.playerScoreIsNegative()).isTrue();

        fixture.getPlayer()
               .setScore(1);
        assertThat(fixture.playerScoreIsNegative()).isFalse();
    }


    @Test
    void shouldCheckIfPlayerEncounterEnemy() {
        List<Enemy> singleEnemyTest = List.of(animateFactory.makeEnemy(10,
                                                                       10));
        List<Enemy> multipleEnemiesTest = List.of(animateFactory.makeEnemy(10,
                                                                           10),
                                                  animateFactory.makeEnemy(9,
                                                                           9));

        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  singleEnemyTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  10,
                                  9);
        fixture.moveEnemies();
        assertThat(fixture.checkIfPlayerEncounterEnemy()).isTrue();

        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  multipleEnemiesTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  10,
                                  9);
        fixture.moveEnemies();
        assertThat(fixture.checkIfPlayerEncounterEnemy()).isTrue();
    }

    @Test
    void shouldCollectObjectives() {
        List<RegularReward> noObjectivesTest = new ArrayList<>();
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  noObjectivesTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);
        fixture.movePlayer(Direction.DOWN);
        fixture.collectObjectives();
        assertThat(fixture.getObjectives()).isEmpty();

        List<RegularReward> singleObjectivesTest = new ArrayList<>();
        singleObjectivesTest.add(inanimateFactory.makeRegularReward(0,
                                                                    1));
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  singleObjectivesTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);
        fixture.movePlayer(Direction.DOWN);
        fixture.collectObjectives();
        assertThat(fixture.getObjectives()).isEmpty();


        List<RegularReward> multipleObjectivesTest = new ArrayList<>();
        multipleObjectivesTest.add(inanimateFactory.makeRegularReward(0,
                                                                      1));
        multipleObjectivesTest.add(inanimateFactory.makeRegularReward(1,
                                                                      1));
        multipleObjectivesTest.add(inanimateFactory.makeRegularReward(1,
                                                                      0));
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  multipleObjectivesTest,
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  0,
                                  0);
        fixture.movePlayer(Direction.DOWN);
        fixture.collectObjectives();
        fixture.movePlayer(Direction.RIGHT);
        fixture.collectObjectives();
        fixture.movePlayer(Direction.UP);
        fixture.collectObjectives();
        assertThat(fixture.getObjectives()).isEmpty();
    }

    @Test
    void shouldCollectBonusRewards() {
        List<BonusReward> noBonusReward = new ArrayList<>();
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  noBonusReward,
                                  0,
                                  0);
        fixture.movePlayer(Direction.DOWN);
        fixture.collectObjectives();
        assertThat(fixture.getBonus()).isEmpty();

        List<BonusReward> singleBonusReward = new ArrayList<>();
        singleBonusReward.add(inanimateFactory.makeBonusReward(0,
                                                               1));
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  singleBonusReward,
                                  0,
                                  0);
        fixture.movePlayer(Direction.DOWN);
        fixture.collectBonusRewards();
        assertThat(fixture.getBonus()).isEmpty();

        List<BonusReward> multipleBonusReward = new ArrayList<>();
        multipleBonusReward.add(inanimateFactory.makeBonusReward(0,
                                                                 1));
        multipleBonusReward.add(inanimateFactory.makeBonusReward(1,
                                                                 1));
        multipleBonusReward.add(inanimateFactory.makeBonusReward(1,
                                                                 0));
        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  multipleBonusReward,
                                  0,
                                  0);
        fixture.movePlayer(Direction.DOWN);
        fixture.collectBonusRewards();
        fixture.movePlayer(Direction.RIGHT);
        fixture.collectBonusRewards();
        fixture.movePlayer(Direction.UP);
        fixture.collectBonusRewards();
        assertThat(fixture.getBonus()).isEmpty();
    }

    @Test
    void shouldCollectPunishments() {
    	  List<Punishment> noPunishmentTest = new ArrayList<>();
          fixture = createTestBoard(10,
                                    10,
                                    Collections.emptySet(),
                                    Collections.emptyList(),
                                    Collections.emptyList(),
                                    noPunishmentTest,
                                    Collections.emptyList(),
                                    0,
                                    0);

          int currentPlayerScoreForNoPunishmentTest = fixture.getPlayer().getScore();
          fixture.movePlayer(Direction.DOWN);
          fixture.collectPunishments();
          int newPlayerScoreForNoPunishmentTest = fixture.getPlayer().getScore();
          assertThat(newPlayerScoreForNoPunishmentTest).isEqualTo(currentPlayerScoreForNoPunishmentTest);

          List<Punishment> singlePunishmentTest = new ArrayList<>();
          singlePunishmentTest.add(inanimateFactory.makePunishment(0,
                                                                   1));
          fixture = createTestBoard(10,
                                    10,
                                    Collections.emptySet(),
                                    Collections.emptyList(),
                                    Collections.emptyList(),
                                    singlePunishmentTest,
                                    Collections.emptyList(),
                                    0,
                                    0);

          int currentPlayerScoreForSinglePunishmentTest = fixture.getPlayer().getScore();
          fixture.movePlayer(Direction.DOWN);
          fixture.collectPunishments();
          int newPlayerScoreForSinglePunishmentTest = fixture.getPlayer().getScore();
          assertThat(newPlayerScoreForSinglePunishmentTest).isEqualTo(currentPlayerScoreForSinglePunishmentTest - 10);

          List<Punishment> multiplePunishmentTest = new ArrayList<>();
          multiplePunishmentTest.add(inanimateFactory.makePunishment(0,
                                                                     1));
          multiplePunishmentTest.add(inanimateFactory.makePunishment(1,
                                                                     1));
          multiplePunishmentTest.add(inanimateFactory.makePunishment(1,
                                                                     0));
          fixture = createTestBoard(10,
                                    10,
                                    Collections.emptySet(),
                                    Collections.emptyList(),
                                    Collections.emptyList(),
                                    multiplePunishmentTest,
                                    Collections.emptyList(),
                                    0,
                                    0);

          int currentPlayerScoreForMultiplePunishmentTest = fixture.getPlayer().getScore();
          fixture.movePlayer(Direction.DOWN);
          fixture.collectPunishments();
          fixture.movePlayer(Direction.RIGHT);
          fixture.collectPunishments();
          fixture.movePlayer(Direction.UP);
          fixture.collectPunishments();
          int newPlayerScoreForMultiplePunishmentTest = fixture.getPlayer().getScore();
          assertThat(newPlayerScoreForMultiplePunishmentTest).isEqualTo(currentPlayerScoreForMultiplePunishmentTest - 30);
    }

    @Test
    void shouldRandomizeAllBonusRewardsAfterBonusRewardRandomPeriod() {
        // A known issue for Mockito requires instantiating the mock without annotations for JDK 17+
        // https://github.com/mockito/mockito/issues/2560
        Random random = Mockito.mock(Random.class,
                                     withSettings().withoutAnnotations());

        List<BonusReward> testBonusRewards = List.of(inanimateFactory.makeBonusReward(2,
                                                                                      2),
                                                     inanimateFactory.makeBonusReward(3,
                                                                                      3));

        fixture = createTestBoard(10,
                                  10,
                                  Collections.emptySet(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  testBonusRewards,
                                  0,
                                  0);

        fixture.setMockRandom(random);

        // Tick the board state right before new positions are generated
        for (int i = 0; i < Board.BONUS_REWARD_RANDOM_PERIOD - 1; i++) {
            // Alternate between RIGHT and LEFT move for the player to wait for new position
            Direction direction = (i % 2 == 0) ? Direction.RIGHT : Direction.LEFT;
            fixture.tickBoardState(direction);
        }

        Position bonusReward1NewPosition = Position.builder()
                                                   .x(4)
                                                   .y(5)
                                                   .build();
        Position bonusReward2NewPosition = Position.builder()
                                                   .x(8)
                                                   .y(8)
                                                   .build();
        given(random.nextInt(fixture.getWidth())).willReturn(bonusReward1NewPosition.getX(),
                                                             bonusReward1NewPosition.getY(),
                                                             bonusReward2NewPosition.getX(),
                                                             bonusReward2NewPosition.getY());

        fixture.tickBoardState(Direction.LEFT);

        assertThat(fixture.getBonus()
                          .get(0)
                          .getPosition()).isEqualTo(bonusReward1NewPosition);
        assertThat(fixture.getBonus()
                          .get(1)
                          .getPosition()).isEqualTo(bonusReward2NewPosition);
    }

    @Test
    void shouldRandomizeBonusRewardUntilNotRandomizedOntoABarrier() {
        // A known issue for Mockito requires instantiating the mock without annotations for JDK 17+
        // https://github.com/mockito/mockito/issues/2560
        Random random = Mockito.mock(Random.class,
                                     withSettings().withoutAnnotations());

        List<BonusReward> testBonusRewards = List.of(inanimateFactory.makeBonusReward(3,
                                                                                      3));

        Position barrierPosition = Position.builder()
                                           .x(4)
                                           .y(5)
                                           .build();

        fixture = createTestBoard(10,
                                  10,
                                  Set.of(barrierPosition),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  Collections.emptyList(),
                                  testBonusRewards,
                                  0,
                                  0);

        fixture.setMockRandom(random);

        // Tick the board state right before new positions are generated
        for (int i = 0; i < Board.BONUS_REWARD_RANDOM_PERIOD - 1; i++) {
            // Alternate between RIGHT and LEFT move for the player to wait for new position
            Direction direction = (i % 2 == 0) ? Direction.RIGHT : Direction.LEFT;
            fixture.tickBoardState(direction);
        }

        Position expectedNewPosition = Position.builder()
                                               .x(6)
                                               .y(7)
                                               .build();

        given(random.nextInt(fixture.getWidth())).willReturn(barrierPosition.getX(),
                                                             barrierPosition.getY(),
                                                             expectedNewPosition.getX(),
                                                             expectedNewPosition.getY());

        fixture.tickBoardState(Direction.LEFT);

        then(random).should(times(4))
                    .nextInt(fixture.getWidth());
        assertThat(fixture.getBonus()
                          .get(0)
                          .getPosition()).isEqualTo(expectedNewPosition);

    }

    private Board createTestBoard() {
        return createTestBoard(10,
                               10,
                               Collections.emptySet(),
                               Collections.emptyList(),
                               Collections.emptyList(),
                               Collections.emptyList(),
                               Collections.emptyList(),
                               0,
                               0);
    }

    private Board createTestBoard(int width,
                                  int height,
                                  Set<Position> barriers,
                                  List<Enemy> enemies,
                                  List<RegularReward> objectives,
                                  List<Punishment> punishments,
                                  List<BonusReward> bonus,
                                  int x,
                                  int y) {
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