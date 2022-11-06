package com.sfu.group6.hungrycow.driver;


import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.model.animate.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.model.inanimate.HungryCowInanimateFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BoardUI extends JPanel implements Runnable {

	public boolean update = false;
    final int defaultTileSize = 16;
    private final int FPS =60;
    private double time = 0;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public boolean startButtonPress = true;
    final int scale = 2;
    public final int tileSize = defaultTileSize * scale;
    public final int numOfTilesHorizontal = 24;
    public final int numOfTilesVertical = 15;
    public final int screenWidth = numOfTilesHorizontal * tileSize;
    public final int screenHeight = numOfTilesVertical * tileSize;

    MapLoader mapLoader;
    Board board;
    DrawBoard drawBoard;
    BoardFactory boardFactory;
    HungryCowAnimateFactory animateFactory;
    HungryCowInanimateFactory inanimateFactory;
    KeyHandler key;
    Thread gameThread;

    public BoardUI() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        mapLoader = new MapLoader();
        int[][] boardData = mapLoader.loadBoard(getRandomMapFilePath(), this.numOfTilesHorizontal, this.numOfTilesVertical);
        boardFactory = new BoardFactory();
        animateFactory = new HungryCowAnimateFactory();
        inanimateFactory = new HungryCowInanimateFactory();
        board = boardFactory.createBoard(boardData, animateFactory, inanimateFactory);
        drawBoard = new DrawBoard(this, board, boardData);
        key = new KeyHandler();
        this.addKeyListener(key);
        startGameThread();
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
    	double interval = 1000000000/FPS;
    	time = interval +  System.nanoTime();
        while(gameThread != null) {
        	System.out.println("hereagain");
            update();
            if(update == true) {
            	repaint();
                sleepForTimeAfterAnimation();
                update = false;
                time = time + interval;
        	}
        }
    }
    
    public void sleepForTimeAfterAnimation() {
    	try {
            double timeLeft = time - System.nanoTime();
            timeLeft = timeLeft/ 1000000;
            if(timeLeft < 0) {
            	timeLeft = 0;
            }
				Thread.sleep((long) timeLeft);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public void update() {
    	if(key.upPressed == true) {
    		 board.tickBoardState(Direction.UP);
    		 update = true;
    	} else if(key.downPressed == true) {
    		board.tickBoardState(Direction.DOWN);
    		update = true;
    	} else if(key.leftPressed == true) {
    		board.tickBoardState(Direction.LEFT);
    		update = true;
    	} else if(key.rightPressed == true) {
    		board.tickBoardState(Direction.RIGHT);
    		update = true;
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
        	try {
				drawBoard.drawTile(g2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            playGame(g2); //For drawing the entities
       } else {
//            //showIntroScreen(g2); //set startButtonPress to true once user press gui button
       }


        g2.dispose();
}

private void playGame(Graphics2D g2) {
	 //drawScore(g2);
    if (board.isGameOver() == true) {
        //gameOverScreen(g2);
    } else if(board.isPlayerWin() == true){
    	//victoryScreen(g2);
    }else {
    	drawBoard.drawPlayer(g2);
    	drawBoard.drawEnemy(g2);
    	drawBoard.drawBonusReward(g2);
    	drawBoard.drawObjective(g2);
    	drawBoard.drawGate(g2);
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
