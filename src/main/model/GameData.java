package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

// Contains all of current game data. Updates according to user inputs from GameRun.
// NOTE: some code is based off of material from class, e.g. the Space Invader Game.
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
public class GameData {
    public static final int WINDOW_WIDTH = 250;
    public static final int WINDOW_HEIGHT = 250;
    public static final int BORDER = 50;

    private int money;
    private int round;
    private boolean gameOver;
    private Player player;
    private List<Bullet> bullets;
    private List<Enemy> enemies;

    // effect: gets window width
    public int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    // effect: gets window height
    public int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    // effect: gets border length
    public int getBorder() {
        return BORDER;
    }

    // effect: gets money
    public int getMoney() {
        return money;
    }

    // effect: gets round number
    public int getRound() {
        return round;
    }

    // effect: gets game state
    public boolean isGameOver() {
        return gameOver;
    }

    // effect: gets player object
    public Player getPlayer() {
        return player;
    }

    // effect: gets active bullet list
    public List<Bullet> getBullets() {
        return bullets;
    }

    // effect: gets active enemies
    public List<Enemy> getEnemies() {
        return enemies;
    }

    // effects: initializes the game data; Player, enemies, rounds, money, bullets in game, enemies in game.
    public GameData() {
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        round = 0;
        money = 0;
        gameOver = false;
        player = new Player();

    }

    // modifies: this
    // effects: updates the game data as time goes on. Updates movements of all sprites,
    // checks for kills, boundaries, game over, and next rounds.
    public void update() {
        if (!gameOver) {
            spawn();
            bulletUpdate();
            enemyUpdate();
            playerGetHit();
            enemyGetHit();
            setGameOver();
        }
    }

    // effects: returns data relating to the player object
    public String pushDataPlayer() {
        return "health: " + player.getHealth() + " ammo: " + player.getAmmo()
                + " playerX: " + player.getPositionX() + " playerY: " + player.getPositionY();
    }

    // effects: returns data relating to GameData
    public String pushDataGame() {
        String enemyPositions = "";
        String bulletPositions = "";

        if (!gameOver) {
            for (Enemy e : enemies) {
                enemyPositions += " X: " + e.getPositionX() + " Y: " + e.getPositionY() + " | ";
            }
            for (Bullet b : bullets) {
                bulletPositions += " X: " + b.getPositionX() + " Y: " + b.getPositionY() + " | ";
            }

            return "Enemy Total: " + enemies.size() + " Enemy Coords: " + enemyPositions
                    + "\n" + "Flying Bullets Total: " + bullets.size() + " Bullet Coords:"
                    + bulletPositions;
        } else {
            return "Game over. P for restart.";
        }
    }

    // requires: single character input string
    // modifies: this
    // effects: takes in keyboard input and responds with appropriate action
    public void doInput(String input) {
        if (!gameOver) {
            if (input.equalsIgnoreCase("m")) {
                shoot();
                player.shoot();
            }
            player.playerMove(input);
        } else {
            if (input.equalsIgnoreCase("p")) {
                restart();
            }
        }
    }

    /*
    // FROM CLASS EXAMPLE SPACE INVADER
    // modifies: this
    // effects: changes player position depending on user input. Space to shoot,
    // P to restart, ESCAPE to exit the game, WASD for movements
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            player.shoot();
            shoot();
        } else if (keyCode == KeyEvent.VK_P && gameOver) {
            restart();
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else {
            player.playerMove(keyCode);
        }
    }*/

    // modifies: this
    // effects: spawn multiple enemies according to the round number, exponentially increases. 3 ^ round #
    private void spawn() {
        if (nextRound()) {
            this.round++;
            int amount = (int) Math.pow(3,this.round);
            for (int i = 0; i < amount; i++) {
                enemies.add(new Enemy());
            }
        }
    }

    // modifies: this
    //effects: detect if enemy is touching player; if so, enemy dies, and player loses health
    private void playerGetHit() {
        //if enemy touches u,they die, but u lose like health.
        List<Enemy> deadEnemies = new ArrayList<Enemy>();

        for (Enemy e: enemies) {
            if (e.hitPlayer(player)) {
                deadEnemies.add(e);
                player.loseHealth();
            }
        }
        enemies.removeAll(deadEnemies);
    }

    // modifies: this
    // effects: if enemy is hit by any bullet existing on the playing field, both bullet and enemy will be removed
    private void enemyGetHit() {
        List<Enemy> deadEnemies = new ArrayList<Enemy>();
        List<Bullet> shotBullets = new ArrayList<Bullet>();

        for (Enemy e : enemies) {
            for (Bullet b : bullets) {
                if (e.getShot(b)) {
                    deadEnemies.add(e);
                    shotBullets.add(b);
                }
            }
        }
        enemies.removeAll(deadEnemies);
        bullets.removeAll(shotBullets);
    }

    // effects: returns true if all enemies have been eliminated.
    private boolean nextRound() {
        return enemies.isEmpty();
    }

    // modifies: this
    // effects: if bullet travels out of the boundaries of the game screen, then remove the bullet.
    private void bulletOutOfBounds() {
        List<Bullet> shotBullets = new ArrayList<Bullet>();
        for (Bullet b : bullets) {
            if (b.bulletOutOfBounds()) {
                shotBullets.add(b);
            }
        }
        bullets.removeAll(shotBullets);
    }

    // modifies: this
    // effects: updates the positions of all enemies on the screen
    private void enemyUpdate() {
        for (Enemy e : enemies) {
            e.chase(player);
        }
    }

    // modifies: this
    // effects: updates the positions of all bullets on the screen
    private void bulletUpdate() {
        for (Bullet b : bullets) {
            b.bulletMove();
        }
        bulletOutOfBounds();
    }

    // modifies: this
    // effects: if player has no more health, make it game over.
    private void setGameOver() {
        if (player.noHealth()) {
            gameOver = true;
        }
    }

    // modifies: this
    // effects: shoots bullet if there is still ammo on the player; shoots in specified direction.
    private void shoot() {
        if (player.getAmmo() != 0) {
            Bullet bullet = new Bullet(player.getPositionX(), player.getPositionY(), player.getDirection());
            bullets.add(bullet);
        }
    }

    // modifies: this
    //effects: resets the game state back to original.
    private void restart() {
        bullets.clear();
        enemies.clear();
        round = 0;
        money = 0;
        gameOver = false;
        player = new Player();
    }


}
