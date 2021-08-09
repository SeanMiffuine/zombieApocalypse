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
    private int direction = 1; // up 1, down 2, left 3, right 4.

    // effects: constructs the player
    public Player() {
        this.positionX = GameData.WINDOW_WIDTH / 2;
        this.positionY = GameData.WINDOW_HEIGHT / 2;
        this.speed = 25;
        this.ammo = 10;
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

/*
    // requires: string input of single character
    // modifies: this
    // effects: takes in input of WASD to move player accordingly.
    public void playerMove(String input) {
        if (input.equalsIgnoreCase("w")) {
            moveUp();
            this.direction = 1;
        }
        if (input.equalsIgnoreCase("s")) {
            moveDown();
            this.direction = 2;
        }
        if (input.equalsIgnoreCase("a")) {
            moveLeft();
            this.direction = 3;
        }
        if (input.equalsIgnoreCase("d")) {
            moveRight();
            this.direction = 4;
        }
        boundaryCheck();
    }
*/

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
        this.positionX += speed;
    }

    // modifies: this
    // effects: moves player left
    public void moveLeft() {
        this.positionX -= speed;
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

    // modifies: this
    // effects: decrease the player's health
    public void loseHealth() {
        this.health -= 20;
    }

    // effects: returns true if health is less than or equal to 0
    public boolean noHealth() {
        return health <= 0;
    }

    // effects: returns positionX
    public int getPositionX() {
        return positionX;
    }

    // effects: returns positionY
    public int getPositionY() {
        return positionY;
    }

    // effects: returns direction
    public int getDirection() {
        return direction;
    }

    // effects: returns ammo
    public int getAmmo() {
        return ammo;
    }

    // effects: returns SIZE
    public int getSize() {
        return SIZE;
    }

    // effects: returns health
    public int getHealth() {
        return health;
    }

    // effects: returns speed
    public int getSpeed() {
        return speed;
    }

    //modifies: this
    // effects: sets health
    public void setHealth(int health) {
        this.health = health;
    }

    //modifies: this
    // effects: sets ammo
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

}
