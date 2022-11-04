package com.sfu.group6.hungrycow.driver.tile;

import com.sfu.group6.hungrycow.driver.BoardUI;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class TileHandler {

    public BoardUI ui;
    public Tile[] tiles;

    public TileHandler(BoardUI ui) throws IOException {
        this.ui = ui;
        tiles = new Tile[50];
        getTileImage();
    }

    public void getTileImage() {

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/field.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cow1.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/1.png")));


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
}
