package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.driver.tile.TileHandler;
import com.sfu.group6.hungrycow.driver.Board; 

import javax.swing.*;
import java.awt.*;

public class BoardUI extends JPanel implements Runnable{

	Board board;
    final int defaultTileSize = 16;
    final int scale = 3;
    public boolean startButtonPress = true;
    public final int tileSize = defaultTileSize * scale;
    public final int numOfTilesHorizontal = 20;
    public final int numOfTilesVertical = 14;
    final int screenWidth = numOfTilesHorizontal * tileSize;
    final int screenHeight = numOfTilesVertical * tileSize;

    public BoardUI() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        Board = boardFactory.createBoard();
        board = Board.builder().build();
    }

    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    TileHandler tileHandler = new TileHandler(this);


    @Override
    public void run() {

        while(gameThread != null) {

            update();
            repaint();
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);

        
        if (startButtonPress) {
            playGame(g2); //For drawing the entities
        } else {
            //showIntroScreen(g2); //set startButtonPress to true once user press gui button
        }

        g2.dispose();
     }
}

private void playGame(Graphics2D g2) {
	 tileHandler.drawTile(g2);
    if (Board.isGameOver() == true) {
        gameOverScreen(g2);
    } else if(Board.isGameOver() == true){
    	victoryScreen(g2);
    }else {
        drawPlayer(g2);
        drawEnemy(g2);
        drawPunishment(g2);
        drawRewards(g2);
        drawBonusReward(g2);
        drawBarriers(g2);
        if(Board.reward() == true) {
        	drawOpenedExit(g2);
        } else {
        	drawClosedExit(g2);
        }
    }
}
