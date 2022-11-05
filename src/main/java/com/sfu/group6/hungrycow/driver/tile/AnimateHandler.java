package com.sfu.group6.hungrycow.driver.tile;

import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.driver.BoardFactory;
import com.sfu.group6.hungrycow.driver.BoardUI;
import com.sfu.group6.hungrycow.driver.KeyHandler;
import com.sfu.group6.hungrycow.model.animate.AbstractAnimate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

public class AnimateHandler {

    BoardUI ui;
    Board board;
    BoardFactory boardFactory;
//    Tile[] tiles;
    public Tile[] tileAnimate;

    public AnimateHandler(BoardUI ui) {
        this.ui = ui;
        tileAnimate = new Tile[10];
        getAnimateImage();
    }
 
    public void getAnimateImage() {

        try {
        	tileAnimate[0] = new Tile();
        	tileAnimate[0].playerUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animate/cow1.png")));
        	tileAnimate[1] = new Tile();
        	tileAnimate[1].playerUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animate/cow1.png")));
        	tileAnimate[2] = new Tile();
        	tileAnimate[2].playerDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animate/cow1.png")));
        	tileAnimate[3] = new Tile();
        	tileAnimate[3].playerDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animate/cow1.png")));
        	tileAnimate[4] = new Tile();
        	tileAnimate[4].playerRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animate/cow1.png")));
        	tileAnimate[5] = new Tile();
        	tileAnimate[5].playerRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animate/cow1.png")));
        	tileAnimate[6] = new Tile();
        	tileAnimate[6].playerLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animate/cow1.png")));
        	tileAnimate[7] = new Tile();
        	tileAnimate[7].playerLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animate/cow1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   
}
