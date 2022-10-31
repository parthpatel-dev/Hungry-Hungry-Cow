package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.Space;
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
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Builder
@Getter
public class Board {
    @Builder.Default
    private boolean gameOver = false;

    private final int dimension;
    private final Space startSpace;
    private final Space endSpace;
    private final Set<Position> barriers;
    private final Player player;
    private final List<Enemy> enemies;
    private final List<RegularReward> objectives;
    private final List<Punishment> punishments;
    private final List<BonusReward> bonus;

    public void tickBoardState(Direction input) {
        movePlayer(input);
        moveEnemies();
        if (isGameOver()) {
            this.gameOver = true;
            return;
        }
        collectRewards();
        collectPunishments();
        randomizeBonusRewards();
    }

    private void movePlayer(Direction input) {
        if (validMove(this.player,
                      input)) {
            this.player.move(input);
        } else {
            this.player.move(Direction.NEUTRAL);
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

    private boolean isGameOver() {
        for (var enemy : this.enemies) {
            if (enemy.getPosition()
                     .equals(this.player.getPosition())) {
                return true;
            }
        }
        return false;
    }

    private void collectRewards() {
        for (var reward : this.objectives) {
            if (reward.getPosition()
                      .equals(this.player.getPosition())) {
                this.player.rewardPlayer(reward);
            }
        }

        for (var bonus : this.bonus) {
            if (bonus.getPosition()
                     .equals(this.player.getPosition())) {
                this.player.rewardPlayer(bonus);
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
        for (var bonus : this.bonus) {
            Position newPosition = generateNewBonusRewardPosition();
            bonus.getPosition().setX(newPosition.getX());
            bonus.getPosition().setY(newPosition.getY());
        }
    }

    private Position generateNewBonusRewardPosition() {
        Position newPosition = Position.builder()
                                       .x(RandomUtils.nextInt(0,
                                                              dimension + 1))
                                       .y(RandomUtils.nextInt(0,
                                                              dimension + 1))
                                       .build();

        while (this.barriers.contains(newPosition)) {
            newPosition.setX(RandomUtils.nextInt(0, dimension + 1));
            newPosition.setY(RandomUtils.nextInt(0, dimension + 1));
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
                if (movePosition.getY() > this.dimension || barriers.contains(movePosition)) {
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
                if (movePosition.getX() > this.dimension || barriers.contains(movePosition)) {
                    return false;
                }
            }
        }
        return true;
    }
}
