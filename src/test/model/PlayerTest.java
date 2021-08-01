package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testConstruct() {
        assertEquals(125, player.getPositionX());
        assertEquals(125, player.getPositionY());
        assertEquals(15, player.getSpeed());
        assertEquals(3, player.getAmmo());
        assertEquals(100, player.getHealth());
    }

    @Test
    public void testShoot() {
        player.shoot();
        assertEquals(player.getAmmo(), 2);
        player.shoot();
        player.shoot();
        assertEquals(player.getAmmo(), 0);
        player.shoot();
        assertEquals(player.getAmmo(), 0);
    }

    @Test
    public void NoAmmo() {
        player.setAmmo(0);
        player.shoot();
        assertEquals(player.getAmmo(), 0);
    }

    @Test
    public void testPlayerMoveUp() {
        player.playerMove("w");
        assertEquals(player.getPositionY(), 110);
        assertEquals(player.getDirection(), 1);
    }

    @Test
    public void testPlayerMoveDown() {
        player.playerMove("s");
        assertEquals(player.getPositionY(), 140);
        assertEquals(player.getDirection(), 2);
    }

    @Test
    public void testPlayerMoveLeft() {
        player.playerMove("a");
        assertEquals(player.getPositionX(), 110);
        assertEquals(player.getDirection(), 3);
    }

    @Test
    public void testPlayerMoveRight() {
        player.playerMove("d");
        assertEquals(player.getPositionX(), 140);
        assertEquals(player.getDirection(), 4);
    }

    @Test
    public void testMoveUp() {
        player.moveUp();
        assertEquals(player.getPositionY(), 110);
    }

    @Test
    public void testMoveDown() {
        player.moveDown();
        assertEquals(player.getPositionY(), 140);
    }

    @Test
    public void testMoveLeft() {
        player.moveLeft();
        assertEquals(player.getPositionX(), 110);
    }

    @Test
    public void testMoveRight() {
        player.moveRight();
        assertEquals(player.getPositionX(), 140);
    }

    @Test
    public void testBoundaryTop() {
        for (int i = 0; i < 25; i++) {
            player.moveUp();
        }
        player.boundaryCheck();
        assertEquals(player.getPositionY(), 50);
    }

    @Test
    public void testBoundaryBot() {
        for (int i = 0; i < 25; i++) {
            player.moveDown();
        }
        player.boundaryCheck();
        assertEquals(player.getPositionY(), 200);
    }

    @Test
    public void testBoundaryLeft() {
        for (int i = 0; i < 25; i++) {
            player.moveLeft();
        }
        player.boundaryCheck();
        assertEquals(player.getPositionX(), 50);
    }

    @Test
    public void testBoundaryRight() {
        for (int i = 0; i < 25; i++) {
            player.moveRight();
        }
        player.boundaryCheck();
        assertEquals(player.getPositionX(), 200);
    }

    @Test
    public void testLostHealth() {
        player.loseHealth();
        assertEquals(player.getHealth(), 80);
        player.loseHealth();
        player.loseHealth();
        assertEquals(player.getHealth(), 40);
    }

    @Test
    public void testNoHealth() {
        player.loseHealth();
        player.loseHealth();
        player.loseHealth();
        player.loseHealth();
        player.loseHealth();
        assertTrue(player.noHealth());
    }


}
