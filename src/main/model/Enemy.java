package model;

import org.json.JSONObject;
import persistence.Saveable;

import java.util.Random;

// Enemy that chases and attempts to kill player. Is killable by bullets.
public class Enemy implements Saveable {

    public static final int SIZE = 10;

    private int speed;
    private int positionX;
    private int positionY;

    // effects: constructs an enemy
    public Enemy() {
        this.speed = 3;
        pickSpawn();
    }

    // modifies: this
    // effects: overloaded enemy object
    public Enemy(int positionX, int positionY) {
        this.speed = 3;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // modifies: this
    // effects: spawn an enemy on a random side of the screen at random position
    private void pickSpawn() {
        Random rand = new Random();
        int side = rand.nextInt(4);
        if (side == 0) {
            this.positionX = 0; //left side
            this.positionY = rand.nextInt(GameData.WINDOW_HEIGHT);
        } else if (side == 1) {
            this.positionX = GameData.WINDOW_WIDTH; //right side
            this.positionY = rand.nextInt(GameData.WINDOW_HEIGHT);
        } else if (side == 2) {
            this.positionX = rand.nextInt(GameData.WINDOW_WIDTH); // top side
            this.positionY = 0;
        } else {
            this.positionX = rand.nextInt(GameData.WINDOW_WIDTH); //right side
            this.positionY = GameData.WINDOW_HEIGHT;
        }
    }

    // modifies: this
    // effects: chases the enemy after the player
    public void chase(Player player) {
        if (this.positionX < player.getPositionX()) {
            positionX += speed;
        }
        if (this.positionX > player.getPositionX()) {
            positionX -= speed;
        }
        if (this.positionY < player.getPositionY()) {
            positionY += speed;
        }
        if (this.positionY > player.getPositionY()) {
            positionY -= speed;
        }
    }

    // effects: returns true if the passed Bullet parameter is within range of the enemy,
    //          representing that the enemy is shot and dead.
    public boolean getShot(Bullet bullet) {
        if ((positionX >= (bullet.getPositionX() - bullet.getSize()))
                && (positionX <= (bullet.getPositionX() + bullet.getSize()))
                && (positionY >= (bullet.getPositionY() - bullet.getSize()))
                && (positionY <= (bullet.getPositionY() + bullet.getSize()))) {
            return true;
        }
        return false;

    }

    // effects: returns true if the passed Player parameter is within range of the enemy,
    //          representing that the player got attacked by the enemy.
    public boolean hitPlayer(Player player) {
        if ((positionX >= (player.getPositionX() - player.getSize()))
                && (positionX <= (player.getPositionX() + player.getSize()))
                && (positionY >= (player.getPositionY() - player.getSize()))
                && (positionY <= (player.getPositionY() + player.getSize()))) {
            return true;
        }
        return false;
    }

    // effects: returns positionX
    public int getPositionX() {
        return positionX;
    }

    // effects: returns positionY
    public int getPositionY() {
        return positionY;
    }

    public int getSpeed() {
        return speed;
    }

    //effects: enemy to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("X", getPositionX());
        json.put("Y", getPositionY());
        return json;
    }
}
