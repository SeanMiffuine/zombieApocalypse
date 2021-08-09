package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BulletTest {
    private Bullet bullet;

    @Test
    public void testConstruct() {
        bullet = new Bullet(125, 125, 1);
        assertEquals(bullet.getPositionX(), 125);
        assertEquals(bullet.getPositionY(), 125);
        assertEquals(bullet.getDirection(), 1);
    }

    @Test
    public void testBulletMoveUp() {
        bullet = new Bullet(125, 125, 1);
        bullet.bulletMove();
        assertEquals(bullet.getPositionY(), 118);
    }

    @Test
    public void testBulletMoveDown() {
        bullet = new Bullet(125, 125, 2);
        bullet.bulletMove();
        assertEquals(bullet.getPositionY(), 132);
    }

    @Test
    public void testBulletMoveLeft() {
        bullet = new Bullet(125, 125, 3);
        bullet.bulletMove();
        assertEquals(bullet.getPositionX(), 118);
    }

    @Test
    public void testBulletMoveRight() {
        bullet = new Bullet(125, 125, 4);
        bullet.bulletMove();
        assertEquals(bullet.getPositionX(), 132);
    }

    @Test
    public void testBoundaryUp() {
        bullet = new Bullet(125, 125, 1);
        for (int i = 0; i < 25; i++) {
            bullet.bulletMove();
        }
        assertTrue(bullet.bulletOutOfBounds());
    }

    @Test
    public void testBoundaryDown() {
        bullet = new Bullet(125, 125, 2);
        for (int i = 0; i < 100; i++) {
            bullet.bulletMove();
        }
        assertTrue(bullet.bulletOutOfBounds());
    }

    @Test
    public void testBoundaryLeft() {
        bullet = new Bullet(125, 125, 3);
        for (int i = 0; i < 25; i++) {
            bullet.bulletMove();
        }
        assertTrue(bullet.bulletOutOfBounds());
    }

    @Test
    public void testBoundaryRight() {
        bullet = new Bullet(125, 125, 4);
        for (int i = 0; i < 100; i++) {
            bullet.bulletMove();
        }
        assertTrue(bullet.bulletOutOfBounds());
    }

    @Test
    public void testBoundaryFalse() {
        bullet = new Bullet(125, 125, 4);
        assertFalse(bullet.bulletOutOfBounds());
    }

    @Test
    public void testGetSpeed() {
        bullet = new Bullet(125, 125, 4);
        assertEquals(bullet.getSpeed(), 7);
    }

}
