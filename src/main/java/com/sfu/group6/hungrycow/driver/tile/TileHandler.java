package com.sfu.group6.hungrycow.driver.tile;

import com.sfu.group6.hungrycow.driver.BoardDataLoader;
import com.sfu.group6.hungrycow.driver.BoardUI;
import org.apache.commons.lang3.RandomUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;

public class TileHandler {

    BoardUI ui;
    Tile[] tiles;

    BoardDataLoader loader;



    public TileHandler(BoardUI ui) throws IOException {
        this.ui = ui;
        tiles = new Tile[20];
        getTileImage();
//        loadBoard(getRandomMapFilePath());
    }

    public void getTileImage() {

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/field.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tall_tree.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pond.png")));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/crop1.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/heavy_grass.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawTile(Graphics2D g) {
         drawGrass(g);
//        drawEarth(g);
//        drawWater(g);
//        drawTree(g);
//        drawWall(g);

        for (int row = 0; row < ui.numOfTilesVertical; row++) {
            
            for (int col = 0; col < ui.numOfTilesHorizontal; col++) {

                int tileData = boardData[col][row];
                int tileSize = ui.tileSize;
                if(tileData == 2) {
                    tileSize *= 3;
                }
                int tilePositionX = col * ui.tileSize;
                int tilePositionY = row * ui.tileSize;
                g.drawImage(tiles[tileData].image, tilePositionX, tilePositionY, tileSize, tileSize, null);
            }
        }


    }


    public void drawGrass(Graphics2D g) {
        for (int i = 0; i < ui.numOfTilesHorizontal; i++) {
            int grassPositionX = i * ui.tileSize;
            for (int j = 0; j < ui.numOfTilesVertical; j++) {
                int grassPositionY = j * ui.tileSize;
                g.drawImage(tiles[0].image, grassPositionX, grassPositionY, ui.tileSize, ui.tileSize, null);
            }
        }
    }

    public void drawEarth(Graphics2D g) {
        for (int i = 0; i < 40; i++) {
            int earthPositionX = new Random().nextInt(ui.numOfTilesHorizontal - 1) * ui.tileSize;
            int earthPositionY = new Random().nextInt(ui.numOfTilesVertical) * ui.tileSize;
            g.drawImage(tiles[1].image, earthPositionX, earthPositionY, ui.tileSize, ui.tileSize, null);
        }
    }

    public void drawWater(Graphics2D g) {

    }

    public void drawTree(Graphics2D g) {
        for (int i = 0; i < 40; i++) {
            int treePositionX = new Random().nextInt(ui.numOfTilesHorizontal - 1) * ui.tileSize;
            int treePositionY = new Random().nextInt(ui.numOfTilesVertical) * ui.tileSize;
            g.drawImage(tiles[3].image, treePositionX, treePositionY, ui.tileSize, ui.tileSize, null);
        }
    }

    public void drawWall(Graphics2D g) {
        for (int i = 0; i < 40; i++) {
            int wallPositionX = new Random().nextInt(ui.numOfTilesHorizontal - 1) * ui.tileSize;
            int wallPositionY = new Random().nextInt(ui.numOfTilesVertical) * ui.tileSize;
            g.drawImage(tiles[4].image, wallPositionX, wallPositionY, ui.tileSize, ui.tileSize, null);
        }
    }
}
