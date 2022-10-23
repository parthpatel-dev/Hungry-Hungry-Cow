package com.sfu.group6.hungrycow.model.driver;

// Some packages are unused since I don't know if we want to implement a check for what a space has and how
// we might do it if we do
// Currently as a place holder, just using Space class to hold where things might be
import java.util.Random;
import java.util.Scanner;

import com.sfu.group6.hungrycow.model.Space;
import com.sfu.group6.hungrycow.model.animate.Enemy;
import com.sfu.group6.hungrycow.model.animate.Player;
import com.sfu.group6.hungrycow.model.inanimate.BonusReward;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import com.sfu.group6.hungrycow.model.inanimate.RegularReward;

public class Board {

    private Space[][] spaces;
    private Space startSpace;
    private Space endSpace;
    private Player cow;
    private Space[] enemies;
    private Space[] objectives;
    private Space[] punishments;
    private Space[] bonus;
    private int width;

    /**
     * @breif sets width to 15
     *        Do we even need this? 
     */
    public void generateWidth()
    {
        width = 15; // do we even need to generate a width?
    }

    /**
     * @breif Generates a list inside a list, basically our map
     * @param width Width of our map
     */
    public void generateSpaces(int width)
    {
        spaces = new Space[width][width];
    }

    /**
     * @breif Takes the input, either 1 or 0, and returns the left or right side of the map
     * 
     * @param num
     * @return width - 1 (the right side of the map)
     */
    private int LeftOrRight(int num) 
    {
        if (num == 0) return 0;

        else return width - 1;
    }


    /**
     * @breif Generates a start space of the edge of the map
     * 
     * TODO: Forgot to check if the space spawns on the edge
     */
    public void generateStartSpace()
    {
        Random randomNum = new Random();
        int x = randomNum.nextInt(2);
        int y = randomNum.nextInt(width);    
        x = LeftOrRight(x);
        startSpace = spaces[x][y];
        generatePlayer(x,y);
    }

    /**
     * @breif Generates a end space on the edge of the map. If the chosen tile is 
     *        the start space, it will place the end space below it
     * 
     * TODO: Forgot to check if the space spawns on the edge
     * 
     * @param startSpace
     */
    public void generateEndSpace(Space startSpace)
    {
        Random randomNum = new Random();
        int x = randomNum.nextInt(2);
        int y = randomNum.nextInt(width);
        x = LeftOrRight(x);
        if (spaces[x][y] == startSpace)
        {
            endSpace = spaces[x][y - 1];
        }
        else
        {
            endSpace = spaces[x][y];
        }   
    }

    /**
     * @breif Generates enemies around the map that hunts the player
     * 
     * TODO: Decide on a way to check if a tile is occupied by something else
     * 
     * @param numEnemies Number of enemies wanted
     */
    public void generateEnemies(int numEnemies)
    {
        enemies = new Space[numEnemies];

        for (int i = 0; i < numEnemies; i++)
        {
            Random randx = new Random();
            Random randy = new Random();
            int x = randx.nextInt(width - 1) + 1;
            int y = randy.nextInt(width - 1) + 1;
            // NO check if space is a barrier or has something on it already
            // Do we have a check in the space if it is a game over or punishment?
            enemies[i] = spaces[x][y];
        }

    }

    /**
     * @breif Generates objectives around the map for the player to collect
     * 
     * TODO: Decide on a way to check if a tile is occupied by something else
     * 
     * @param numObjectives
     */
    public void generateObjectives(int numObjectives)
    {
        objectives = new Space[numObjectives];

        for (int i = 0; i < numObjectives; i++)
        {
            Random randx = new Random();
            Random randy = new Random();
            int x = randx.nextInt(width - 1) + 1;
            int y = randy.nextInt(width - 1) + 1;
            // NO check if space is a barrier or has something on it already
            // Do we have a check in the space if it is a game over or punishment?
            objectives[i] = spaces[x][y];
        }
    }

    /**
     * @breif Generates punishments around the maps
     * 
     * TODO: Decide on a way to check if a tile is occupied by something else
     * 
     * @param numPunishments
     */
    public void generatePunishment(int numPunishments)
    {
        punishments = new Space[numPunishments];

        for (int i = 0; i < numPunishments; i++)
        {
            Random randx = new Random();
            Random randy = new Random();
            int x = randx.nextInt(width - 1) + 1;
            int y = randy.nextInt(width - 1) + 1;
            // NO check if space is a barrier or has something on it already
            // Do we have a check in the space if it is a game over or punishment?
            punishments[i] = spaces[x][y];
        }
    }

    /**
     * @breif Places bonuses around the map
     * 
     * TODO: Decide on a way to check if a tile is occupied by something else
     * 
     * @param numBonus number of bonuses wanted on the map
     */
    public void generateBonus(int numBonus)
    {
        bonus = new Space[numBonus];

        for (int i = 0; i < numBonus; i++)
        {
            Random randx = new Random();
            Random randy = new Random();
            int x = randx.nextInt(width - 1) + 1;
            int y = randy.nextInt(width - 1) + 1;
            // NO check if space is a barrier or has something on it already
            // Do we have a check in the space if it is a game over or punishment?
            bonus[i] = spaces[x][y];
        }
    }

    /**
     * @breif Places player to the left or right of the start tile 
     *        depending on which side the tile is placed.
     * 
     * @param x x direction
     * @param y y direction
     */
    public void generatePlayer(int x, int y)
    {
        if (x == 0)
        {
            cow.getPosition().setX(x+1);
            cow.getPosition().setY(y);
        }
        else
        {
            cow.getPosition().setX(x);
            cow.getPosition().setY(y);
        }
    }

    /**
     * @brief Sets the edge of the map to barrier tile other than the start and end tile
     * 
     * @param width width of the map
     */
    public void generateBarrierEdge(int width)
    {
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < width; y++)
            {
                // sets spaces[x][y].barrier = true
                // unsure if it is possible right now, want to check with others
            }
        }
    }
    
    /**
     * @breif Calls all the generation functions to make our map
     */
    public void generateBoardInstance()
    {
        Scanner scan = new Scanner(System.in);
        generateWidth();
        generateStartSpace();
        generateSpaces(width);
        System.out.print("Enter number of punishments wanted: ");  
        int input = scan.nextInt();  
        generatePunishment(input);
        System.out.print("Enter number of objectives wanted: ");  
        input = scan.nextInt();
        generateObjectives(input);
        System.out.print("Enter number of enemies wanted: ");  
        input = scan.nextInt();
        generateEnemies(input);
        generateEndSpace(startSpace);
        generateBonus(width);
        generateBarrierEdge(width);
        scan.close();


    }
    
}
