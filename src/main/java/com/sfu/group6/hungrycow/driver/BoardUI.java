package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.driver.tile.TileHandler;
import com.sfu.group6.hungrycow.driver.Board; 
import com.sfu.group6.hungrycow.control.*;
import com.sfu.group6.hungrycow.ui.DrawScreen;
import com.sfu.group6.hungrycow.ui.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardUI extends JPanel implements Runnable, KeyListener {

	Board board;
    final int defaultTileSize = 16;
    final int scale = 3;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, escPressed;
    public boolean startButtonPress = true;
    public final int tileSize = defaultTileSize * scale;
    public final int numOfTilesHorizontal = 20;
    public final int numOfTilesVertical = 14;
    final int screenWidth = numOfTilesHorizontal * tileSize;
    final int screenHeight = numOfTilesVertical * tileSize;

    Screen state = Screen.START; // Assume it starts in Board state


    public BoardUI() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        //Board = boardFactory.createBoard();
        board = Board.builder().build();
        //board.getPlayer().getPosition().getX();
        board = Board.builder().build();
    }

    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    DrawScreen drawScreen = new DrawScreen();
    TileHandler tileHandler = new TileHandler(this);
    

    @Override
    public void run() {

        while(gameThread != null) {
        	
            update();
            repaint();
            
        }
    }
    
    public void update() {
    	if(upPressed == true) {
    		 board.tickBoardState(Direction.UP);
    	} else if(downPressed == true) {
    		board.tickBoardState(Direction.DOWN);
    	} else if(leftPressed == true) {
    		board.tickBoardState(Direction.LEFT);
    	} else if(rightPressed == true) {
    		board.tickBoardState(Direction.RIGHT);
    	} else if (spacePressed == true) {
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
        } else if (escPressed == true)
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
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);

        
        if (startButtonPress) {
            // Board creation
            playGame(g2); //For drawing the entities
        } else {
            DrawScreen.startScreen(g2, this.tileSize, this.numOfTilesHorizontal, this.numOfTilesVertical);//set startButtonPress to true once user press gui button
            state = Screen.BOARD;
            startButtonPress = false;
        }

        g2.dispose();
     }

private void playGame(Graphics2D g2) {
	 tileHandler.drawTile(g2);
	 drawScore(g2);
    if (board.isGameOver() == true) {
        drawScreen.gameOverScreen(g2,this.tileSize,this.numOfTilesHorizontal,this.numOfTilesVertical, this.state);
        //gameOverScreen(g2);
    } else if(board.isGameOver() == false){
        drawScreen.victoryScreen(g2,this.tileSize,this.numOfTilesHorizontal,this.numOfTilesVertical);
        //victoryScreen(g2)
    }else {
        if (state == Screen.BOARD)
        {
            // make draw method for score. Assume top row is blank so draw screo top left, and title top right
            // use board.getPlayer.getScore()
        }
        drawPlayer(g2);
        drawEnemy(g2);
        /*
         * drawPunishment
         * drawBonusReward
         * drawReward
         * 
        */
        if(Board.reward() == true) {
        	drawOpenedExit(g2);
        } else {
        	drawClosedExit(g2);
        }
    }
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub

}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	switch(e.getKeyCode()) {
	case 37: leftPressed = true; //For arrow key left
	        break;
	case 38: upPressed = true; //For arrow key up
	        break;
	case 39: downPressed = true; //For arrow key down
	        break;
	case 40: rightPressed = true; //For arrow key right
	        break;
    case 32: spacePressed = true;
            break;
    case 27: escPressed = true;
            break;
	}

}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub

}
}
