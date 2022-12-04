package com.sfu.group6.hungrycow.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * DrawScreen is a utility function that encapsulates the draw screen logic for BoardUI.
 */
public class DrawScreen {

    public void drawCenteredString(Graphics g,
                                   String text,
                                   Rectangle rect,
                                   Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text,
                     x,
                     y);
    }

    Font bold_Font = new Font("Bold",
                              Font.BOLD,
                              BoardUI.tileSize * 3);
    Font regular_Font = new Font("Regular",
                                 Font.PLAIN,
                                 BoardUI.tileSize);
    Font score_Font = new Font("Score",
                               Font.BOLD,
                               BoardUI.tileSize * 2);


    /**
     * Draws the start screen on a JPanel.
     *
     * @param g2d  a Graphics2D object
     * @param xNum the x coordinate to draw in
     * @param yNum the y coordinate to draw in
     */
    public void startScreen(Graphics2D g2d,
                            int xNum,
                            int yNum) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     xNum,
                     yNum);


        g2d.setColor(Color.YELLOW);
        g2d.drawRect(xNum - (xNum - BoardUI.tileSize),
                     yNum - (yNum - BoardUI.tileSize),
                     xNum - (2 * BoardUI.tileSize),
                     yNum / 2);

        g2d.setColor(new Color(148,
                               69,
                               53));
        g2d.fillRect(xNum - (xNum - BoardUI.tileSize),
                     yNum - (yNum - BoardUI.tileSize),
                     xNum - (2 * BoardUI.tileSize),
                     yNum / 2);

        g2d.setColor(Color.ORANGE);
        g2d.setFont(bold_Font);
        g2d.drawString("Hungry Hungry Cow",
                       xNum - (xNum - (2 * BoardUI.tileSize)),
                       yNum / 4);

        g2d.drawString("The Hunger",
                       xNum / 2,
                       (yNum / 4) * 2);


        g2d.setColor(Color.YELLOW);

        Rectangle centerTextRect = new Rectangle(0,
                                                 yNum / 2,
                                                 xNum,
                                                 yNum / 4 + yNum / 8);
        drawCenteredString(g2d,
                           "Press Space To Play",
                           centerTextRect,
                           regular_Font);

        centerTextRect.y += yNum / 8;
        drawCenteredString(g2d,
                           "Press Esc to Exit",
                           centerTextRect,
                           regular_Font);

    }

    /**
     * Draws the game over screen on a JPanel.
     *
     * @param g2d  a Graphics2D object
     * @param xNum the x coordinate to draw in
     * @param yNum the y coordinate to draw in
     */
    public void gameOverScreen(Graphics2D g2d,
                               int xNum,
                               int yNum,
                               int score) {

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,
                     0,
                     xNum,
                     yNum);


        g2d.setColor(Color.RED);

        Rectangle centerTextRect = new Rectangle(0,
                                                 0,
                                                 xNum,
                                                 yNum / 2);
        drawCenteredString(g2d,
                           "Game Over",
                           centerTextRect,
                           bold_Font);


        g2d.setColor(Color.ORANGE);

        centerTextRect.y = yNum / 4;
        centerTextRect.height = yNum / 4;

        if (score < 0) {

            drawCenteredString(g2d,
                               "You lost too many points",
                               centerTextRect,
                               regular_Font);

        } else {
            drawCenteredString(g2d,
                               "You got caught by the Farmer",
                               centerTextRect,
                               regular_Font);
        }
        // draw score
        String scoreStr = Integer.toString(score);
        centerTextRect.y += yNum / 8;

        drawCenteredString(g2d,
                           "Score: " + scoreStr,
                           centerTextRect,
                           regular_Font);

        centerTextRect.y = yNum / 2;
        centerTextRect.height = yNum / 2;
        g2d.setColor(Color.YELLOW);
        drawCenteredString(g2d,
                           "Press Space To Exit",
                           centerTextRect,
                           regular_Font);
    }

    /**
     * Draws the victory screen on a JPanel.
     *
     * @param g2d  a Graphics2D object
     * @param xNum the x coordinate to draw in
     * @param yNum the y coordinate to draw in
     */
    public void victoryScreen(Graphics2D g2d,
                              int xNum,
                              int yNum,
                              int score) {

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     xNum,
                     yNum);


        g2d.setColor(Color.GREEN);

        Rectangle centerTextRect = new Rectangle(0,
                                                 0,
                                                 xNum,
                                                 yNum / 2);
        drawCenteredString(g2d,
                           "You Win!",
                           centerTextRect,
                           bold_Font);

        String scoreStr = Integer.toString(score);
        centerTextRect.y += yNum / 8;
        drawCenteredString(g2d,
                           "Score: " + scoreStr,
                           centerTextRect,
                           score_Font);

        g2d.setColor(Color.YELLOW);

        centerTextRect.y = yNum / 2;
        drawCenteredString(g2d,
                           "Press Space to Exit.",
                           centerTextRect,
                           regular_Font);
    }

    /**
     * Draws the pause screen on a JPanel.
     *
     * @param g2d      a Graphics2D object
     * @param xNum     the x coordinate to draw in
     * @param yNum     the y coordinate to draw in
     */
    public void pauseScreen(Graphics2D g2d,
                            int xNum,
                            int yNum) {

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     xNum,
                     yNum);


        g2d.setFont(bold_Font);
        g2d.setColor(Color.BLACK);

        Rectangle centerTextRect = new Rectangle(0, 0, xNum, yNum / 2);
        drawCenteredString(g2d, "PAUSED", centerTextRect, bold_Font);

        g2d.setColor(Color.YELLOW);

        centerTextRect.y = yNum/2;
        drawCenteredString(g2d, "Press Space to Resume.", centerTextRect, regular_Font);

        centerTextRect.y += yNum/8;
        drawCenteredString(g2d, "Press Esc to Exit.", centerTextRect, regular_Font);
    }

    /**
     * Draw score counter on a JPanel.
     *
     * @param g2d      a Graphics object
     * @param tileSize an int representing the size of tile
     * @param score    an int representing the score value
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
