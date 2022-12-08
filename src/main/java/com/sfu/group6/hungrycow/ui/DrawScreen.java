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

    /**
     *
     * @param g2d   a Graphics2D object
     * @param text  text to be drawn on the scene
     * @param rect  rectangle to help center the text
     * @param font  font to use to draw the text
     */
    public void drawCenteredString(Graphics g2d,
                                   String text,
                                   Rectangle rect,
                                   Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g2d.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g2d.setFont(font);
        // Draw the String
        g2d.drawString(text,
                     x,
                     y);
    }

    private static final Font bold_Font = new Font("Bold",
                                                   Font.BOLD,
                              BoardUI.tileSize * 3);
    private static final Font regular_Font = new Font("Regular",
                                                      Font.PLAIN,
                                                      BoardUI.tileSize);
    private static final Font score_Font = new Font("Score",
                                                    Font.BOLD,
                               BoardUI.tileSize * 2);


    /**
     * Draws the start screen on a JPanel.
     *
     * @param g2d  a Graphics2D object
     * @param screenWidth width of the screen
     * @param screenHeight height of the screen
     */
    public void startScreen(Graphics2D g2d,
                            int screenWidth,
                            int screenHeight) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     screenWidth,
                     screenHeight);


        g2d.setColor(Color.YELLOW);
        g2d.drawRect(screenWidth - (screenWidth - BoardUI.tileSize),
                     screenHeight - (screenHeight - BoardUI.tileSize),
                     screenWidth - (2 * BoardUI.tileSize),
                     screenHeight / 2);

        g2d.setColor(new Color(148,
                               69,
                               53));
        g2d.fillRect(screenWidth - (screenWidth - BoardUI.tileSize),
                     screenHeight - (screenHeight - BoardUI.tileSize),
                     screenWidth - (2 * BoardUI.tileSize),
                     screenHeight / 2);

        g2d.setColor(Color.ORANGE);
        g2d.setFont(bold_Font);
        g2d.drawString("Hungry Hungry Cow",
                       screenWidth - (screenWidth - (2 * BoardUI.tileSize)),
                       screenHeight / 4);

        g2d.drawString("The Hunger",
                       screenWidth / 2,
                       (screenHeight / 4) * 2);


        g2d.setColor(Color.YELLOW);

        Rectangle centerTextRect = new Rectangle(0,
                                                 screenHeight / 2,
                                                 screenWidth,
                                                 screenHeight / 4 + screenHeight / 8);
        drawCenteredString(g2d,
                           "Press Space To Play",
                           centerTextRect,
                           regular_Font);

        centerTextRect.y += screenHeight / 8;
        drawCenteredString(g2d,
                           "Press Esc to Exit",
                           centerTextRect,
                           regular_Font);

    }

    /**
     * Draws the game over screen on a JPanel.
     *
     * @param g2d  a Graphics2D object
     * @param screenWidth width of the screen
     * @param screenHeight height of the screen
     */
    public void gameOverScreen(Graphics2D g2d,
                               int screenWidth,
                               int screenHeight,
                               int score) {

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,
                     0,
                     screenWidth,
                     screenHeight);


        g2d.setColor(Color.RED);

        Rectangle centerTextRect = new Rectangle(0,
                                                 0,
                                                 screenWidth,
                                                 screenHeight / 2);
        drawCenteredString(g2d,
                           "Game Over",
                           centerTextRect,
                           bold_Font);


        g2d.setColor(Color.ORANGE);

        centerTextRect.y = screenHeight / 4;
        centerTextRect.height = screenHeight / 4;

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
        centerTextRect.y += screenHeight / 8;

        drawCenteredString(g2d,
                           "Score: " + scoreStr,
                           centerTextRect,
                           regular_Font);

        centerTextRect.y = screenHeight / 2;
        centerTextRect.height = screenHeight / 2;
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
     * @param screenWidth width of the screen
     * @param screenHeight height of the screen
     */
    public void victoryScreen(Graphics2D g2d,
                              int screenWidth,
                              int screenHeight,
                              int score) {

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     screenWidth,
                     screenHeight);


        g2d.setColor(Color.GREEN);

        Rectangle centerTextRect = new Rectangle(0,
                                                 0,
                                                 screenWidth,
                                                 screenHeight / 2);
        drawCenteredString(g2d,
                           "You Win!",
                           centerTextRect,
                           bold_Font);

        String scoreStr = Integer.toString(score);
        centerTextRect.y += screenHeight / 8;
        drawCenteredString(g2d,
                           "Score: " + scoreStr,
                           centerTextRect,
                           score_Font);

        g2d.setColor(Color.YELLOW);

        centerTextRect.y = screenHeight / 2;
        drawCenteredString(g2d,
                           "Press Space to Exit.",
                           centerTextRect,
                           regular_Font);
    }

    /**
     * Draws the pause screen on a JPanel.
     *
     * @param g2d a Graphics2D object
     * @param screenWidth width of the screen
     * @param screenHeight height of the screen
     */
    public void pauseScreen(Graphics2D g2d,
                            int screenWidth,
                            int screenHeight) {

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,
                     0,
                     screenWidth,
                     screenHeight);

        g2d.setColor(Color.BLACK);

        Rectangle centerTextRect = new Rectangle(0, 0, screenWidth, screenHeight / 2);
        drawCenteredString(g2d, "PAUSED", centerTextRect, bold_Font);

        g2d.setColor(Color.YELLOW);

        centerTextRect.y = screenHeight/2;
        drawCenteredString(g2d, "Press Space to Resume.", centerTextRect, regular_Font);

        centerTextRect.y += screenHeight/8;
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
