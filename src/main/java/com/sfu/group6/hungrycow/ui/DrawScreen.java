package com.sfu.group6.hungrycow.ui;

import com.sfu.group6.hungrycow.driver.BoardUI;

import java.awt.*;

public class DrawScreen {



    public static void startScreen(Graphics2D g2d, int tileSize, int xNum, int yNum)
    {
        g2d.setBackground(Color.WHITE);

        Font bold_Font = new Font("Bold", Font.BOLD, tileSize * 3);
        Font regular_Font = new Font("Regular", Font.PLAIN, tileSize);

        g2d.setColor(Color.YELLOW);
        g2d.drawRect((xNum - (xNum - 1)) * tileSize, (yNum - (yNum - 2)) * tileSize,
                (xNum - 2) * tileSize, (yNum/2) * tileSize);

        g2d.setColor(new Color(148,69,53));
        g2d.fillRect((xNum - (xNum - 1)) * tileSize, (yNum - (yNum - 2)) * tileSize,
                (xNum - 2) * tileSize, (yNum/2) * tileSize);

        g2d.setColor(Color.ORANGE);
        g2d.setFont(bold_Font);
        g2d.drawString("Hungry Hungry",xNum/2, yNum/4);
        g2d.drawString("Cow", xNum/2,(yNum/2) - (yNum/4));

        g2d.setColor(Color.YELLOW);
        g2d.setFont(regular_Font);

        g2d.drawString("Press Start To Play",xNum/2, yNum - 4);
        g2d.drawString("Press Esc to Escape", xNum/2,yNum - 2);

    }

    public void gameOverScreen(Graphics2D g2d, int tileSize, int xNum, int yNum, Screen state)
    {

        g2d.setBackground(Color.BLACK);

        Font bold_Font = new Font("Bold", Font.BOLD, tileSize * 3);
        Font regular_Font = new Font("Regular", Font.PLAIN, tileSize);

        g2d.setFont(bold_Font);
        g2d.setColor(Color.RED);
        g2d.drawString("Game Over", (xNum/2)*tileSize, (yNum/4)*tileSize);

        g2d.setFont(regular_Font);
        g2d.setColor(Color.ORANGE);
        g2d.drawString("You got caught by a Farmer", (xNum/2)*tileSize, (yNum/2)*tileSize);

        // draw score

        g2d.setColor(Color.YELLOW);
        g2d.drawString("Press Space to exit.", (xNum/2)*tileSize, (yNum/2 + yNum/4)*tileSize);
    }

    public static void victoryScreen(Graphics2D g2d, int tileSize, int xNum, int yNum)
    {

        Font bold_Font = new Font("Bold", Font.BOLD, tileSize * 3);
        Font regular_Font = new Font("Regular", Font.PLAIN, tileSize);

        g2d.setBackground(Color.WHITE);

        g2d.setFont(bold_Font);
        g2d.setColor(Color.GREEN);
        g2d.drawString("You Win!", (xNum/2)*tileSize, (yNum/4)*tileSize);

        g2d.setFont(regular_Font);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Press Space to exit.", (xNum/2)*tileSize, (yNum/2 + yNum/4)*tileSize);
    }

    public static void pauseScreen(Graphics2D g2d, int tileSize, int xNum, int yNum)
    {

        g2d.setBackground(Color.GRAY);

        Font bold_Font = new Font("Bold", Font.BOLD, tileSize * 3);
        Font regular_Font = new Font("Regular", Font.PLAIN, tileSize);

        g2d.setFont(bold_Font);
        g2d.setColor(Color.BLACK);
        g2d.drawString("PAUSED", (xNum/2)*tileSize, (yNum/4)*tileSize);

        g2d.setFont(regular_Font);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Press Space to resume.", (xNum/2)*tileSize, (yNum/2 + yNum/4)*tileSize);

        g2d.drawString("Press Esc to exit.", (xNum/2)*tileSize, (yNum/2 + yNum/4 + yNum/8)*tileSize);
    }
}
