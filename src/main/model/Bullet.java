package model;

// A projectile coming from Player in thr form of a bullet. Direct hits cause enemies to die.
public class Bullet {

    public static final int SIZE = 5;
    public static final int SPEED = 20;
    private int positionX;
    private int positionY;
    private int direction; // up 1, down 2, left 3, right 4.

    // effects: constructs a bullet object
    public Bullet(int x, int y, int direction) {
        this.positionX = x;
        this.positionY = y;
        this.direction = direction;
    }

    // modifies: this
    // effects: moves bullet in the direction that it was shot by SPEED.
    public void bulletMove() {
        if (this.direction == 1) {
            this.positionY -= SPEED;
        } else if (this.direction == 2) {
            this.positionY += SPEED;
        } else if (this.direction == 3) {
            this.positionX -= SPEED;
        } else if (this.direction == 4) {
            this.positionX += SPEED;
        }
    }

    // effects: gets positionX
    public int getPositionX() {
        return positionX;
    }

    // effects: gets positionY
    public int getPositionY() {
        return positionY;
    }

    // effects: gets Size
    public int getSize() {
        return SIZE;
    }

    // effects: gets SPEED
    public int getSpeed() {
        return SPEED;
    }

    // effects: gets direction
    public int getDirection() {
        return direction;
    }

    // effects: returns true if the bullet is out of the bounds of the game window, else false.
    public boolean bulletOutOfBounds() {
        if (positionX < 0 || positionX > GameData.WINDOW_WIDTH) {
            return true;
        } else if (positionY < 0 || positionY > GameData.WINDOW_HEIGHT) {
            return true;
        } else {
            return false;
        }
    }
}
