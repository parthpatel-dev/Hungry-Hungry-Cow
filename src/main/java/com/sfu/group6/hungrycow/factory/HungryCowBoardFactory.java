package com.sfu.group6.hungrycow.factory;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.maploader.MapLoader;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import com.sfu.group6.hungrycow.model.animate.Player;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The HungryCowBoardFactory creates a hungry cow themed board.
 * It takes in a 2 dimensional array of integers that is loaded from MapLoader {@link MapLoader}.
 * The Hungry Cow themed board consist of many integer constants that represent the type of space it is.
 */
public class HungryCowBoardFactory {
    public static final int COW = 1;
    public static final int FARMER = 2;
    public static final int TALL_TREE = 3;
    public static final int CIRCLE_TREE = 4;
    public static final int DEAD_TREE = 5;
    public static final int ROCK = 6;
    public static final int POND = 7;
    public static final int FENCE_DOWN = 8;
    public static final int FENCE_MIDDLE = 9;
    public static final int FENCE_DOWN_LEFT_CORNER = 10;
    public static final int FENCE_DOWN_RIGHT_CORNER = 11;
    public static final int FENCE_UP_LEFT_CORNER = 12;
    public static final int FENCE_UP_RIGHT_CORNER = 13;

    public static final int OBJECTIVES = 14;
    public static final int BONUS_REWARD = 15;
    public static final int PUNISHMENT = 16;
    public static final int START_SPACE = 17;
    public static final int END_SPACE = 18;

    /**
     * Creates a new Hungry Cow themed Board.
     *
     * @param boardData        a 2D array which consist Hungry Cow themed integers.
     * @param animateFactory   a HungryCowAnimateFactory
     * @param inanimateFactory a HungryCowInanimateFactory
     * @return a Hungry Cow themed Board.
     */
    public Board createBoard(int[][] boardData,
                             HungryCowAnimateFactory animateFactory,
                             HungryCowInanimateFactory inanimateFactory) {

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
                    case COW -> player = animateFactory.makePlayer(x,
                                                                   y);

                    case FARMER -> enemies.add(
                            animateFactory.makeEnemy(x,
                                                     y));

                    case TALL_TREE, CIRCLE_TREE, DEAD_TREE,
                            ROCK, POND, FENCE_DOWN, FENCE_MIDDLE,
                            FENCE_DOWN_LEFT_CORNER, FENCE_DOWN_RIGHT_CORNER, FENCE_UP_LEFT_CORNER,
                            FENCE_UP_RIGHT_CORNER -> barriers.add(Position.builder()
                                                                          .x(x)
                                                                          .y(y)
                                                                          .build());

                    case OBJECTIVES -> objectives.add(inanimateFactory.makeRegularReward(x,
                                                                                         y));

                    case BONUS_REWARD -> bonusRewards.add(inanimateFactory.makeBonusReward(x,
                                                                                           y));

                    case PUNISHMENT -> punishments.add(inanimateFactory.makePunishment(x,
                                                                                       y));

                    case START_SPACE -> startSpace = Position.builder()
                                                             .x(x)
                                                             .y(y)
                                                             .build();

                    case END_SPACE -> endSpace = Position.builder()
                                                         .x(x)
                                                         .y(y)
                                                         .build();
                }
            }
        }

        return Board
                .builder()
                .height(boardData.length)
                .width(boardData[0].length)
                .startSpace(Objects.requireNonNull(startSpace))
                .endSpace(Objects.requireNonNull(endSpace))
                .barriers(barriers)
                .player(Objects.requireNonNull(player))
                .enemies(enemies)
                .objectives(objectives)
                .bonus(bonusRewards)
                .punishments(punishments)
                .build();
    }
}