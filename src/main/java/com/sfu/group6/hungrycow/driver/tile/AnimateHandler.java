package com.sfu.group6.hungrycow.driver.tile;

import com.sfu.group6.hungrycow.driver.Board;
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
//    Tile[] tiles;
    Tile[] tileAnimate;

    public AnimateHandler(BoardUI ui) {
        this.ui = ui;
        //tiles = new Tile[10];
        tileAnimate = new Tile[10];
        getAnimateImage();
        board = Board.builder().build();
        //board.getPlayer().getPosition().getX();
    }

    public void getAnimateImage() {

        try {
        	tileAnimate[0] = new Tile();
        	tileAnimate[0].playerUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animals/cow1.png")));
        	tileAnimate[1] = new Tile();
        	tileAnimate[1].playerUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animals/cow1.png")));
        	tileAnimate[2] = new Tile();
        	tileAnimate[2].playerDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animals/cow1.png")));
        	tileAnimate[3] = new Tile();
        	tileAnimate[3].playerDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animals/cow1.png")));
        	tileAnimate[4] = new Tile();
        	tileAnimate[4].playerRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animals/cow1.png")));
        	tileAnimate[5] = new Tile();
        	tileAnimate[5].playerRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animals/cow1.png")));
        	tileAnimate[6] = new Tile();
        	tileAnimate[6].playerLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animals/cow1.png")));
        	tileAnimate[7] = new Tile();
        	tileAnimate[7].playerLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/animals/cow1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawPlayer(Graphics2D g2) {
    	switch(board.getPlayer().getFacingDirection()) {
    	case UP:
    		if(ui.spriteNumber == 1) {
    			g2.drawImage(tileAnimate[0].playerUp1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		if(ui.spriteNumber == 2) {
    			g2.drawImage(tileAnimate[1].playerUp2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		break;
    	case DOWN:
    		if(ui.spriteNumber == 1) {
    			g2.drawImage(tileAnimate[2].playerDown1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		if(ui.spriteNumber == 2) {
    			g2.drawImage(tileAnimate[3].playerDown2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		break;
    	case LEFT:
    		if(ui.spriteNumber == 1) {
    			g2.drawImage(tileAnimate[4].playerLeft1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		if(ui.spriteNumber == 2) {
    			g2.drawImage(tileAnimate[5].playerLeft2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		break;
    	case RIGHT:
    		if(ui.spriteNumber == 1) {
    			g2.drawImage(tileAnimate[6].playerRight1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		if(ui.spriteNumber == 2) {
    			g2.drawImage(tileAnimate[7].playerRight2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		}
    		break;
    	}
    }

    public void drawEnemy(Graphics2D g2) {
    	for (var enemy : board.getEnemies()) {
    		switch(enemy.getFacingDirection()) {
        	case UP:
        		if(ui.spriteNumber == 1) {
        			g2.drawImage(tileAnimate[0].playerUp1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		if(ui.spriteNumber == 2) {
        			g2.drawImage(tileAnimate[1].playerUp2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		break;
        	case DOWN:
        		if(ui.spriteNumber == 1) {
        			g2.drawImage(tileAnimate[2].playerDown1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		if(ui.spriteNumber == 2) {
        			g2.drawImage(tileAnimate[3].playerDown2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		break;
        	case LEFT:
        		if(ui.spriteNumber == 1) {
        			g2.drawImage(tileAnimate[4].playerLeft1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		if(ui.spriteNumber == 2) {
        			g2.drawImage(tileAnimate[5].playerLeft2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		break;
        	case RIGHT:
        		if(ui.spriteNumber == 1) {
        			g2.drawImage(tileAnimate[6].playerRight1, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		if(ui.spriteNumber == 2) {
        			g2.drawImage(tileAnimate[7].playerRight2, board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY(), ui.tileSize, ui.tileSize, null);
        		}
        		break;
        	}
    	}
    	
    }
    
    public void drawBonusReward(Graphics2D g2) {
    	for(var BonusReward : board.getBonus()) {
    		if(board.isCollectedBonus() == true) {
    			g2.drawImage(tileAnimate[7].playerRight2, BonusReward.getPosition().getX(), BonusReward.getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		} 
    	}
    }
    
    public void drawObjective(Graphics2D g2) {
    	for(var objective : board.getObjectives()) {
    		if(board.isCollectedObjective() == true) {
    			g2.drawImage(tileAnimate[7].playerRight2, objective.getPosition().getX(), objective.getPosition().getY(), ui.tileSize, ui.tileSize, null);
    		} 
    	}
    }
    public void drawClosedGate(Graphics2D g2) {
    	
    }
    public void drawOpenGate(Graphics2D g2) {
    	
    }
}
