package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

// Contains all of current game data. Updates according to user inputs from GameRun.
public class GameData {

    private Player player;
    private int points;
    private int ammo;
    private boolean gameOver;

    private List<Bullet> bullets;
    private List<Enemy> enemies;

    // effects: initializes the game data; Players, enemies, score, points, etc.
    public GameData() {
        
    }

    // modifies: this
    // effects: updates the game data as time goes on. All movements,
    public void update() {
        bulletUpdate();
        enemyUpdate();
        playerUpdate();

        bulletOutOfBounds();
        isKill();
        nextRound();
        gameOverScene();

    }

    private void nextRound() {
    }

    private void isKill() {
    }

    private void bulletOutOfBounds() {
    }

    private void playerUpdate() {
    }

    private void enemyUpdate() {
    }

    private void bulletUpdate() {
    }

    private void gameOverScene() {
    }

    // effects: prints all game data to display for user.
    public void printData() {

    }

    // FROM CLASS EXAMPLE SPACE INVADER
    // modifies: this
    // effects: changes player position depending on user input. Space to shoot,
    // P to restart, ESCAPE to exit the game.
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            // shoot stuff. REQUIRES AMMO.
        } else if (keyCode == KeyEvent.VK_P && gameOver) {
            //restart
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else {
            //playerControl(keyCode);
            // control player
        }

    }
}
