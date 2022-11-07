package com.sfu.group6.hungrycow.ui;

import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.factory.HungryCowBoardFactory;
import com.sfu.group6.hungrycow.ui.tile.TileHandler;

import java.awt.Graphics2D;


public class DrawBoard {
    private final int[][] boardData;
    private final BoardUI ui;
    private final Board board;

    private final TileHandler tileHandler;
    private final AnimateHandler animateHandler;

    public DrawBoard(BoardUI ui,
                     Board boardCreated,
                     int[][] boardData) {
        this.ui = ui;
        this.boardData = boardData;
        this.tileHandler = new TileHandler(this.ui);
        this.animateHandler = new AnimateHandler(this.ui);
        this.board = boardCreated;
    }

    /**
     * Draws the static tiles defined in board data.
     * For example: fields, barriers, punishments, trees.
     *
     * @param g a Graphics2D object
     */
    public void drawTile(Graphics2D g) {
        drawGrass(g);

        for (int row = 0; row < ui.numOfTilesVertical; row++) {

            for (int col = 0; col < ui.numOfTilesHorizontal; col++) {

                int tileData = boardData[row][col];
                int tileSize = ui.tileSize;
                int tilePositionX = col * ui.tileSize;
                int tilePositionY = row * ui.tileSize;
                if (tileData != HungryCowBoardFactory.COW && tileData != HungryCowBoardFactory.FARMER
                    && tileData != HungryCowBoardFactory.OBJECTIVES && tileData != HungryCowBoardFactory.BONUS_REWARD) {
                    g.drawImage(tileHandler.tiles[tileData].image,
                                tilePositionX,
                                tilePositionY,
                                tileSize,
                                tileSize,
                                null);
                }
            }
        }
    }

    /**
     * Draws the grass background of each grid block on a Graphics2D object.
     * @param g a Graphics2D object
     */
    private void drawGrass(Graphics2D g) {
        for (int i = 0; i < ui.numOfTilesHorizontal; i++) {
            int grassPositionX = i * ui.tileSize;
            for (int j = 0; j < ui.numOfTilesVertical; j++) {
                int grassPositionY = j * ui.tileSize;
                g.drawImage(tileHandler.tiles[0].image,
                            grassPositionX,
                            grassPositionY,
                            ui.tileSize,
                            ui.tileSize,
                            null);
            }
        }
    }

    /**
     * Draws the player on a Graphics2D object.
     * @param g2 a Graphics2D object
     */
    public void drawPlayer(Graphics2D g2) {
        switch (board.getPlayer()
                     .getFacingDirection()) {
            case UP -> g2.drawImage(animateHandler.tileAnimate[0].image,
                                    board.getPlayer()
                                         .getPosition()
                                         .getX() * ui.tileSize,
                                    board.getPlayer()
                                         .getPosition()
                                         .getY() * ui.tileSize,
                                    ui.tileSize,
                                    ui.tileSize,
                                    null);
            case DOWN -> g2.drawImage(animateHandler.tileAnimate[1].image,
                                      board.getPlayer()
                                           .getPosition()
                                           .getX() * ui.tileSize,
                                      board.getPlayer()
                                           .getPosition()
                                           .getY() * ui.tileSize,
                                      ui.tileSize,
                                      ui.tileSize,
                                      null);
            case LEFT -> {
                if (ui.spriteNumber == 1) {
                    g2.drawImage(animateHandler.tileAnimate[2].image,
                                 board.getPlayer()
                                      .getPosition()
                                      .getX() * ui.tileSize,
                                 board.getPlayer()
                                      .getPosition()
                                      .getY() * ui.tileSize,
                                 ui.tileSize,
                                 ui.tileSize,
                                 null);
                }
                if (ui.spriteNumber == 2) {
                    g2.drawImage(animateHandler.tileAnimate[3].image,
                                 board.getPlayer()
                                      .getPosition()
                                      .getX() * ui.tileSize,
                                 board.getPlayer()
                                      .getPosition()
                                      .getY() * ui.tileSize,
                                 ui.tileSize,
                                 ui.tileSize,
                                 null);
                }
            }
            case RIGHT -> {
                if (ui.spriteNumber == 1) {
                    g2.drawImage(animateHandler.tileAnimate[4].image,
                                 board.getPlayer()
                                      .getPosition()
                                      .getX() * ui.tileSize,
                                 board.getPlayer()
                                      .getPosition()
                                      .getY() * ui.tileSize,
                                 ui.tileSize,
                                 ui.tileSize,
                                 null);
                }
                if (ui.spriteNumber == 2) {
                    g2.drawImage(animateHandler.tileAnimate[5].image,
                                 board.getPlayer()
                                      .getPosition()
                                      .getX() * ui.tileSize,
                                 board.getPlayer()
                                      .getPosition()
                                      .getY() * ui.tileSize,
                                 ui.tileSize,
                                 ui.tileSize,
                                 null);
                }
            }
        }
    }

    /**
     * Draws the enemy on a Graphics2D object.
     * @param g2 a Graphics2D object
     */
    public void drawEnemy(Graphics2D g2) {
        for (var enemy : board.getEnemies()) {
            switch (enemy.getFacingDirection()) {
                case UP, LEFT -> {
                    if (ui.spriteNumber == 1) {
                        g2.drawImage(animateHandler.tileAnimate[6].image,
                                     enemy.getPosition()
                                          .getX() * ui.tileSize,
                                     enemy.getPosition()
                                          .getY() * ui.tileSize,
                                     ui.tileSize,
                                     ui.tileSize,
                                     null);
                    }
                    if (ui.spriteNumber == 2) {
                        g2.drawImage(animateHandler.tileAnimate[7].image,
                                     enemy.getPosition()
                                          .getX() * ui.tileSize,
                                     enemy.getPosition()
                                          .getY() * ui.tileSize,
                                     ui.tileSize,
                                     ui.tileSize,
                                     null);
                    }
                }
                case DOWN, RIGHT -> {
                    if (ui.spriteNumber == 1) {
                        g2.drawImage(animateHandler.tileAnimate[8].image,
                                     enemy.getPosition()
                                          .getX() * ui.tileSize,
                                     enemy.getPosition()
                                          .getY() * ui.tileSize,
                                     ui.tileSize,
                                     ui.tileSize,
                                     null);
                    }
                    if (ui.spriteNumber == 2) {
                        g2.drawImage(animateHandler.tileAnimate[9].image,
                                     enemy.getPosition()
                                          .getX() * ui.tileSize,
                                     enemy.getPosition()
                                          .getY() * ui.tileSize,
                                     ui.tileSize,
                                     ui.tileSize,
                                     null);
                    }
                }
            }
        }

    }

    /**
     * Draws the bonus rewards on a Graphics2D object.
     * @param g2 a Graphics2D object
     */
    public void drawBonusReward(Graphics2D g2) {
        for (var BonusReward : board.getBonus()) {
            g2.drawImage(animateHandler.tileAnimate[12].image,
                         BonusReward.getPosition()
                                    .getX() * ui.tileSize,
                         BonusReward.getPosition()
                                    .getY() * ui.tileSize,
                         ui.tileSize,
                         ui.tileSize,
                         null);
        }
    }

    /**
     * Draws all objectives on a Graphics2D object.
     * @param g2 a Graphics2D object
     */
    public void drawObjective(Graphics2D g2) {
        for (var RegularReward : board.getObjectives()) {
            g2.drawImage(animateHandler.tileAnimate[13].image,
                         RegularReward.getPosition()
                                      .getX() * ui.tileSize,
                         RegularReward.getPosition()
                                      .getY() * ui.tileSize,
                         ui.tileSize,
                         ui.tileSize,
                         null);
        }
    }
    /**
     * Draws the end space gate on a Graphics2D object.
     * @param g2 a Graphics2D object
     */
    public void drawGate(Graphics2D g2) {
        if (board.getObjectives()
                 .isEmpty()) {
            g2.drawImage(animateHandler.tileAnimate[11].image,
                         board.getEndSpace()
                              .getX() * ui.tileSize,
                         board.getEndSpace()
                              .getY() * ui.tileSize,
                         ui.tileSize,
                         ui.tileSize,
                         null);
        } else {
            g2.drawImage(animateHandler.tileAnimate[10].image,
                         board.getEndSpace()
                              .getX() * ui.tileSize,
                         board.getEndSpace()
                              .getY() * ui.tileSize,
                         ui.tileSize,
                         ui.tileSize,
                         null);
        }
    }

}