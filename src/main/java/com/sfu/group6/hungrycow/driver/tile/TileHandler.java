package com.sfu.group6.hungrycow.driver.tile;

import com.sfu.group6.hungrycow.driver.BoardUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
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
//            tiles[0].image = new ImageIcon("/Images/tiles/grass.png").getImage();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawTile(Graphics2D g) {
         drawGrass(g);
//         drawEarth(g);
//        g.drawImage(tiles[0].image, 0, 0, ui.tileSize, ui.tileSize, null);

    }


    public void drawGrass(Graphics2D g) {
        for (int i = 0; i < ui.numOfTilesHorizontal; i++) {
//            int grassPositionX = new Random().nextInt(ui.numOfTilesHorizontal - 1) * ui.tileSize;
//            int grassPositionY = new Random().nextInt(ui.numOfTilesVertical - 1) * ui.tileSize;
            int grassPositionX = i * ui.tileSize;
            for (int j = 0; j < ui.numOfTilesVertical; j++) {
                int grassPositionY = j * ui.tileSize;
                g.drawImage(tiles[0].image, grassPositionX, grassPositionY, ui.tileSize, ui.tileSize, null);
            }
        }
    }

    public void drawEarth(Graphics2D g) {
        for (int i = 0; i < 100; i++) {
            int earthPositionX = new Random().nextInt(ui.numOfTilesHorizontal - 1) * ui.tileSize;
            int earthPositionY = new Random().nextInt(ui.numOfTilesVertical) * ui.tileSize;
            g.drawImage(tiles[1].image, earthPositionX, earthPositionY, ui.tileSize, ui.tileSize, null);
        }
    }

//    public void drawWall(Graphics2D g) {
//        for (int i = 0; i < 25; i++) {
//
//        }
//    }
}
