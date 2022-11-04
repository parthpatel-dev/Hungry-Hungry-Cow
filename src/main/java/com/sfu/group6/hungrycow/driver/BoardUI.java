package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.driver.tile.TileHandler;
import com.sfu.group6.hungrycow.driver.tile.AnimateHandler;

import com.sfu.group6.hungrycow.driver.Board; 
import com.sfu.group6.hungrycow.control.*;
import com.sfu.group6.hungrycow.ui.DrawScreen;
import com.sfu.group6.hungrycow.ui.Screen;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



import java.io.IOException;


public class BoardUI extends JPanel implements Runnable {

	Board board;
    final int defaultTileSize = 16;
    final int FPS =60;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public boolean startButtonPress = true;
    final int scale = 2;
    public final int tileSize = defaultTileSize * scale;
    public final int numOfTilesHorizontal = 24;
    public final int numOfTilesVertical = 15;
    public final int screenWidth = numOfTilesHorizontal * tileSize;
    public final int screenHeight = numOfTilesVertical * tileSize;
    Screen state = Screen.START; // Assume it starts in Board state
    

    public BoardUI() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        board = Board.builder().build();
        //board.getPlayer().getPosition().getX();
        board = Board.builder().build();
        //createBoard
        this.addKeyListener(key);
        //Board = boardFactory.createBoard();
    }
    KeyHandler key = new KeyHandler();
    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    DrawScreen drawScreen = new DrawScreen();
    TileHandler tileHandler = new TileHandler(this);

    AnimateHandler animateHandler = new AnimateHandler(this);
    
    @Override
    public void run() {
    	double drawInterval = 1000000000/FPS;
    	double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) {
        	
            update();
            repaint();
            try {
            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime/ 1000000;
            if(remainingTime < 0) {
            	remainingTime = 0;
            }
            
				Thread.sleep((long) remainingTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            nextDrawTime += drawInterval;
        }
    }
    
    public void update() {
    	if(key.upPressed == true) {
    		 board.tickBoardState(Direction.UP);
    	} else if(key.downPressed == true) {
    		board.tickBoardState(Direction.DOWN);
    	} else if(key.leftPressed == true) {
    		board.tickBoardState(Direction.LEFT);
    	} else if(key.rightPressed == true) {
    		board.tickBoardState(Direction.RIGHT);
    	} else if (key.spacePressed == true) {
            if (state == Screen.START)
            {
                // Start Board Game
                startButtonPress = true;
                state = Screen.BOARD;
            }
            else if (state == Screen.PAUSE)
            {
                state = Screen.BOARD;
                repaint();
            }
            else if (state == Screen.GAME_OVER || state == Screen.GAME_WIN)
            {
                System.exit(0); // Ext screen
            }
            else // Catch all in case unknown space press action
            {
                System.exit(10); //exit the game
            }
        } else if (key.escPressed == true)
        {
            if (state == Screen.START)
            {
                System.exit(1); // Exit Game
            }
            else if (state == Screen.PAUSE)
            {
                System.exit(2); // Exit Game
            }
            else if (state == Screen.BOARD)
            {
                state = Screen.PAUSE;
                repaint();
            }
        }
    	spriteCounter++;
    	if(spriteCounter > 10) {
    		if(spriteNumber == 1) {
    			spriteNumber = 2;
    		} else if (spriteNumber == 2) {
    			spriteNumber = 1;
    		}
    		spriteCounter = 0;
    	}
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        
        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);

        if (startButtonPress) {
            tileHandler.drawTile(g2);
            playGame(g2); //For drawing the entities
        } else {
            drawScreen.startScreen(g2, this.tileSize, this.numOfTilesHorizontal, this.numOfTilesVertical);//set startButtonPress to true once user press gui button
            state = Screen.START;
            startButtonPress = false;
        }

        //tileHandler.drawTile(g2);
        g2.dispose();
     }



    private void playGame(Graphics2D g2) {
    //        drawScreen.score(g2,this.tileSize,this.numOfTilesHorizontal,this.numOfTilesVertical, board.getPlayer().getScore());
        //if (board.isGameOver() == true) {
    //        drawScreen.gameOverScreen(g2,this.tileSize,this.numOfTilesHorizontal,this.numOfTilesVertical);
    //    } else if(isPlayerWin() == true){
    //    	drawScreen.victoryScreen(g2,this.tileSize,this.numOfTilesHorizontal,this.numOfTilesVertical);
        //}else {
            //animateHandler.drawPlayer(g2);
            //animateHandler.drawEnemy(g2);
            /*
             * drawPunishment
             * drawBonusReward
             * drawReward
             *
            */
    //        if(Board.reward() == true) {
    //        	drawOpenedExit(g2);
    //        } else {
    //        	drawClosedExit(g2);
    //        }
        //}
    }

}
