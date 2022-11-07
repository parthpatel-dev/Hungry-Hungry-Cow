package com.sfu.group6.hungrycow.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * MapLoader is a utility class that reads a text file of integers that represents a board map.
 */
public class MapLoader {
    /**
     * Loads board data from a text file.
     * The text file is space separated integers.
     * It has n amount of lines equal to height.
     * Each line has m amount of integers equal to width.
     *
     * @param filePath a path to a text file containing board data
     * @param width the amount of integers in each line
     * @param height the number of lines in the text file
     * @return a 2-dimensional integer array
     * @throws IOException when path file cannot be read
     */
    public int[][] loadBoard(String filePath,
                             int width,
                             int height) throws IOException {
        InputStream stream = Objects.requireNonNull(getClass().getResourceAsStream(filePath));
        int[][] boardData = new int[height][width];

        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(stream));

        for (int row = 0; row < height; row++) {
            String line = bufferReader.readLine();
            String[] rowData = line.split(" ");

            for (int col = 0; col < width; col++) {
                int tileData = Integer.parseInt(rowData[col]);
                boardData[row][col] = tileData;
            }
        }
        bufferReader.close();
        return boardData;
    }
}
