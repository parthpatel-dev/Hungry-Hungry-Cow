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

import java.util.HashSet;
import java.util.List;
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
    private final Player cow;
    private final List<Enemy> enemies;
    private final List<RegularReward> objectives;
    private final List<Punishment> punishments;
    private final List<BonusReward> bonus;

    public void tickBoardState(Direction input) {
        movePlayer(input);
        moveEnemies();
        if (isGameOver()) {
            this.gameOver = true;
        }
        collectRewards();
        collectPunishments();
        randomizeBonusRewards();
    }

    private void movePlayer(Direction input) {
        if (validMove(this.cow,
                      input)) {
            this.cow.move(input);
        } else {
            this.cow.move(Direction.NEUTRAL);
        }
    }

    private void moveEnemies() {
        // TODO: Implement enemy movement
    }

    private boolean isGameOver() {
        for (var enemy : this.enemies) {
            if (enemy.getPosition()
                     .equals(this.cow.getPosition())) {
                return true;
            }
        }
        return false;
    }

    private void collectRewards() {
        for (var reward : this.objectives) {
            if (reward.getPosition()
                      .equals(this.cow.getPosition())) {
                this.cow.rewardPlayer(reward);
            }
        }

        for (var bonus : this.bonus) {
            if (bonus.getPosition()
                     .equals(this.cow.getPosition())) {
                this.cow.rewardPlayer(bonus);
            }
        }
    }

    private void collectPunishments() {
        for (var punishment : this.punishments) {
            if (punishment.getPosition()
                          .equals(this.cow.getPosition())) {
                this.cow.punishPlayer(punishment);
            }
        }
    }

    private void randomizeBonusRewards() {
        // TODO: Implement randomization of bonus rewards
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
