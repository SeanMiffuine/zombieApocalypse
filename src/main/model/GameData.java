package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Saveable;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.SortedMap;

// Contains all of current game data. Updates according to user inputs from GameRun.
// NOTE: some code is based off of material from class, e.g. the Space Invader Game.
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
public class GameData implements Saveable {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 580;
    public static final int BORDER = 50;

    private int money;
    private int round;
    private boolean gameOver;
    private Player player;
    private List<Bullet> bullets;
    private List<Enemy> enemies;

    // effects: initializes the game data; Player, enemies, rounds, money, bullets in game, enemies in game.
    public GameData() {
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        round = 0;
        money = 0;
        gameOver = true; //!!! true first
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
            return "Game over. L for restart.";
        }
    }

//    // requires: single character input string
//    // modifies: this
//    // effects: takes in keyboard input and responds with appropriate action
//    public void doInput(String input) {
//        if (!gameOver) {
//            if (input.equalsIgnoreCase("m")) {
//                shoot();
//                player.shoot();
//            }
//            player.playerMove(input);
//        } else {
//            if (input.equalsIgnoreCase("l")) {
//                restart();
//            }
//        }
//    }

    // FROM CLASS EXAMPLE SPACE INVADER
    // modifies: this
    // effects: changes player position depending on user input. Space to shoot,
    // P to restart, ESCAPE to exit the game, WASD for movements
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            player.shoot();
            shoot();
            shootSound();
        } else if (keyCode == KeyEvent.VK_P && gameOver) {
            restart();
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else {
            player.playerMove(keyCode);
        }
    }

    // effects: plays a shooting sound
    public void shootSound() {
        File sound = new File("data/shoot.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // modifies: this
    // effects: spawn multiple enemies according to the round number, exponentially increases. 3 ^ round #
    public void spawn() {
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
    public void playerGetHit() {
        //if enemy touches u,they die, but u lose like health.
        List<Enemy> deadEnemies = new ArrayList<Enemy>();

        for (Enemy e: enemies) {
            if (e.hitPlayer(player)) {
                deadEnemies.add(e);
                player.loseHealth();
                loseHealthSound();
            }
        }
        enemies.removeAll(deadEnemies);
    }

    // effects: plays a sound when player loses health
    public void loseHealthSound() {
        File sound = new File("data/damage.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // modifies: this
    // effects: if enemy is hit by any bullet existing on the playing field, both bullet and enemy will be removed
    public void enemyGetHit() {
        List<Enemy> deadEnemies = new ArrayList<Enemy>();
        List<Bullet> shotBullets = new ArrayList<Bullet>();

        for (Enemy e : enemies) {
            for (Bullet b : bullets) {
                if (e.getShot(b)) {
                    player.setAmmo(player.getAmmo() + 2);
                    deadEnemies.add(e);
                    shotBullets.add(b);
                    enemyDieSound();
                }
            }
        }
        enemies.removeAll(deadEnemies);
        bullets.removeAll(shotBullets);
    }

    // effects: plays a sound when player loses health
    public void enemyDieSound() {
        File sound = new File("data/die.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // effects: returns true if all enemies have been eliminated.
    public boolean nextRound() {
        return enemies.isEmpty();
    }

    // modifies: this
    // effects: if bullet travels out of the boundaries of the game screen, then remove the bullet.
    public void bulletOutOfBounds() {
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
    public void enemyUpdate() {
        for (Enemy e : enemies) {
            e.chase(player);
        }
    }

    // modifies: this
    // effects: updates the positions of all bullets on the screen
    public void bulletUpdate() {
        for (Bullet b : bullets) {
            b.bulletMove();
        }
        bulletOutOfBounds();
    }

    // modifies: this
    // effects: if player has no more health, make it game over.
    public void setGameOver() {
        if (player.noHealth()) {
            gameOver = true;
        }
    }

    // modifies: this
    // effects: set game over to false.
    public void setGameStart() {
        gameOver = false;
    }

    // modifies: this
    // effects: shoots bullet if there is still ammo on the player; shoots in specified direction.
    public void shoot() {
        if (player.getAmmo() != 0) {
            Bullet bullet = new Bullet(player.getPositionX(), player.getPositionY(), player.getDirection());
            bullets.add(bullet);
        }
    }

    // modifies: this
    //effects: resets the game state back to original.
    public void restart() {
        bullets.clear();
        enemies.clear();
        round = 0;
        money = 0;
        gameOver = false;
        player = new Player();
    }

    @Override
    // effects: returns this data as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("health", player.getHealth());
        json.put("ammo", player.getAmmo());
        json.put("round", this.round);
        json.put("enemies", enemiesJson());
        return json;
    }

   // effects: returns fresh list of enemies as a JSON array
    private JSONArray enemiesJson() {

        JSONArray jsonArray = new JSONArray();

        for (Enemy e : enemies) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

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

    // modifies: this
    // effect: sets health
    public void setHealth(int health) {
        this.player.setHealth(health);
    }

    // modifies: this
    // effect: sets ammo
    public void setAmmo(int ammo) {
        this.player.setAmmo(ammo);
    }

    // modifies: this
    // effect: sets round
    public void setRound(int round) {
        this.round = round;
    }

    // modifies: this
    // effects: inserts enemy into list
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }


}
