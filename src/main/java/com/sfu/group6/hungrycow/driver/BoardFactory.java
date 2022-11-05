package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import com.sfu.group6.hungrycow.model.animate.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.model.animate.Player;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;


import java.util.*;
import java.util.List;

public class BoardFactory {
    private static final int BACKGROUND = 0; // field
    private static final int PLAYER = 1; // cow
    private static final int ENEMY = 2; //
    private static final int BARRIER_A = 3; // tall tree
    private static final int BARRIER_B = 4; // circle tree
    private static final int BARRIER_C = 5; // dead tree
    private static final int BARRIER_D = 6; // rock
    private static final int BARRIER_E = 7; // pond
    private static final int BARRIER_F = 8; // fence_down
    private static final int BARRIER_G = 9; // fence_down_left_corner
    private static final int BARRIER_H = 10; // fence_down_right_corner
    private static final int BARRIER_I = 11; // fence_up_left_corner
    private static final int BARRIER_J = 12; // fence_up_right_corner

    private static final int OBJECTIVES = 13; // mama_sheep
    private static final int BONUS_REWARD = 14; // baby_sheep
    private static final int PUNISHMENT = 15; // mama_chicken
    private static final int START_SPACE = 16;
    private static final int END_SPACE = 17;

    public static Board createBoard(int[][] boardData,
                                    HungryCowAnimateFactory animateFactory,
                                    HungryCowAnimateFactory inanimateFactory) {

        Position startSpace = null;
        Position endSpace = null;
        Player player = null;
        Set<Position> barriers = new HashSet<>();
        List<Enemy> enemies = new ArrayList<>();
        List<RegularReward> objectives = new ArrayList<>();
        List<BonusReward> bonusRewards = new ArrayList<>();
        List<Punishment> punishments = new ArrayList<>();

        for (int y = 0; y < boardData.length; y++) {
            for (int x = 0; x < boardData[y].length; x++) {
                int entityType = boardData[y][x];

                switch (entityType) {
                    case PLAYER -> {
                        player = Player.builder().position(
                                Position.builder().x(x).y(y).build()
                        ).build();
                    }
                    case ENEMY -> {
                        enemies.add(
                                Enemy.builder().position(
                                        Position.builder().x(x).y(y).build()
                                ).build()
                        );
                    }
                    case BARRIER_A, BARRIER_B, BARRIER_C,
                            BARRIER_D, BARRIER_E, BARRIER_F,
                            BARRIER_G, BARRIER_H, BARRIER_I,
                            BARRIER_J-> {
                        barriers.add(Position.builder().x(x).y(y).build());
                    }
                    case OBJECTIVES -> {
                        objectives.add(
                                RegularReward.builder().position(
                                        Position.builder().x(x).y(y).build()
                                ).build()
                        );
                    }
                    case BONUS_REWARD -> {
                        bonusRewards.add(
                                BonusReward.builder().position(
                                        Position.builder().x(x).y(y).build()
                                ).build()
                        );
                    }
                    case PUNISHMENT -> {
                        punishments.add(
                                Punishment.builder().position(
                                        Position.builder().x(x).y(y).build()
                                ).build()
                        );
                    }
                    case START_SPACE -> {
                        startSpace = Position.builder().x(x).y(y).build();
                    }
                    case END_SPACE -> {
                        endSpace = Position.builder().x(x).y(y).build();
                    }
                }
            }
        }

        return Board
                .builder()
                .startSpace(startSpace)
                .endSpace(endSpace)
                .barriers(barriers)
                .player(Objects.requireNonNull(player))
                .enemies(enemies)
                .objectives(objectives)
                .build();
    }
}