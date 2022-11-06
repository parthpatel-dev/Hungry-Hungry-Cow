package com.sfu.group6.hungrycow.driver;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.model.animate.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

public class BoardDataLoader {

    int[][] boardData;
    BoardUI ui;

    private static final int BACKGROUND = 0;
    private static final int BARRIER = 1;
    private static final int PLAYER = 3;
    private static final int ENEMY = 4;

    Set<Position> barriers = new HashSet<>();
//    ? ?
    Player.PlayerBuilder<?, ?> playerBuilder = Player.builder();

    BoardFactory boardFactory;

    public BoardDataLoader(BoardUI ui) throws IOException {
        this.ui = ui;
        boardData = new int[ui.numOfTilesHorizontal][ui.numOfTilesVertical];
        boardFactory = new BoardFactory();
    }

    public void loadBoard(String filePath) throws IOException {
        InputStream stream = getClass().getResourceAsStream(filePath);
        assert stream != null;
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(stream));

        for (int row = 0; row < ui.numOfTilesVertical; row++) {

            String line = bufferReader.readLine();
            String[] rowData = line.split(" ");

            for (int col = 0; col < ui.numOfTilesHorizontal; col++) {

                int tileData = Integer.parseInt(rowData[col]);
                boardData[col][row] = tileData;

                if (isBarrier(tileData)) {
                    Position barrierPosition = Position.builder()
                            .x(col)
                            .y(row)
                            .build();

                    barriers.add(barrierPosition);
                    boardFactory.createBarrier(barriers);
                }

                if(isPlayer(tileData)) {
                    Position playerPosition = Position.builder()
                            .x(col)
                            .y(row)
                            .build();

                    playerBuilder.position(playerPosition);
                    boardFactory.createPlayer(playerBuilder);
                }
            }
            bufferReader.close();
        }

    }

//    public boolean isBarrier(int tileData) {
//        if(tileData == 3 || tileData == 4 || tileData == 5
//                || tileData == 6 || tileData == 7 || tileData = 8
//                || tileData == 9 || tileData == 10 || tileData == 11
//                || tileData == 12) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public boolean isPlayer(int tileData) {
        if(tileData == 1) {
            return true;
        } else {
            return false;
        }
    }
}