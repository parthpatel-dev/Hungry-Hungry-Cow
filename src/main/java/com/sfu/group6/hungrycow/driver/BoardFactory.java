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
    private static final int BACKGROUND = 0;
    private static final int BARRIER_A = 1;
    private static final int BARRIER_B = 2;
    private static final int PLAYER = 3;
    private static final int ENEMY = 4;

    public Board createBoard(int[][] boardData, HungryCowAnimateFactory animateFactory, HungryCowAnimateFactory inanimateFactory) {
        Set<Position> barriers = new HashSet<>();
        Player player = null;
        List<Enemy> enemies = new ArrayList<>();
        List<RegularReward> objectives = new ArrayList<>();
        List<BonusReward> bonusRewards = new ArrayList<>();
        List<Punishment> punishments = new ArrayList<>();

        for (int y = 0; y < boardData.length; y++) {
            for (int x = 0; x < boardData[y].length; x++) {
                int entityType = boardData[y][x];

                switch (entityType) {
                    case BARRIER_A -> {
                        barriers.add(Position.builder().x(x).y(y).build());
                    }
                    case BARRIER_B -> {
                        barriers.add(Position.builder().x(x).y(y).build());
                    }
                    case PLAYER -> {
                        player = Player.builder().position(Position.builder().x(x).y(y).build()).build();
                    }
                }
            }
        }

        return Board.builder().barriers(barriers).player(Objects.requireNonNull(player)).enemies(enemies).objectives(objectives).build();
    }
}