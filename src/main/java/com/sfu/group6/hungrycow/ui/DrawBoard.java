package com.sfu.group6.hungrycow.ui;

import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.factory.HungryCowBoardFactory;
import com.sfu.group6.hungrycow.ui.tile.Tile;
import com.sfu.group6.hungrycow.ui.tile.InanimateResourceLoaderUtil;

import java.awt.Graphics2D;


public class DrawBoard {
    private final int[][] boardData;
    private final BoardUI ui;
    private final Board board;

    private final Tile[] tileInanimates;
    private final Tile[] tileAnimates;

    public DrawBoard(BoardUI ui,
                     Board boardCreated,
                     int[][] boardData) {
        this.ui = ui;
        this.boardData = boardData;
        this.tileInanimates = InanimateResourceLoaderUtil.getTileImages();
        this.tileAnimates = AnimateResourceLoaderUtil.getAnimateImages();
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

        for (int row = 0; row < BoardUI.numOfTilesVertical; row++) {

            for (int col = 0; col < BoardUI.numOfTilesHorizontal; col++) {

                int tileData = boardData[row][col];
                int tileSize = BoardUI.tileSize;
                int tilePositionX = col * BoardUI.tileSize;
                int tilePositionY = row * BoardUI.tileSize;
                if (tileData != HungryCowBoardFactory.COW && tileData != HungryCowBoardFactory.FARMER
                    && tileData != HungryCowBoardFactory.OBJECTIVES && tileData != HungryCowBoardFactory.BONUS_REWARD) {
                    g.drawImage(tileInanimates[tileData].image,
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
     *
     * @param g a Graphics2D object
     */
    private void drawGrass(Graphics2D g) {
        for (int i = 0; i < BoardUI.numOfTilesHorizontal; i++) {
            int grassPositionX = i * BoardUI.tileSize;
            for (int j = 0; j < BoardUI.numOfTilesVertical; j++) {
                int grassPositionY = j * BoardUI.tileSize;
                g.drawImage(tileInanimates[0].image,
                            grassPositionX,
                            grassPositionY,
                            BoardUI.tileSize,
                            BoardUI.tileSize,
                            null);
            }
        }
    }

    /**
     * Draws the player on a Graphics2D object.
     *
     * @param g2 a Graphics2D object
     */
    public void drawPlayer(Graphics2D g2) {
        switch (board.getPlayer()
                     .getFacingDirection()) {
            case UP -> g2.drawImage(tileAnimates[0].image,
                                    board.getPlayer()
                                         .getPosition()
                                         .getX() * BoardUI.tileSize,
                                    board.getPlayer()
                                         .getPosition()
                                         .getY() * BoardUI.tileSize,
                                    BoardUI.tileSize,
                                    BoardUI.tileSize,
                                    null);
            case DOWN -> g2.drawImage(tileAnimates[1].image,
                                      board.getPlayer()
                                           .getPosition()
                                           .getX() * BoardUI.tileSize,
                                      board.getPlayer()
                                           .getPosition()
                                           .getY() * BoardUI.tileSize,
                                      BoardUI.tileSize,
                                      BoardUI.tileSize,
                                      null);
            case LEFT -> {
                if (ui.spriteNumber == 1) {
                    g2.drawImage(tileAnimates[2].image,
                                 board.getPlayer()
                                      .getPosition()
                                      .getX() * BoardUI.tileSize,
                                 board.getPlayer()
                                      .getPosition()
                                      .getY() * BoardUI.tileSize,
                                 BoardUI.tileSize,
                                 BoardUI.tileSize,
                                 null);
                }
                if (ui.spriteNumber == 2) {
                    g2.drawImage(tileAnimates[3].image,
                                 board.getPlayer()
                                      .getPosition()
                                      .getX() * BoardUI.tileSize,
                                 board.getPlayer()
                                      .getPosition()
                                      .getY() * BoardUI.tileSize,
                                 BoardUI.tileSize,
                                 BoardUI.tileSize,
                                 null);
                }
            }
            case RIGHT -> {
                if (ui.spriteNumber == 1) {
                    g2.drawImage(tileAnimates[4].image,
                                 board.getPlayer()
                                      .getPosition()
                                      .getX() * BoardUI.tileSize,
                                 board.getPlayer()
                                      .getPosition()
                                      .getY() * BoardUI.tileSize,
                                 BoardUI.tileSize,
                                 BoardUI.tileSize,
                                 null);
                }
                if (ui.spriteNumber == 2) {
                    g2.drawImage(tileAnimates[5].image,
                                 board.getPlayer()
                                      .getPosition()
                                      .getX() * BoardUI.tileSize,
                                 board.getPlayer()
                                      .getPosition()
                                      .getY() * BoardUI.tileSize,
                                 BoardUI.tileSize,
                                 BoardUI.tileSize,
                                 null);
                }
            }
        }
    }

    /**
     * Draws the enemy on a Graphics2D object.
     *
     * @param g2 a Graphics2D object
     */
    public void drawEnemy(Graphics2D g2) {
        for (var enemy : board.getEnemies()) {
            switch (enemy.getFacingDirection()) {
                case UP, LEFT -> {
                    if (ui.spriteNumber == 1) {
                        g2.drawImage(tileAnimates[6].image,
                                     enemy.getPosition()
                                          .getX() * BoardUI.tileSize,
                                     enemy.getPosition()
                                          .getY() * BoardUI.tileSize,
                                     BoardUI.tileSize,
                                     BoardUI.tileSize,
                                     null);
                    }
                    if (ui.spriteNumber == 2) {
                        g2.drawImage(tileAnimates[7].image,
                                     enemy.getPosition()
                                          .getX() * BoardUI.tileSize,
                                     enemy.getPosition()
                                          .getY() * BoardUI.tileSize,
                                     BoardUI.tileSize,
                                     BoardUI.tileSize,
                                     null);
                    }
                }
                case DOWN, RIGHT -> {
                    if (ui.spriteNumber == 1) {
                        g2.drawImage(tileAnimates[8].image,
                                     enemy.getPosition()
                                          .getX() * BoardUI.tileSize,
                                     enemy.getPosition()
                                          .getY() * BoardUI.tileSize,
                                     BoardUI.tileSize,
                                     BoardUI.tileSize,
                                     null);
                    }
                    if (ui.spriteNumber == 2) {
                        g2.drawImage(tileAnimates[9].image,
                                     enemy.getPosition()
                                          .getX() * BoardUI.tileSize,
                                     enemy.getPosition()
                                          .getY() * BoardUI.tileSize,
                                     BoardUI.tileSize,
                                     BoardUI.tileSize,
                                     null);
                    }
                }
            }
        }

    }

    /**
     * Draws the bonus rewards on a Graphics2D object.
     *
     * @param g2 a Graphics2D object
     */
    public void drawBonusReward(Graphics2D g2) {
        for (var BonusReward : board.getBonus()) {
            g2.drawImage(tileAnimates[12].image,
                         BonusReward.getPosition()
                                    .getX() * BoardUI.tileSize,
                         BonusReward.getPosition()
                                    .getY() * BoardUI.tileSize,
                         BoardUI.tileSize,
                         BoardUI.tileSize,
                         null);
        }
    }

    /**
     * Draws all objectives on a Graphics2D object.
     *
     * @param g2 a Graphics2D object
     */
    public void drawObjective(Graphics2D g2) {
        for (var RegularReward : board.getObjectives()) {
            g2.drawImage(tileAnimates[13].image,
                         RegularReward.getPosition()
                                      .getX() * BoardUI.tileSize,
                         RegularReward.getPosition()
                                      .getY() * BoardUI.tileSize,
                         BoardUI.tileSize,
                         BoardUI.tileSize,
                         null);
        }
    }

    /**
     * Draws the end space gate on a Graphics2D object.
     *
     * @param g2 a Graphics2D object
     */
    public void drawGate(Graphics2D g2) {
        if (board.getObjectives()
                 .isEmpty()) {
            g2.drawImage(tileAnimates[11].image,
                         board.getEndSpace()
                              .getX() * BoardUI.tileSize,
                         board.getEndSpace()
                              .getY() * BoardUI.tileSize,
                         BoardUI.tileSize,
                         BoardUI.tileSize,
                         null);
        } else {
            g2.drawImage(tileAnimates[10].image,
                         board.getEndSpace()
                              .getX() * BoardUI.tileSize,
                         board.getEndSpace()
                              .getY() * BoardUI.tileSize,
                         BoardUI.tileSize,
                         BoardUI.tileSize,
                         null);
        }
    }

}