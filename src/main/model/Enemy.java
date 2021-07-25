package model;

import java.util.Random;

// Enemy that chases and attempts to kill player. Is killable by bullets.
public class Enemy {

    public static final int SIZE = 10;

    private int speed;
    private int positionX;
    private int positionY;

    public Enemy() {
        this.speed = 5;
        pickSpawn(positionX, positionY);
    }

    // effects: spawn an enemy on a random side of the screen at random position
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
        } else {
            positionX -= speed;
        }
        if (this.positionY < player.getPositionY()) {
            positionY += speed;
        } else {
            positionY -= speed;
        }
    }

    public boolean getShot(Bullet bullet) {
        if ((positionX + (SIZE / 2)) >= (bullet.getPositionX() - bullet.getSize())
                && (positionX + (SIZE / 2)) <= (bullet.getPositionX() + bullet.getSize())) {
            return true; //detect touching bullet from left
        } else if ((positionX - (SIZE / 2)) <= (bullet.getPositionX() + bullet.getSize())
                && (positionX - (SIZE / 2)) >= (bullet.getPositionX() - bullet.getSize())) {
            return true; //detect touching bullet from right
        } else if ((positionY + (SIZE / 2)) >= (bullet.getPositionY() - bullet.getSize())
                && (positionY + (SIZE / 2)) <= (bullet.getPositionY() + bullet.getSize())) {
            return true; //detect touching bullet from top
        } else if ((positionY - (SIZE / 2)) >= (bullet.getPositionY() + bullet.getSize())
                && (positionY - (SIZE / 2)) <= (bullet.getPositionY() - bullet.getSize())) {
            return true; //detect touching bullet from bottom
        } else {
            return false;
        }

    }

    public boolean hitPlayer(Player player) {
        if ((positionX + (SIZE / 2)) >= (player.getPositionX() - player.getSize())
                && (positionX + (SIZE / 2)) <= (player.getPositionX() + player.getSize())) {
            return true; //detect touching player from left
        } else if ((positionX - (SIZE / 2)) <= (player.getPositionX() + player.getSize())
                && (positionX - (SIZE / 2)) >= (player.getPositionX() - player.getSize())) {
            return true; //detect touching player from right
        } else if ((positionY + (SIZE / 2)) >= (player.getPositionY() - player.getSize())
                && (positionY + (SIZE / 2)) <= (player.getPositionY() + player.getSize())) {
            return true; //detect touching player from top
        } else if ((positionY - (SIZE / 2)) >= (player.getPositionY() + player.getSize())
                && (positionY - (SIZE / 2)) <= (player.getPositionY() - player.getSize())) {
            return true; //detect touching player from bottom
        } else {
            return false;
        }
    }
}
