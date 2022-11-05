package com.sfu.group6.hungrycow.driver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BoardUI extends JPanel implements Runnable{

    final int defaultTileSize = 16;
    final int scale = 2;

    public final int tileSize = defaultTileSize * scale;
    public final int numOfTilesHorizontal = 24;
    public final int numOfTilesVertical = 15;
    public final int screenWidth = numOfTilesHorizontal * tileSize;
    public final int screenHeight = numOfTilesVertical * tileSize;
    DrawBoard drawBoard;


    public BoardUI() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        drawBoard = new DrawBoard(this);
    }

    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

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

        try {
            drawBoard.drawTile(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.dispose();
     }

}
