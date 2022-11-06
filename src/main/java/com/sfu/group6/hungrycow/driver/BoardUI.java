package com.sfu.group6.hungrycow.driver;



import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.model.animate.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.model.inanimate.HungryCowInanimateFactory;

import com.sfu.group6.hungrycow.ui.DrawScreen;
import com.sfu.group6.hungrycow.ui.Screen;


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
    DrawScreen drawScreen;
    Screen state; // Assume it starts in Board state
    


    public BoardUI() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        mapLoader = new MapLoader();
        int[][] boardData = mapLoader.loadBoard(getRandomMapFilePath(), this.numOfTilesHorizontal, this.numOfTilesVertical);
        boardFactory = new BoardFactory();
        animateFactory = new HungryCowAnimateFactory();
        inanimateFactory = new HungryCowInanimateFactory();
        drawScreen = new DrawScreen();
        board = boardFactory.createBoard(boardData, animateFactory, inanimateFactory);
        drawBoard = new DrawBoard(this, board, boardData);
        key = new KeyHandler();
        this.addKeyListener(key);
        startGameThread();
        state = Screen.START;
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

    private boolean isBoard()
    {
        return state == Screen.BOARD;
    }

    public void update() {
    	if(key.upPressed == true && isBoard()) {
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
    	} else if(key.downPressed == true && isBoard()) {
    		board.tickBoardState(Direction.DOWN);
    	} else if(key.leftPressed == true && isBoard()) {
    		board.tickBoardState(Direction.LEFT);
    	} else if(key.rightPressed == true && isBoard()) {
    		board.tickBoardState(Direction.RIGHT);
    	} else if (key.spacePressed == true ) {
            if (state == Screen.START)
            {
                // Start Board Game
                startButtonPress = true;
                state = Screen.BOARD;
            }
            else if (state == Screen.PAUSE)
            {
                state = Screen.BOARD;
                //repaint();
            }
            else if (state == Screen.GAME_OVER || state == Screen.GAME_WIN)
            {
                System.exit(0); // Ext screen
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
                //repaint();
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

        if (startButtonPress && isBoard()) {
        	try {
				drawBoard.drawTile(g2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            playGame(g2); //For drawing the entities
        }
        else if (state == Screen.PAUSE)
        {
            drawScreen.pauseScreen(g2, this.tileSize, this.numOfTilesHorizontal, this.numOfTilesVertical);
        }
        else {
            drawScreen.startScreen(g2, this.tileSize, this.numOfTilesHorizontal, this.numOfTilesVertical);//set startButtonPress to true once user press gui button
            state = Screen.START; // Likely redundant but here to make sure
            startButtonPress = false; // Likely redundant but here to make sure
        }

        // tileHandler.drawTile(g2); This was place here when I merged, assume this is for
        //                           testing, commented this out for now
        g2.dispose();
}

   private void playGame(Graphics2D g2) {
        drawScreen.score(g2,this.tileSize,this.numOfTilesHorizontal,this.numOfTilesVertical, board.getPlayer().getScore());

        if (board.isGameOver() == true) {
        drawScreen.gameOverScreen(g2,this.tileSize,this.numOfTilesHorizontal,this.numOfTilesVertical, board.getPlayer().getScore());
        } else if(board.isPlayerWin() == true){
        	drawScreen.victoryScreen(g2,this.tileSize,this.numOfTilesHorizontal,this.numOfTilesVertical, board.getPlayer().getScore());
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
