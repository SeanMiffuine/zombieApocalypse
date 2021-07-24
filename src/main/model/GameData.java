package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

// Contains all of current game data. Updates according to user inputs from GameRun.
// NOTE: some code is based off of material from class, e.g. the Space Invader Game.
public class GameData {
    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 900;
    public static final int BORDER = 50;

    private Player player;
    private int money;
    private int round;
    private boolean gameOver;
    private List<Bullet> bullets;
    private List<Enemy> enemies;

    // effects: initializes the game data; Players, enemies, score, points, etc.
    public GameData() {
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        round = 1;
        money = 1;
        gameOver = false;
        player = new Player();
    }

    // modifies: this
    // effects: updates the game data as time goes on. Updates movements of all sprites,
    // checks for kills, boundaries, game over, and next rounds.
    public void update() {
        bulletUpdate();
        enemyUpdate();
        playerGetHit();
        enemyGetHit();
        nextRound();
        gameOverScene();
    }

    //effects: detect if enemy is touching player; if so, they die, and player loses health
    private void playerGetHit() {
        //if enemy touches u,they die, but u lose like health.
        for
    }

    private void enemyGetHit() {

    }

    private void nextRound() {
    }


    private void bulletOutOfBounds() {
    }
    
    private void enemyUpdate() {
        for (Enemy enemy : enemies) {
            enemy.chase(player);
        }
    }

    private void bulletUpdate() {
        bulletOutOfBounds();
    }

    private void gameOverScene() {
    }

    private void shoot() {
        if (player.getAmmo() != 0) {
            Bullet bullet = new Bullet(player.getPositionX(), player.getPositionY(), player.getDirection());
            bullets.add(bullet);
        }
    }


    //effects: resets the game state back to original.
    private void restart() {
        bullets.clear();
        enemies.clear();
        round = 1;
        money = 1;
        gameOver = false;
        player = new Player();
    }

    // FROM CLASS EXAMPLE SPACE INVADER
    // modifies: this
    // effects: changes player position depending on user input. Space to shoot,
    // P to restart, ESCAPE to exit the game.
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            player.shoot();
            shoot();
        } else if (keyCode == KeyEvent.VK_P && gameOver) {
            //restart
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else {
            player.playerMove(keyCode);
            // control player
        }

    }
}
