package model;

import java.awt.event.KeyEvent;

// Player object that you control. Able to move
public class Player {
    private static final int SIZE = 10;

    private int positionX;
    private int positionY;
    private int speed;
    private int ammo;
    private int health;
    private int direction; // up 1, down 2, left 3, right 4.

    // effects: constructs the player
    public Player() {
        this.positionX = SIZE;
        this.positionY = SIZE;
        this.speed = 15;
        this.ammo = 100;
        this.health = 100;
    }

    // modifies: this
    // effects: take sin keycode and moves player accordingly
    public void playerMove(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            moveUp();
            this.direction = 1;
        }
        if (keyCode == KeyEvent.VK_S) {
            moveDown();
            this.direction = 2;
        }
        if (keyCode == KeyEvent.VK_A) {
            moveLeft();
            this.direction = 3;
        }
        if (keyCode == KeyEvent.VK_D) {
            moveRight();
            this.direction = 4;
        }
        boundaryCheck();
    }

    // !!!
    // requires: ammo is not 0
    // modifies: this
    // effects: shoots bullet from player
    public void shoot() {
        if (ammo != 0) {
            ammo--;
        }
    }

    // modifies: this
    // effects: moves player up
    public void moveUp() {
        this.positionY -= speed;
    }

    // modifies: this
    // effects: moves player down
    public void moveDown() {
        this.positionY += speed;
    }

    // modifies: this
    // effects: moves player right
    public void moveRight() {
        this.positionY += speed;
    }

    // modifies: this
    // effects: moves player left
    public void moveLeft() {
        this.positionX += speed;
    }

    // modifies: this
    // effects: keeps player within the bounds of the border.
    public void boundaryCheck() {
        if (positionX < GameData.BORDER) {
            positionX = GameData.BORDER;
        }
        if (positionX > (GameData.WINDOW_WIDTH - GameData.BORDER)) {
            positionX = GameData.WINDOW_WIDTH - GameData.BORDER;
        }
        if (positionY < GameData.BORDER) {
            positionY = GameData.BORDER;
        }
        if (positionY > (GameData.WINDOW_HEIGHT - GameData.BORDER)) {
            positionY = GameData.WINDOW_HEIGHT - GameData.BORDER;
        }
    }

    public void loseHealth() {
        health -= 20;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getDirection() {
        return direction;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getSize() {
        return SIZE;
    }

}
