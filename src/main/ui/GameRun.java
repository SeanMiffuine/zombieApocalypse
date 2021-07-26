package ui;

import model.GameData;
import java.util.Scanner;

// Runs the game as well as take in inputs form user (Main game loop).
// NOTE: some code is based off of material from class, e.g. the Space Invader Game.
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
public class GameRun {
    private GameData game;
    private Scanner input;

    // modifies: this
    // effects: begins running the main game, outputs instructions.
    public GameRun() {
        System.out.println("Test version of game. Only data inputs.");
        System.out.println("WASD to move. M to shoot.");
        System.out.println("For demo purpose, any other input is treated as no action.");
        System.out.println("Input your move and press Enter");
        game = new GameData();
        gameRunLoop();
    }

    // modifies: this
    // effects: Runs main game loop. Updates game according to inputs and time elapsed.
    public void gameRunLoop() {
        // KeyListener concept from SpaceInvader
        while (true) {
            //addKeyListener(new KeyHandler()); not applicable inputs
            takeInput();
            game.update();
            printData();
            try {
                Thread.sleep(42);
            } catch (InterruptedException e) {
                System.out.println("Not supposed to fail, just trying to make compiler happy.");
            }
        }
    }

    // requires: single character keyboard input
    // modifies: this
    // effects: takes in keyboard input to control player.
    private void takeInput() {
        input = new Scanner(System.in);
        String in = input.next();
        game.doInput(in);
    }

    // effects: prints out data from GameData
    public void printData() {
        System.out.println("");
        System.out.println(game.pushDataPlayer());
        System.out.println(game.pushDataGame());
    }

    // not applicable inputs
    /*
    // effects: takes in inputs from player to move / shoot / interact.
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }
    */
}
