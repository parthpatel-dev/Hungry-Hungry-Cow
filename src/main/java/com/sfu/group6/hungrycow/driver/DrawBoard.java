package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.driver.tile.TileHandler;
import com.sfu.group6.hungrycow.model.animate.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.model.inanimate.HungryCowInanimateFactory;

import java.awt.*;
import java.io.IOException;


public class DrawBoard {
    private static final int WIDTH = 0;
    private static final int HEIGHT = 0;
    private int[][] boardData;
    BoardUI ui;
    Board board;

    TileHandler tileHandler;

    BoardFactory boardFactory;

    HungryCowAnimateFactory animateFactory;
    HungryCowInanimateFactory inanimateFactory;

    public DrawBoard(BoardUI ui) throws IOException {
        this.ui = ui;
        MapLoader mapLoader = new MapLoader();
        boardData = mapLoader.loadBoard(getRandomMapFilePath(), WIDTH, HEIGHT);
        animateFactory = new HungryCowAnimateFactory();
        inanimateFactory = new HungryCowInanimateFactory();
        boardFactory = new BoardFactory();
        board = boardFactory.createBoard(boardData, animateFactory, inanimateFactory);
        tileHandler = new TileHandler(this.ui);
//        animateHandler = new AnimateHandler(this.ui);
    }

    public void drawTile(Graphics2D g) throws IOException {
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