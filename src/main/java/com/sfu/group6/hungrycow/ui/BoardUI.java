package com.sfu.group6.hungrycow.ui;


import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.factory.HungryCowAnimateFactory;
import com.sfu.group6.hungrycow.factory.HungryCowBoardFactory;
import com.sfu.group6.hungrycow.factory.HungryCowInanimateFactory;
import com.sfu.group6.hungrycow.maploader.MapLoader;
import org.apache.commons.lang3.RandomUtils;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * The BoardUI class extends a JPanel {@link JPanel} and encapsulates animation and key event logic.
 */
public class BoardUI extends JPanel implements Runnable {

    private static final int UP_KEY_CODE = 38;
    private static final int DOWN_KEY_CODE = 40;
    private static final int LEFT_KEY_CODE = 37;
    private static final int RIGHT_KEY_CODE = 39;
    private static final int SPACE_KEY_CODE = 32;
    private static final int ESC_KEY_CODE = 27;

    private static final double PERIOD = 1000000000;
    private static final double FPS = 60;

    public int spriteCounter = 0;
    public boolean startButtonPress = true;
    public int spriteNumber = 1;

    public static final int defaultTileSize = 16;
    public static final int scale = 2;
    public static final int tileSize = defaultTileSize * scale;
    public static final int numOfTilesHorizontal = 48;
    public static final int numOfTilesVertical = 24;
    public static final int screenWidth = numOfTilesHorizontal * tileSize;
    public static final int screenHeight = numOfTilesVertical * tileSize;

    private final Board board;
    private final DrawBoard drawBoard;
    private Thread gameThread;
    private final DrawScreen drawScreen;
    private Screen state;

    public BoardUI() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,
                                            screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        MapLoader mapLoader = new MapLoader();
        int[][] boardData = mapLoader.loadBoard(getRandomMapFilePath(),
                                                numOfTilesHorizontal,
                                                numOfTilesVertical);
        HungryCowBoardFactory boardFactory = new HungryCowBoardFactory();
        HungryCowAnimateFactory animateFactory = new HungryCowAnimateFactory();
        HungryCowInanimateFactory inanimateFactory = new HungryCowInanimateFactory();
        this.drawScreen = new DrawScreen();
        this.board = boardFactory.createBoard(boardData,
                                              animateFactory,
                                              inanimateFactory);
        this.drawBoard = new DrawBoard(this,
                                       this.board,
                                       boardData);

        setGameKeyListener();
        this.setFocusable(true);
        this.state = Screen.START;
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = PERIOD / FPS;
        double time = interval + System.nanoTime();
        while (gameThread != null) {
            time = tickUI(time,
                          interval);
        }
    }

    private double tickUI(double time,
                          double interval) {
        this.spriteCounter++;
        if (this.spriteCounter > 10) {
            if (this.spriteNumber == 1) {
                this.spriteNumber = 2;
            } else if (this.spriteNumber == 2) {
                this.spriteNumber = 1;
            }
            this.spriteCounter = 0;
        }
        repaint();
        sleepForTimeAfterAnimation(time);
        return time + interval;
    }

    public void sleepForTimeAfterAnimation(double time) {
        try {
            double timeLeft = time - System.nanoTime();
            timeLeft = timeLeft / 1000000;
            if (timeLeft < 0) {
                timeLeft = 0;
            }
            Thread.sleep((long) timeLeft);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isBoard() {
        return state == Screen.BOARD;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(100,
                    100,
                    tileSize,
                    tileSize);

        if (state == Screen.START) {
            drawScreen.startScreen(g2,
                                   this.screenWidth,
                                   this.screenHeight);
        } else if (startButtonPress) {
            drawBoard.drawTile(g2);
            playGame(g2);
        } //else {
//            drawScreen.startScreen(g2,
//                                   this.tileSize,
//                                   this.numOfTilesHorizontal,
//                                   this.numOfTilesVertical);
//
//        }

        g2.dispose();
    }

    private void playGame(Graphics2D g2) {
        drawScreen.score(g2,
                         this.tileSize,
                         board.getPlayer()
                              .getScore());


        if (board.isPlayerWin()) {
            drawScreen.victoryScreen(g2,
                                     this.screenWidth,
                                     this.screenHeight,
                                     board.getPlayer()
                                          .getScore());
            state = Screen.GAME_WIN;
        } else if (board.isGameOver()) {
            drawScreen.gameOverScreen(g2,
                                      this.screenWidth,
                                      this.screenHeight,
                                      board.getPlayer()
                                           .getScore());
            state = Screen.GAME_OVER;
        } else if (state == Screen.PAUSE) {
            drawScreen.pauseScreen(g2,
                                   this.screenWidth,
                                   this.screenHeight);
        } else {
            drawBoard.drawPlayer(g2);
            drawBoard.drawEnemy(g2);
            drawBoard.drawBonusReward(g2);
            drawBoard.drawObjective(g2);
            drawBoard.drawGate(g2);
        }
    }

    private void setGameKeyListener() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do nothing
            }
        });
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case UP_KEY_CODE -> {
                if (isBoard()) {
                    this.board.tickBoardState(Direction.UP);
                }
            }
            case DOWN_KEY_CODE -> {
                if (isBoard()) {
                    this.board.tickBoardState(Direction.DOWN);
                }
            }
            case LEFT_KEY_CODE -> {
                if (isBoard()) {
                    this.board.tickBoardState(Direction.LEFT);
                }
            }
            case RIGHT_KEY_CODE -> {
                if (isBoard()) {
                    this.board.tickBoardState(Direction.RIGHT);
                }
            }
            case SPACE_KEY_CODE -> {
                if (this.state.equals(Screen.START)) {
                    this.state = Screen.BOARD;
                    this.startButtonPress = true;
                } else if (this.state.equals(Screen.PAUSE)) {
                    this.state = Screen.BOARD;
                } else if (this.state.equals(Screen.GAME_OVER) || this.state.equals(Screen.GAME_WIN)) {
                    exitSuccessfully();
                }

            }
            case ESC_KEY_CODE -> {
                if (this.state.equals(Screen.BOARD)) {
                    this.state = Screen.PAUSE;
                } else {
                    exitSuccessfully();
                }
            }
        }
    }

    private void exitSuccessfully() {
        System.out.println("Game has been exited successfully");
        System.exit(0);
    }


    private String getRandomMapFilePath() {
        int randNum = RandomUtils.nextInt(1,
                                          4);
        return "/maps/map" +
               randNum +
               ".txt";
    }
}
