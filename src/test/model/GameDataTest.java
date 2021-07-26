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



}