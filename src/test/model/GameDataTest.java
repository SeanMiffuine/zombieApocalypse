package model;
import model.GameData;

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
        assertEquals(250, game.getBorder());
        assertEquals(0, game.getMoney());
        assertEquals(0, game.getEnemies().size());
        assertEquals(0, game.getBullets().size());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testUpdate() {


    }

    @Test
    public void test() {

    }

    @Test
    public void test() {

    }

    @Test
    public void test() {

    }

}