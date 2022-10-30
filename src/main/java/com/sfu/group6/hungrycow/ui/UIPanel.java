package com.sfu.group6.hungrycow.ui;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.model.AbstractEntity;
import com.sfu.group6.hungrycow.model.animate.Animate;
import com.sfu.group6.hungrycow.model.driver.Board; 


public class UIPanel extends JPanel implements KeyListener {
	
	private Image babyCowLeft, babyCowDown, babyCowUp, babyCowRight, barrierTree, milk, grass, punishmentFlies, enemyUp, enemyDown, enemyLeft, enemyRight, exitClosed, exitOpen, background;
	private int req_dx, req_dy;

	UIPanel(){
		loadImages();
		this.addKeyListener(this);
		setFocusable(true);
		this.setPreferredSize(new Dimension(600,600));
	}
	
	 private void loadImages() {
	    	babyCowDown = new ImageIcon("/Images/babyCowDown").getImage();
	    	babyCowUp = new ImageIcon("/Images/babyCowUp").getImage();
	    	babyCowLeft = new ImageIcon("/Images/babyCowLeft").getImage();
	    	babyCowRight = new ImageIcon("/Images/babyCowDown").getImage();
	    	barrierTree = new ImageIcon("/Images/barrierTree").getImage();
	    	milk = new ImageIcon("/Images/milk").getImage();
	    	grass = new ImageIcon("/Images/grass").getImage();
	    	punishmentFlies = new ImageIcon("/Images/punishmentFlies").getImage();
	    	enemyUp = new ImageIcon("/Images/enemyUp").getImage();
	    	enemyDown = new ImageIcon("/Images/enemyDown").getImage();
	    	enemyLeft = new ImageIcon("/Images/enemyLeft").getImage();
	    	enemyRight = new ImageIcon("/Images/enemyRight").getImage();
	    	exitClosed = new ImageIcon("/Images/exitClosed").getImage();
	    	exitOpen = new ImageIcon("/Images/exitOpen").getImage();
	    	background = new ImageIcon("/Images/background").getImage();
	    }
	
	 public void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;

	        g2d.setColor(Color.black);
	        
	        drawBackGround(g2d); //For drawing the background grass field (very bottom layer)
	        drawScore(g2d); //For displaying score

	        if (Board.inGame()) {
	            playGame(g2d); //For drawing the entities
	        } else {
	            showIntroScreen(g2d);
	        }

	        Toolkit.getDefaultToolkit().sync();
	        g2d.dispose();
	    }
	 
	 private void playGame(Graphics2D g2d) {
	        if (Board.status() == gameOver) {
	            gameOverScreen(g2d);
	        } else if(Board.status() == victory){
	        	victoryScreen(g2d);
	        }else {
	            drawPlayer(g2d);
	            drawEnemy(g2d);
	            drawPunishment(g2d);
	            drawRewards(g2d);
	            drawBonusReward(g2d);
	            drawBarriers(g2d);
	            if(Board.reward() == true) {
	            	drawOpenedExit(g2d);
	            } else {
	            	drawClosedExit(g2d);
	            }
	        }
	    }
	 
	 //Example of how the entities can be drawn
	 //player and enemy needs 4 picture which depicts the direction they went
	 private void drawPlayer(Graphics2D g2d) {
	        if ( req_dx	== -1) {
	        	g2d.drawImage(babyCowLeft, Board.Player.getPostionX(), Board.Player.getPostionY(), this);
	        } else if (req_dx == 1) {
	        	g2d.drawImage(babyCowRight, Board.Player.getPostionX(), Board.Player.getPostionY(), this);
	        } else if (req_dy == -1) {
	        	g2d.drawImage(babyCowUp, Board.Player.getPostionX(), Board.Player.getPostionY(), this);
	        } else {
	        	g2d.drawImage(babyCowDown, Board.Player.getPostionX(), Board.Player.getPostionY(), this);
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
		case 37: Board.tickGame(LEFT); //For arrow key left
				req_dx = -1;
		        req_dy = 0;
		        break;
		case 38: Board.tickGame(UP); //For arrow key up
				req_dx = 1;
		        req_dy = 0;
		        break;
		case 39: Board.tickGame(DOWN); //For arrow key down
				req_dx = 0;
		        req_dy = -1;
		        break;
		case 40: Board.tickGame(RIGHT); //For arrow key right
				req_dx = 0;
		        req_dy = 1;
		        break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
