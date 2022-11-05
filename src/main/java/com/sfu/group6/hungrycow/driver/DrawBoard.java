package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.driver.tile.TileHandler;
import com.sfu.group6.hungrycow.driver.tile.AnimateHandler;
import com.sfu.group6.hungrycow.driver.tile.Tile;

import com.sfu.group6.hungrycow.model.animate.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.model.inanimate.HungryCowInanimateFactory;

import java.awt.*;
import java.io.IOException;


public class DrawBoard {
    private int[][] boardData;
    BoardUI ui;
    Board board;
    private static int WIDTH = 0;
    private static int HEIGHT = 0;

    TileHandler tileHandler;
    AnimateHandler animateHandler;

    public DrawBoard(BoardUI ui, Board board, String filePath) throws IOException {
        this.ui = ui;
        WIDTH = ui.numOfTilesHorizontal;
        HEIGHT = ui.numOfTilesVertical;
        MapLoader mapLoader = new MapLoader();
        boardData = mapLoader.loadBoard(filePath, WIDTH, HEIGHT);
        tileHandler = new TileHandler(this.ui);
        animateHandler = new AnimateHandler(this.ui);
    }

    public void drawTile(Graphics2D g) throws IOException {
        drawGrass(g);

        for (int row = 0; row < ui.numOfTilesVertical; row++) {

            for (int col = 0; col < ui.numOfTilesHorizontal; col++) {

                int tileData = boardData[col][row];
                int tileSize = ui.tileSize;
                int tilePositionX = col * ui.tileSize;
                int tilePositionY = row * ui.tileSize;
                if(tileData != 2 && tileData != 13
                        && tileData != 14 && tileData != 15
                        && tileData != 16 && tileData != 17) {
                    g.drawImage(tileHandler.tiles[tileData].image, tilePositionX, tilePositionY, tileSize, tileSize, null);
                }
            }
        }
    }

    private void drawGrass(Graphics2D g) {
        for (int i = 0; i < ui.numOfTilesHorizontal; i++) {
            int grassPositionX = i * ui.tileSize;
            for (int j = 0; j < ui.numOfTilesVertical; j++) {
                int grassPositionY = j * ui.tileSize;
                g.drawImage(tileHandler.tiles[0].image, grassPositionX, grassPositionY, ui.tileSize, ui.tileSize, null);
            }
        }
    }
    
    public void drawPlayer(Graphics2D g2) {
    	switch(board.getPlayer().getFacingDirection()) {
    	case UP:
    		if(ui.spriteNumber == 1) {
    			g2.drawImage(animateHandler.tileAnimate[0].playerUp1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		if(ui.spriteNumber == 2) {
    			g2.drawImage(animateHandler.tileAnimate[1].playerUp2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		break;
    	case DOWN:
    		if(ui.spriteNumber == 1) {
    			g2.drawImage(animateHandler.tileAnimate[2].playerDown1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		if(ui.spriteNumber == 2) {
    			g2.drawImage(animateHandler.tileAnimate[3].playerDown2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		break;
    	case LEFT:
    		if(ui.spriteNumber == 1) {
    			g2.drawImage(animateHandler.tileAnimate[4].playerLeft1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		if(ui.spriteNumber == 2) {
    			g2.drawImage(animateHandler.tileAnimate[5].playerLeft2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		break;
    	case RIGHT:
    		if(ui.spriteNumber == 1) {
    			g2.drawImage(animateHandler.tileAnimate[6].playerRight1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		if(ui.spriteNumber == 2) {
    			g2.drawImage(animateHandler.tileAnimate[7].playerRight2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		break;
    	}
    }

    public void drawEnemy(Graphics2D g2) {
    	for (var enemy : board.getEnemies()) {
    		switch(enemy.getFacingDirection()) {
        	case UP:
        		if(ui.spriteNumber == 1) {
        			g2.drawImage(animateHandler.tileAnimate[0].playerUp1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		if(ui.spriteNumber == 2) {
        			g2.drawImage(animateHandler.tileAnimate[1].playerUp2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		break;
        	case DOWN:
        		if(ui.spriteNumber == 1) {
        			g2.drawImage(animateHandler.tileAnimate[2].playerDown1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		if(ui.spriteNumber == 2) {
        			g2.drawImage(animateHandler.tileAnimate[3].playerDown2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		break;
        	case LEFT:
        		if(ui.spriteNumber == 1) {
        			g2.drawImage(animateHandler.tileAnimate[4].playerLeft1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		if(ui.spriteNumber == 2) {
        			g2.drawImage(animateHandler.tileAnimate[5].playerLeft2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		break;
        	case RIGHT:
        		if(ui.spriteNumber == 1) {
        			g2.drawImage(animateHandler.tileAnimate[6].playerRight1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		if(ui.spriteNumber == 2) {
        			g2.drawImage(animateHandler.tileAnimate[7].playerRight2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		break;
        	}
    	}
    	
    }
    
    public void drawBonusReward(Graphics2D g2) {
    	for(var BonusReward : board.getBonus()) {
    		if(board.isCollectedBonus() == true) {
    			g2.drawImage(animateHandler.tileAnimate[7].playerRight2, BonusReward.getPosition().getX(), BonusReward.getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		} 
    	}
    }
    
    public void drawObjective(Graphics2D g2) {
    	for(var RegularReward : board.getObjectives()) {
    		if(board.isCollectedObjective() == true) {
    			g2.drawImage(animateHandler.tileAnimate[7].playerRight2, RegularReward.getPosition().getX(), RegularReward.getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		} 
    	}
    }
    
    public void drawGate(Graphics2D g2) {
    		if(board.getObjectives().isEmpty()) {
    			g2.drawImage(animateHandler.tileAnimate[7].playerRight2, board.getEndSpace().getX(), board.getEndSpace().getY(), ui.tileSize, ui.tileSize, null);
        	} else {
        		g2.drawImage(animateHandler.tileAnimate[7].playerRight2, board.getEndSpace().getX(), board.getEndSpace().getY(), ui.tileSize, ui.tileSize, null);
        	}
    }

}