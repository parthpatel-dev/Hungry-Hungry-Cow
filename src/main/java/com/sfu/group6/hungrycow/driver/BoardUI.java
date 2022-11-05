package com.sfu.group6.hungrycow.driver;

<<<<<<< HEAD
import com.sfu.group6.hungrycow.driver.tile.TileHandler;
import com.sfu.group6.hungrycow.driver.tile.AnimateHandler;

import com.sfu.group6.hungrycow.driver.Board; 
import com.sfu.group6.hungrycow.control.*;

=======
>>>>>>> 0e441a4e71cbb122166465031d0ff9927789fca6
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
    

    MapLoader mapLoader = new MapLoader();
    DrawBoard drawBoard = new DrawBoard(this);
    int[][] boardData = mapLoader.loadBoard(drawBoard.getRandomMapFilePath(), numOfTilesHorizontal, numOfTilesVertical);
    BoardFactory boardFactory = new BoardFactory();


    public BoardUI() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        board = Board.builder().build();
        //createBoard
        this.addKeyListener(key);
        //Board = boardFactory.createBoard();
        //isObjective.isEmpty
    }

    KeyHandler key = new KeyHandler();
    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    BoardDataLoader tileHandler = new BoardDataLoader(this);

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

        
//        if (startButtonPress) {
        	try {
				tileHandler.drawTile(g2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //playGame(g2); //For drawing the entities
//        } else {
//            //showIntroScreen(g2); //set startButtonPress to true once user press gui button
//        }


        //tileHandler.drawTile(g2);


        g2.dispose();
     //}
}

private void playGame(Graphics2D g2) {
	 drawScore(g2);
    if (board.isGameOver() == true) {
        gameOverScreen(g2);
    } else if(board.isPlayerWin() == true){
    	victoryScreen(g2);
    }else {
        animateHandler.drawPlayer(g2);
        animateHandler.drawEnemy(g2);
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
    }
}

}
