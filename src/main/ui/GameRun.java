package ui;

import model.GameData;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Runs the game as well as take in inputs form user (Main game loop).
// NOTE: some code is based off of material from class, e.g. the Space Invader Game.
//       as well as from Json sample material
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class GameRun extends JFrame {
    private static final String ADDRESS = "./data/gamestate.json";
    private static final int TIME_TO_UPDATE = 12;

    private GameData game;
    private GameWindow gameWindow;
    private ScoreWindow scoreWindow;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // modifies: this
    // effects: begins running the main game, outputs instructions.
    public GameRun() {
        super("Kill Zombies Earn Money");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        //newOrLoad later
        jsonWriter = new JsonWriter(ADDRESS);
        jsonReader = new JsonReader(ADDRESS);
        game = new GameData();
        newOrLoad();
        gameWindow = new GameWindow(game);
        scoreWindow = new ScoreWindow(game);
        add(gameWindow);
        add(scoreWindow, BorderLayout.SOUTH);
        pack();
        addKeyListener(new KeyHandler());
        centreOnScreen();
        setVisible(true);
        // game menu new or load !!!
        gameRunLoop();
    }

//    public GameRun() {
//        System.out.println("Test version of game. Only data inputs.");
//        System.out.println("WASD to move. M to shoot. P to pause");
//        System.out.println("For demo purpose, any other input is treated as no action.");
//        System.out.println("Input your move and press Enter");
//        jsonWriter = new JsonWriter(ADDRESS);
//        jsonReader = new JsonReader(ADDRESS);
//        newOrLoad();
//        gameRunLoop();
//    }

//    //effects: chooses between fresh game or load the old game state.
//    private void newOrLoad() {
//        System.out.println("");
//        System.out.println("New Game: press N");
//        System.out.println("Load Game Game: press L");
//        input = new Scanner(System.in);
//        String in = input.next();
//        if (in.equalsIgnoreCase("l")) {
//            loadGameState();
//        } else if (in.equalsIgnoreCase("n")) {
//            System.out.println("New Game! ");
//            game = new GameData();
//        }
//    }

    private void newOrLoad() {
        Boolean menu = true;
        int choice = newOrLoadButton();
        while (menu) {
            if (choice == 0) {
                //null
                menu = false;
            } else if (choice == 1) {
                loadGameState();
                menu = false;
            }
        }
        game.setGameStart();
    }

    private int newOrLoadButton() {
        String[] options = new String[] {"New", "Load"};
        return JOptionPane.showOptionDialog(null, "Load Game or New Game?", ":)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    // modifies: this
    // effects: Runs main game loop. Updates game according to inputs and time elapsed.
    public void gameRunLoop() {
        // KeyListener concept from SpaceInvader
        while (true) {
            game.update();
            gameWindow.repaint();
            scoreWindow.update();

            //!!! Game window and score
            try {
                Thread.sleep(42);
            } catch (InterruptedException e) {
                System.out.println("Not supposed to fail, just trying to make compiler happy.");
            }
        }
    }

//    // modifies: this
//    // effects: Runs main game loop. Updates game according to inputs and time elapsed.
//    public void gameRunLoop() {
//        // KeyListener concept from SpaceInvader
//        while (true) {
//            //addKeyListener(new KeyHandler()); not applicable inputs
//            takeInput();
//            game.update();
//            printData();
//            try {
//                Thread.sleep(42);
//            } catch (InterruptedException e) {
//                System.out.println("Not supposed to fail, just trying to make compiler happy.");
//            }
//        }
//    }

    // requires: single character keyboard input
    // modifies: this
    // effects: takes in keyboard input to control player.
//    private void takeInput() {
//        input = new Scanner(System.in);
//        String in = input.next();
//        if (in.equalsIgnoreCase("p")) {
//            System.out.println("Game Paused. P to resume. O to save and quit.");
//            in = input.next();
//            if (in.equalsIgnoreCase("o")) {
//                saveGameState();
//                System.exit(0);
//            }
//        }
//        game.doInput(in);
//    }

//    // effects: prints out data from GameData
//    public void printData() {
//        System.out.println("");
//        System.out.println(game.pushDataPlayer());
//        System.out.println(game.pushDataGame());
//    }

    //effects: saves the current game state
    private void saveGameState() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Save successful! ");
            saveButtonConfirm();
        } catch (FileNotFoundException e) {
            System.out.println("Save Failed. ");
        }
    }

    private void saveButtonConfirm() {
        JOptionPane.showMessageDialog(null, "Save Success!", "Save",
                JOptionPane.INFORMATION_MESSAGE);
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

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }


    // effects: takes in inputs from player to move / shoot / interact.
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_N && game.isGameOver()) {
                game.restart();
            } else if (e.getKeyCode() == KeyEvent.VK_P && !game.isGameOver()) {
                saveGameState();
            }
            game.keyPressed(e.getKeyCode());
        }
    }

}
