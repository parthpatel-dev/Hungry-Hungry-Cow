package com.sfu.group6.hungrycow.driver;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

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