package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.driver.tile.TileHandler;
import com.sfu.group6.hungrycow.model.animate.Player;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BoardDataLoader {

    int[][] boardData;
    BoardUI ui;

    TileHandler tileHandler;

    private static final int BACKGROUND = 0;
    private static final int BARRIER = 1;
    private static final int PLAYER = 3;
    private static final int ENEMY = 4;

    Set<Position> barriers = new HashSet<>();
//    ? ?
    Player.PlayerBuilder<?, ?> playerBuilder = Player.builder();

    BoardFactory boardFactory;

    public BoardDataLoader(BoardUI ui) throws IOException {
        this.ui = ui;
        boardData = new int[ui.numOfTilesHorizontal][ui.numOfTilesVertical];
        boardFactory = new BoardFactory();
        tileHandler = new TileHandler(this.ui);
    }

    public void loadBoard(String filePath) throws IOException {
        InputStream stream = getClass().getResourceAsStream(filePath);
        assert stream != null;
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(stream));

        for (int row = 0; row < ui.numOfTilesVertical; row++) {

            String line = bufferReader.readLine();
            String[] rowData = line.split(" ");

            for (int col = 0; col < ui.numOfTilesHorizontal; col++) {

                int tileData = Integer.parseInt(rowData[col]);
                boardData[col][row] = tileData;

                if (isBarrier(tileData)) {
                    Position barrierPosition = Position.builder()
                            .x(col)
                            .y(row)
                            .build();

                    barriers.add(barrierPosition);
                    boardFactory.createBarrier(barriers);
                }

                if(isPlayer(tileData)) {
                    Position playerPosition = Position.builder()
                            .x(col)
                            .y(row)
                            .build();

                    playerBuilder.position(playerPosition);
                    boardFactory.createPlayer(playerBuilder);
                }
            }
            bufferReader.close();
        }

    }

    public boolean isBarrier(int tileData) {
        if(tileData == 3 || tileData == 4 || tileData == 5
                || tileData == 6 || tileData == 7 || tileData = 8
                || tileData == 9 || tileData == 10 || tileData == 11
                || tileData == 12) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPlayer(int tileData) {
        if(tileData == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void drawTile(Graphics2D g) {
        drawGrass(g);

        for (int row = 0; row < ui.numOfTilesVertical; row++) {

            for (int col = 0; col < ui.numOfTilesHorizontal; col++) {

                int tileData = boardData[col][row];
                int tileSize = ui.tileSize;
                if(tileData == 2) {
                    tileSize *= 3;
                }
                int tilePositionX = col * ui.tileSize;
                int tilePositionY = row * ui.tileSize;
                g.drawImage(tileHandler.tiles[tileData].image, tilePositionX, tilePositionY, tileSize, tileSize, null);
            }
        }
    }

    public void drawGrass(Graphics2D g) {
        for (int i = 0; i < ui.numOfTilesHorizontal; i++) {
            int grassPositionX = i * ui.tileSize;
            for (int j = 0; j < ui.numOfTilesVertical; j++) {
                int grassPositionY = j * ui.tileSize;
                g.drawImage(tileHandler.tiles[0].image, grassPositionX, grassPositionY, ui.tileSize, ui.tileSize, null);
            }
        }
    }
}