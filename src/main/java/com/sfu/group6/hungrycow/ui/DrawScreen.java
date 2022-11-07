package com.sfu.group6.hungrycow.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * DrawScreen is a utility function that encapsulates the draw screen logic for BoardUI.
 */
public class DrawScreen {
    /**
     * Draws the start screen on a JPanel.
     *
     * @param g2d a Graphics2D object
     * @param tileSize an int representing the size of tile
     * @param xNum the x coordinate to draw in
     * @param yNum the y coordinate to draw in
     */
    public void startScreen(Graphics2D g2d,
                            int tileSize,
                            int xNum,
                            int yNum) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     xNum * tileSize,
                     yNum * tileSize);

        Font bold_Font = new Font("Bold",
                                  Font.BOLD,
                                  tileSize * 3);
        Font regular_Font = new Font("Regular",
                                     Font.PLAIN,
                                     tileSize);

        g2d.setColor(Color.YELLOW);
        g2d.drawRect((xNum - (xNum - 1)) * tileSize,
                     (yNum - (yNum - 2)) * tileSize,
                     (xNum - 2) * tileSize,
                     (yNum / 2) * tileSize);

        g2d.setColor(new Color(148,
                               69,
                               53));
        g2d.fillRect((xNum - (xNum - 1)) * tileSize + 2,
                     (yNum - (yNum - 2)) * tileSize + 2,
                     (xNum - 2) * tileSize - 2,
                     (yNum / 2) * tileSize - 2);

        g2d.setColor(Color.ORANGE);
        g2d.setFont(bold_Font);
        g2d.drawString("Hungry Hungry Cow",
                       (xNum - (xNum - 2)) * tileSize,
                       (yNum / 4) * tileSize);
        g2d.drawString("The Hunger",
                       (xNum / 2) * tileSize,
                       (yNum / 4 + 5) * tileSize);


        g2d.setColor(Color.YELLOW);
        g2d.setFont(regular_Font);

        g2d.drawString("Press Space To Play",
                       (xNum / 2 - 5) * tileSize,
                       (yNum - 4) * tileSize);
        g2d.drawString("Press Esc to Exit",
                       (xNum / 2 - 5) * tileSize,
                       (yNum - 2) * tileSize);

    }

    /**
     * Draws the game over screen on a JPanel.
     *
     * @param g2d a Graphics2D object
     * @param tileSize an int representing the size of tile
     * @param xNum the x coordinate to draw in
     * @param yNum the y coordinate to draw in
     */
    public void gameOverScreen(Graphics2D g2d,
                               int tileSize,
                               int xNum,
                               int yNum,
                               int score) {

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,
                     0,
                     xNum * tileSize,
                     yNum * tileSize);

        Font bold_Font = new Font("Bold",
                                  Font.BOLD,
                                  tileSize * 3);
        Font regular_Font = new Font("Regular",
                                     Font.PLAIN,
                                     tileSize);

        g2d.setFont(bold_Font);
        g2d.setColor(Color.RED);
        g2d.drawString("Game Over",
                       (xNum / 2 - 8) * tileSize,
                       (yNum / 4) * tileSize);

        g2d.setFont(regular_Font);
        g2d.setColor(Color.ORANGE);

        if (score < 0) {
            g2d.drawString("You lost too many points",
                           (xNum / 2 - 5) * tileSize,
                           (yNum / 2) * tileSize);
        } else {
            g2d.drawString("You got caught by a Farmer",
                           (xNum / 2 - 6) * tileSize,
                           (yNum / 2) * tileSize);
        }
        // draw score
        String scoreStr = Integer.toString(score);
        g2d.drawString("Score: " + scoreStr,
                       (xNum / 2 - 2) * tileSize,
                       ((yNum / 4) + 3) * tileSize);

        g2d.setColor(Color.YELLOW);
        g2d.drawString("Press Space to Exit.",
                       (xNum / 2 - 4) * tileSize,
                       (yNum / 2 + 6) * tileSize);
    }

    /**
     * Draws the victory screen on a JPanel.
     *
     * @param g2d a Graphics2D object
     * @param tileSize an int representing the size of tile
     * @param xNum the x coordinate to draw in
     * @param yNum the y coordinate to draw in
     */
    public void victoryScreen(Graphics2D g2d,
                              int tileSize,
                              int xNum,
                              int yNum,
                              int score) {

        Font bold_Font = new Font("Bold",
                                  Font.BOLD,
                                  tileSize * 3);
        Font score_Font = new Font("Score",
                                   Font.BOLD,
                                   tileSize * 2);
        Font regular_Font = new Font("Regular",
                                     Font.PLAIN,
                                     tileSize);

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     xNum * tileSize,
                     yNum * tileSize);

        g2d.setFont(bold_Font);
        g2d.setColor(Color.GREEN);
        g2d.drawString("You Win!",
                       (xNum / 2 - 6) * tileSize,
                       (yNum / 4) * tileSize);

        String scoreStr = Integer.toString(score);
        g2d.setFont(score_Font);
        g2d.drawString("Score: " + scoreStr,
                       (xNum / 2 - 6) * tileSize,
                       ((yNum / 4) + (yNum / 8)) * tileSize);

        g2d.setFont(regular_Font);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Press Space to Exit.",
                       (xNum / 2 - 5) * tileSize,
                       (yNum / 2 + yNum / 4) * tileSize);
    }

    /**
     * Draws the pause screen on a JPanel.
     *
     * @param g2d a Graphics2D object
     * @param tileSize an int representing the size of tile
     * @param xNum the x coordinate to draw in
     * @param yNum the y coordinate to draw in
     */
    public void pauseScreen(Graphics2D g2d,
                            int tileSize,
                            int xNum,
                            int yNum) {

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     xNum * tileSize,
                     yNum * tileSize);

        Font bold_Font = new Font("Bold",
                                  Font.BOLD,
                                  tileSize * 3);
        Font regular_Font = new Font("Regular",
                                     Font.PLAIN,
                                     tileSize);

        g2d.setFont(bold_Font);
        g2d.setColor(Color.BLACK);
        g2d.drawString("PAUSED",
                       (xNum / 2 - 6) * tileSize,
                       (yNum / 4) * tileSize);

        g2d.setFont(regular_Font);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Press Space to Resume.",
                       (xNum / 2 - 5) * tileSize,
                       (yNum / 2 + yNum / 4) * tileSize);

        g2d.drawString("Press Esc to Exit.",
                       (xNum / 2 - 4) * tileSize,
                       (yNum / 2 + yNum / 4 + yNum / 8) * tileSize);
    }

    /**
     * Draw score counter on a JPanel.
     *
     * @param g2d a Graphics object
     * @param tileSize an int representing the size of tile
     * @param score an int representing the score value
     */
    public void score(Graphics g2d,
                      int tileSize,
                      int score) {
        Font bold_Font = new Font("Bold",
                                  Font.BOLD,
                                  tileSize);

        g2d.setFont(bold_Font);
        g2d.setColor(Color.WHITE); // Assuming board background is white

        String scoreStr = Integer.toString(score);
        g2d.drawString("Score: " + scoreStr,
                       0,
                       tileSize);
    }

}
