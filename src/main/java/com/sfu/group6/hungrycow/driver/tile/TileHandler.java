package com.sfu.group6.hungrycow.driver.tile;

import com.sfu.group6.hungrycow.driver.BoardUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class TileHandler {

    BoardUI ui;
    Tile[] tiles;

    public TileHandler(BoardUI ui) {
        this.ui = ui;
        tiles = new Tile[5];
        getTileImage();
    }

    public void getTileImage() {

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("./tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("../img/tiles/earth.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("../img/tiles/water.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("../img/tiles/tree.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("../img/tiles/wall.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawTile(Graphics2D g) {
        // drawGrass(g);
        g.drawImage(tiles[0].image, 0, 0, ui.tileSize, ui.tileSize, null);

    }

    public void drawGrass(Graphics2D g) {
        for (int i = 0; i < 100; i++) {
            int grassPositionX = new Random().nextInt(ui.numOfTilesHorizontal-1) * ui.tileSize;
            int grassPositionY = new Random().nextInt(ui.numOfTilesVertical-1) * ui.tileSize;
            g.drawImage(tiles[0].image, grassPositionX, grassPositionY, ui.tileSize, ui.tileSize, null);
        }
    }

//    public void drawWall(Graphics2D g) {
//        for (int i = 0; i < 25; i++) {
//
//        }
//    }
}
