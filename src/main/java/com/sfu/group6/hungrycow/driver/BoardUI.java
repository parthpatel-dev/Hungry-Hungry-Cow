package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.driver.tile.TileHandler;

import javax.swing.*;
import java.awt.*;

public class BoardUI extends JPanel implements Runnable{

    final int defaultTileSize = 16;
    final int scale = 3;

    public final int tileSize = defaultTileSize * scale;
    public final int numOfTilesHorizontal = 20;
    public final int numOfTilesVertical = 14;
    final int screenWidth = numOfTilesHorizontal * tileSize;
    final int screenHeight = numOfTilesVertical * tileSize;

    public BoardUI() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
    }

    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    TileHandler tileHandler = new TileHandler(this);


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

        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);

        tileHandler.drawTile(g2);

        g2.dispose();
     }

}
