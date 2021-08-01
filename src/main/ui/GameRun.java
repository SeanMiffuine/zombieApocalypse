package ui;

import model.GameData;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Runs the game as well as take in inputs form user (Main game loop).
// NOTE: some code is based off of material from class, e.g. the Space Invader Game.
//       as well as from Json sample material
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class GameRun {
    private static final String ADDRESS = "./data/gamestate.json";
    private GameData game;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // modifies: this
    // effects: begins running the main game, outputs instructions.
    public GameRun() {
        System.out.println("Test version of game. Only data inputs.");
        System.out.println("WASD to move. M to shoot. P to pause");
        System.out.println("For demo purpose, any other input is treated as no action.");
        System.out.println("Input your move and press Enter");
        jsonWriter = new JsonWriter(ADDRESS);
        jsonReader = new JsonReader(ADDRESS);
        newOrLoad();
        gameRunLoop();
    }

    //effects: chooses between fresh game or load the old game state.
    private void newOrLoad() {
        System.out.println("");
        System.out.println("New Game: press N");
        System.out.println("Load Game Game: press L");
        input = new Scanner(System.in);
        String in = input.next();
        if (in.equalsIgnoreCase("l")) {
            loadGameState();
        } else if (in.equalsIgnoreCase("n")) {
            System.out.println("New Game! ");
            game = new GameData();
        }
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
        if (in.equalsIgnoreCase("p")) {
            System.out.println("Game Paused. P to resume. O to save and quit.");
            in = input.next();
            if (in.equalsIgnoreCase("o")) {
                saveGameState();
                System.exit(0);
            }
        }
        game.doInput(in);
    }

    // effects: prints out data from GameData
    public void printData() {
        System.out.println("");
        System.out.println(game.pushDataPlayer());
        System.out.println(game.pushDataGame());
    }

    //effects: saves the current game state
    private void saveGameState() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Save successful! ");
        } catch (FileNotFoundException e) {
            System.out.println("Save Failed. ");
        }

    }

    //modifies: this
    //effects: loads the current game state.
    private void loadGameState() {
        try {
            this.game = jsonReader.read();
            System.out.println("Load Successful! ");
            System.out.println("Continue Game: ");
        } catch (IOException e) {
            System.out.println("Load failed. ");
        }
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
