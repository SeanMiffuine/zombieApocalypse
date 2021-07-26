package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDataTest {
    private GameData game;

    @BeforeEach
    public void setUp() {
        game = new GameData();
    }

    @Test
    public void testConstruct() {
        assertEquals(250, game.getWindowWidth());
        assertEquals(250, game.getWindowHeight());
        assertEquals(50, game.getBorder());
        assertEquals(0, game.getRound());
        assertEquals(0, game.getMoney());
        assertEquals(0, game.getEnemies().size());
        assertEquals(0, game.getBullets().size());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testUpdate() {
        game.update();
        assertEquals(1, game.getRound());
        assertEquals(3, game.getEnemies().size());
        assertFalse(game.isGameOver());
    }
    @Test
    public void testUpdateGameOver() {
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.setGameOver();
        game.update();
        assertTrue(game.isGameOver());
    }

    @Test
    public void testDoInput() {
        game.doInput("m");
        assertEquals(1, game.getBullets().size());
    }

    @Test
    public void testSpawn() {
        game.spawn();
        assertEquals(3, game.getEnemies().size());
    }

    @Test
    public void testGameOver() {
        game.setGameOver();
        assertFalse(game.isGameOver());
    }

    @Test
    public void testShoot() {
        game.shoot();
        assertEquals(1, game.getBullets().size());
        game.shoot();
        assertEquals(2, game.getBullets().size());
    }

    @Test
    public void testRestart() {
        game.restart();
        assertEquals(250, game.getWindowWidth());
        assertEquals(250, game.getWindowHeight());
        assertEquals(50, game.getBorder());
        assertEquals(0, game.getRound());
        assertEquals(0, game.getMoney());
        assertEquals(0, game.getEnemies().size());
        assertEquals(0, game.getBullets().size());
        assertFalse(game.isGameOver());

        game.update();
        game.update();
        game.update();
        game.restart();
        assertEquals(250, game.getWindowWidth());
        assertEquals(250, game.getWindowHeight());
        assertEquals(50, game.getBorder());
        assertEquals(0, game.getRound());
        assertEquals(0, game.getMoney());
        assertEquals(0, game.getEnemies().size());
        assertEquals(0, game.getBullets().size());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGetPlayer() {
        assertTrue(game.getPlayer() != null);
    }

    @Test
    public void testPushDataPlayer() {
        assertTrue(game.pushDataPlayer().equals("health: " + game.getPlayer().getHealth() + " ammo: "
                + game.getPlayer().getAmmo() + " playerX: " + game.getPlayer().getPositionX()
                + " playerY: " + game.getPlayer().getPositionY()));
    }

    @Test
    public void testPushDataGame() {
        assertTrue(game.pushDataGame().equals("Enemy Total: " + game.getEnemies().size() + " Enemy Coords: "
                + "\n" + "Flying Bullets Total: " + game.getBullets().size() + " Bullet Coords:"));
    }

    @Test
    public void testPushDataGameGameOver() {
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.setGameOver();
        assertTrue(game.pushDataGame().equals("Game over. P for restart."));
    }



}