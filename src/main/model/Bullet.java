package model;

// A projectile coming from Player in thr form of a bullet. Direct hits cause enemies to die.
public class Bullet {

    public static final int SIZE = 4;
    public static final int SPEED = 20;
    private int positionX;
    private int positionY;
    private int direction; // up 1, down 2, left 3, right 4.

    public Bullet(int x, int y, int direction) {
        this.positionX = x;
        this.positionY = y;
        this.direction = direction;
    }

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

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getSize() {
        return SIZE;
    }

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
