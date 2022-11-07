package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.AbstractAnimate;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import com.sfu.group6.hungrycow.model.animate.Player;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Board class encapsulates the game logic of the game board.
 * The builder takes in the components of the game and creates an instance of class board.
 * Builder defaults should be used and not instantiated with another value.
 */
@Builder
@Getter
public class Board {
    @Builder.Default
    private boolean gameOver = false;
    @Builder.Default
    private boolean playerWin = false;
    @Builder.Default
    private int tickCounter = 0;

    private static final int BONUS_REWARD_RANDOM_PERIOD = 10;

    private final int width;
    private final int height;
    private final Position startSpace;
    private final Position endSpace;
    private final Set<Position> barriers;
    private final Player player;
    private final List<Enemy> enemies;
    private final List<RegularReward> objectives;
    private final List<Punishment> punishments;
    private final List<BonusReward> bonus;

    /**
     * Perform a tick in the board state.
     * In a tick, the board moves the player, move the enemies, collects objectives, collects punishments,
     * collects bonus rewards, and checks if the game is over.
     *
     * The game is over when one of the following happens:
     * 1. The player encounters an enemy
     * 2. The player's score is negative
     * 3. The player has won, i.e. the player collects all objectives and reaches the end position.
     *
     * @param input a Direction {@link Direction}
     */
    public void tickBoardState(Direction input) {
        incrementTickCounter();
        movePlayer(input);

        if (checkIfPlayerEncounterEnemy()) {
            this.gameOver = true;
            return;
        }

        moveEnemies();

        if (checkIfPlayerEncounterEnemy()) {
            this.gameOver = true;
            return;
        }

        collectObjectives();
        collectBonusRewards();
        collectPunishments();

        if (playerScoreIsNegative()) {
            this.gameOver = true;
            return;
        }

        randomizeBonusRewards();

        if (checkIfPlayerWon()) {
            this.playerWin = this.gameOver = true;
        }
    }

    private void incrementTickCounter() {
        if (this.tickCounter == Integer.MAX_VALUE) {
            this.tickCounter = 0;
        }
        this.tickCounter++;
    }

    private void movePlayer(Direction input) {
        if (validMove(this.player,
                      input)) {
            this.player.move(input);
        }
    }

    private void moveEnemies() {
        for (var enemy : enemies) {
            Map<Integer, Direction> distances = generateManhattanDistances(enemy);
            if (!distances.isEmpty()) {
                enemy.move(distances.get(Collections.min(distances.keySet())));
            }
        }
    }

    private Map<Integer, Direction> generateManhattanDistances(Enemy enemy) {
        Map<Integer, Direction> distances = new HashMap<>();

        if (validMove(enemy,
                      Direction.UP)) {
            Position moveUpPosition = Position.builder()
                                              .x(enemy.getPosition()
                                                      .getX())
                                              .y(enemy.getPosition()
                                                      .getY() - 1)
                                              .build();

            distances.putIfAbsent(calculateManhattanDistance(this.player.getPosition(),
                                                             moveUpPosition),
                                  Direction.UP);
        }

        if (validMove(enemy,
                      Direction.DOWN)) {
            Position moveUpPosition = Position.builder()
                                              .x(enemy.getPosition()
                                                      .getX())
                                              .y(enemy.getPosition()
                                                      .getY() + 1)
                                              .build();

            distances.putIfAbsent(calculateManhattanDistance(this.player.getPosition(),
                                                             moveUpPosition),
                                  Direction.DOWN);
        }

        if (validMove(enemy,
                      Direction.LEFT)) {
            Position moveUpPosition = Position.builder()
                                              .x(enemy.getPosition()
                                                      .getX() - 1)
                                              .y(enemy.getPosition()
                                                      .getY())
                                              .build();

            distances.putIfAbsent(calculateManhattanDistance(this.player.getPosition(),
                                                             moveUpPosition),
                                  Direction.LEFT);
        }

        if (validMove(enemy,
                      Direction.RIGHT)) {
            Position moveUpPosition = Position.builder()
                                              .x(enemy.getPosition()
                                                      .getX() + 1)
                                              .y(enemy.getPosition()
                                                      .getY())
                                              .build();

            distances.putIfAbsent(calculateManhattanDistance(this.player.getPosition(),
                                                             moveUpPosition),
                                  Direction.RIGHT);
        }
        return distances;
    }

    private int calculateManhattanDistance(Position playerPosition,
                                           Position enemyPosition) {
        return Math.abs(playerPosition.getX() - enemyPosition.getX()) + Math.abs(playerPosition.getY() -
                                                                                 enemyPosition.getY());
    }

    private boolean checkIfPlayerEncounterEnemy() {
        for (var enemy : this.enemies) {
            if (enemy.getPosition()
                     .equals(this.player.getPosition())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfPlayerWon() {
        return this.objectives.isEmpty() && this.player.getPosition()
                                                       .equals(this.endSpace);
    }

    private boolean playerScoreIsNegative() {
        return this.player.getScore() < 0;
    }

    private void collectObjectives() {
        for (Iterator<RegularReward> iterator = this.objectives.iterator(); iterator.hasNext(); ) {
            RegularReward reward = iterator.next();
            if (reward.getPosition()
                      .equals(this.player.getPosition())) {
                this.player.rewardPlayer(reward);
                iterator.remove();
            }
        }
    }

    private void collectBonusRewards() {
        for (Iterator<BonusReward> iterator = this.bonus.iterator(); iterator.hasNext(); ) {
            BonusReward bonus = iterator.next();
            if (bonus.getPosition()
                     .equals(this.player.getPosition())) {
                this.player.rewardPlayer(bonus);
                iterator.remove();
            }
        }
    }

    private void collectPunishments() {
        for (var punishment : this.punishments) {
            if (punishment.getPosition()
                          .equals(this.player.getPosition())) {
                this.player.punishPlayer(punishment);
            }
        }
    }

    private void randomizeBonusRewards() {
        if (tickCounter % BONUS_REWARD_RANDOM_PERIOD == 0) {
            for (var bonus : this.bonus) {
                Position newPosition = generateNewBonusRewardPosition();
                bonus.getPosition()
                     .setX(newPosition.getX());
                bonus.getPosition()
                     .setY(newPosition.getY());
            }
        }
    }

    private Position generateNewBonusRewardPosition() {
        Position newPosition = Position.builder()
                                       .x(RandomUtils.nextInt(0,
                                                              width))
                                       .y(RandomUtils.nextInt(0,
                                                              height))
                                       .build();

        while (this.barriers.contains(newPosition)) {
            newPosition.setX(RandomUtils.nextInt(0,
                                                 width));
            newPosition.setY(RandomUtils.nextInt(0,
                                                 height));
        }
        return newPosition;
    }

    private boolean validMove(AbstractAnimate animate,
                              Direction input) {
        Position movePosition = Position.builder()
                                        .x(animate.getPosition()
                                                  .getX())
                                        .y(animate.getPosition()
                                                  .getY())
                                        .build();
        switch (input) {
            case UP -> {
                movePosition.setY(movePosition.getY() - 1);
                if (movePosition.getY() < 0 || barriers.contains(movePosition)) {
                    return false;
                }
            }
            case DOWN -> {
                movePosition.setY(movePosition.getY() + 1);
                if (movePosition.getY() > this.height || barriers.contains(movePosition)) {
                    return false;
                }
            }
            case LEFT -> {
                movePosition.setX(movePosition.getX() - 1);
                if (movePosition.getX() < 0 || barriers.contains(movePosition)) {
                    return false;
                }
            }
            case RIGHT -> {
                movePosition.setX(movePosition.getX() + 1);
                if (movePosition.getX() > this.width || barriers.contains(movePosition)) {
                    return false;
                }
            }
        }
        return true;
    }
}
