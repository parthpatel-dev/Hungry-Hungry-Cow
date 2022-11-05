package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.driver.tile.TileHandler;

import java.awt.*;
import java.io.IOException;


public class DrawBoard {
    private static final int WIDTH = 0;
    private static final int HEIGHT = 0;
    private MapLoader mapLoader;
    BoardUI ui;

    TileHandler tileHandler;

    public DrawBoard(BoardUI ui) throws IOException {
        this.ui = ui;
        this.mapLoader = new MapLoader();
        tileHandler = new TileHandler(this.ui);
    }

    public void drawTile(Graphics2D g) throws IOException {
        int[][] boardData = this.mapLoader.loadBoard(getRandomMapFilePath(), WIDTH, HEIGHT);
        drawGrass(g);

        for (int row = 0; row < ui.numOfTilesVertical; row++) {

            for (int col = 0; col < ui.numOfTilesHorizontal; col++) {

                int tileData = boardData[col][row];
                int tileSize = ui.tileSize;
                int tilePositionX = col * ui.tileSize;
                int tilePositionY = row * ui.tileSize;
                if(tileData != 1 && tileData != 2 && tileData != 13
                        && tileData != 14 && tileData != 15
                        && tileData != 16 && tileData != 17) {
                    g.drawImage(tileHandler.tiles[tileData].image, tilePositionX, tilePositionY, tileSize, tileSize, null);
                }
            }
        }
    }

    private void drawGrass(Graphics2D g) {
        for (int i = 0; i < ui.numOfTilesHorizontal; i++) {
            int grassPositionX = i * ui.tileSize;
            for (int j = 0; j < ui.numOfTilesVertical; j++) {
                int grassPositionY = j * ui.tileSize;
                g.drawImage(tileHandler.tiles[0].image, grassPositionX, grassPositionY, ui.tileSize, ui.tileSize, null);
            }
        }
    }

    public String getRandomMapFilePath() {
        String filePath;
//        int randNum = RandomUtils.nextInt(1, 6);
        int randNum = 1;
        return "/maps/map" +
                randNum +
                ".txt";
    }
}