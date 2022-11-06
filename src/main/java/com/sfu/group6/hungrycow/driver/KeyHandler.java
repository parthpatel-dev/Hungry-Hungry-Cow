package com.sfu.group6.hungrycow.driver;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, escPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case 37: upPressed = true; //For arrow key left
		        break;
		case 38: downPressed = true; //For arrow key up
		        break;
		case 39: leftPressed = true; //For arrow key down
		        break;
		case 40: rightPressed = true; //For arrow key right
		        break;
		case 32: spacePressed = true; // For space key
			break;
		case 27: escPressed = true; // For escape key
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
