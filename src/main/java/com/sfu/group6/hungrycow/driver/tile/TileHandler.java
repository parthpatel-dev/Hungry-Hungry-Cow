package com.sfu.group6.hungrycow.driver.tile;

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

    int[][] boardData;

    public TileHandler(BoardUI ui) throws IOException {
        this.ui = ui;
        tiles = new Tile[5];
        boardData = new int[ui.numOfTilesHorizontal][ui.numOfTilesVertical];
        getTileImage();
        loadBoard(getRandomMapFilePath());
    }

    public void getTileImage() {

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/barrierTree.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

            }
        }
        bufferReader.close();
    }

    public String getRandomMapFilePath() {
        String filePath;
//        int randNum = RandomUtils.nextInt(1, 6);
        int randNum = 1;
        return "/maps/map" +
                randNum +
                ".txt";
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
                int tilePositionX = col * ui.tileSize;
                int tilePositionY = row * ui.tileSize;
                g.drawImage(tiles[tileData].image, tilePositionX, tilePositionY, ui.tileSize, ui.tileSize, null);
            }
        }


    }


    public void drawGrass(Graphics2D g) {
        int backgroundSize = 320;
        for (int i = 0; i < 3; i++) {
            int grassPositionX = i * backgroundSize;
            for (int j = 0; j < 3; j++) {
                int grassPositionY = j * backgroundSize;
                g.drawImage(tiles[0].image, grassPositionX, grassPositionY, backgroundSize, backgroundSize, null);
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
