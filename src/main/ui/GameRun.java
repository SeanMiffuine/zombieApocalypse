package ui;

import model.GameData;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

// Runs the game as well as take in inputs form user (Main game loop).
public class GameRun {
    private GameData game;

    // effects: begins running the main game
    public GameRun() {
        System.out.println("Test version of game. Only data inputs.");
        System.out.println("WASD to move. Space to shoot. E to interact");
        gameRunLoop();
    }

    // effects: Runs main game loop. Updates game according to inputs and time elapsed.
    public void gameRunLoop() {
        // KeyListener concept from SpaceInvader

        addKeyListener(new KeyHandler());
        game.update();
        printData();
        try {
            Thread.sleep(32);
        } catch (InterruptedException e) {
            System.out.println("Not supposed to fail, just trying to make compiler happy.");
        }
    }

    public void printData() {
        // !!!
    }
    // FROM CLASS EXAMPLE SPACE INVADER
    // effects: takes in inputs from player to move / shoot / interact.
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }
}
