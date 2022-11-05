package com.sfu.group6.hungrycow.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MapLoader {
    public int[][] loadBoard(String filePath, int width, int height) throws IOException {
        InputStream stream = Objects.requireNonNull(getClass().getResourceAsStream(filePath));
        int[][] boardData = new int[width + 1][height + 1];

        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(stream));

        for (int row = 0; row < height; row++) {

            String line = bufferReader.readLine();
            String[] rowData = line.split(" ");

            for (int col = 0; col < width; col++) {

                int tileData = Integer.parseInt(rowData[col]);
                boardData[col][row] = tileData;
            }
        }
<<<<<<< HEAD
=======

>>>>>>> 55f9dce8ba9ad3e8c2572cc1f118294ea5d9a67b
        bufferReader.close();
        return boardData;
    }
}
