package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.Player;

import java.awt.*;
import java.io.IOException;
import java.util.Set;

public class BoardFactory {

    public void createBoard(BoardUI ui, Graphics2D g) throws IOException {
        BoardDataLoader loader = new BoardDataLoader(ui);

        loader.loadBoard(getRandomMapFilePath());
        loader.drawTile(g);
    }

    public void createPlayer(Player.PlayerBuilder<?, ?> playerBuilder) {
        Player player = playerBuilder.build();
    }

    public void createBarrier(Set<Position> barriers) {

    }

    private String getRandomMapFilePath() {
        String filePath;
//        int randNum = RandomUtils.nextInt(1, 6);
        int randNum = 1;
        return "/maps/map" +
                randNum +
                ".txt";
    }

}
