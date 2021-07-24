package model;

import java.util.Random;

// Enemy that chases and attempts to kill player. Is killable by bullets.
public class Enemy {

    public static final int SIZE = 10;

    private int speed;
    private int positionX;
    private int positionY;

    public Enemy() {
        this.speed = 10;

        pickSpawn(positionX, positionY);
    }

    private void pickSpawn(int positionX, int positionY) {
        Random rand = new Random();
        int side = rand.nextInt(4);
        if (side == 0) {
            this.positionX = 0; //left side
            this.positionY = rand.nextInt(GameData.WINDOW_HEIGHT);
        } else if (side == 1) {
            this.positionX = GameData.WINDOW_WIDTH; //right side
            this.positionY = rand.nextInt(GameData.WINDOW_HEIGHT);
        } else if (side == 2) {
            this.positionX = rand.nextInt(GameData.WINDOW_WIDTH); //TOP
            this.positionY = 0;
        } else if (side == 3) {
            this.positionX = rand.nextInt(GameData.WINDOW_WIDTH); //right side
            this.positionY = GameData.WINDOW_HEIGHT;
        }
    }

    // modifies: this
    // effects: chases the enemy after the player
    public void chase(Player player) {
        if (this.positionX < player.getPositionX()) {
            positionX += speed;
        } else {
            positionX -= speed;
        }
        if (this.positionY < player.getPositionY()) {
            positionY += speed;
        } else {
            positionY -= speed;
        }
    }

    public boolean getShot() {
        //!!! health?
    }

    public boolean hitPlayer(Player player) {
        //!!!
    }


}
