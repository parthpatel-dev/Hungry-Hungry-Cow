package com.sfu.group6.hungrycow;

import com.sfu.group6.hungrycow.ui.BoardUI;

import javax.swing.JFrame;
import java.io.IOException;

/**
 * The main class runs the BoardUI program in a JFrame {@link JFrame}.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Hungry Hungry Cow");

        BoardUI ui = new BoardUI();
        gameWindow.add(ui);
        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }
}
