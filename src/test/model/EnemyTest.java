package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    public void setUp() {
        enemy = new Enemy();
    }

    @Test
    public void testConstruct() {
        assertEquals(enemy.getSpeed(), 5);
        //testing random spawn
        assertTrue(enemy.getPositionX() == 0 || enemy.getPositionX() == GameData.WINDOW_WIDTH
                || (enemy.getPositionX() >= 0 && enemy.getPositionX() < GameData.WINDOW_WIDTH ));
        assertTrue(enemy.getPositionY() == 0 || enemy.getPositionY() == GameData.WINDOW_HEIGHT
                || (enemy.getPositionY() >= 0 && enemy.getPositionY() < GameData.WINDOW_HEIGHT ));
    }

    @Test
    public void testGetPositionX() {
        assertTrue(enemy.getPositionX() == 0 || enemy.getPositionX() == GameData.WINDOW_WIDTH
                || (enemy.getPositionX() >= 0 && enemy.getPositionX() < GameData.WINDOW_WIDTH ));
    }

    @Test
    public void testGetPositionY() {
        assertTrue(enemy.getPositionY() == 0 || enemy.getPositionY() == GameData.WINDOW_HEIGHT
                || (enemy.getPositionY() >= 0 && enemy.getPositionY() < GameData.WINDOW_HEIGHT ));
    }


    @Test
    public void testChase() {
        Player player = new Player();
        int enemyBeforePosX = enemy.getPositionX();
        int enemyBeforePosY = enemy.getPositionY();
        enemy.chase(player);
        if (enemy.getPositionX() < player.getPositionX()) {
            assertEquals(enemy.getPositionX(), enemyBeforePosX + enemy.getSpeed());
        } else if (enemy.getPositionX() > player.getPositionX()) {
            assertEquals(enemy.getPositionX(), enemyBeforePosX - enemy.getSpeed());
        }
        if (enemy.getPositionY() < player.getPositionY()) {
            assertEquals(enemy.getPositionY(), enemyBeforePosY + enemy.getSpeed());
        } else if (enemy.getPositionY() > player.getPositionY()) {
            assertEquals(enemy.getPositionY(), enemyBeforePosY - enemy.getSpeed());
        }
    }

    @Test
    public void testGetShotFalse() {
        Bullet bullet = new Bullet(125, 125, 1);
        assertFalse(enemy.getShot(bullet));
    }

    @Test
    public void testGetShotTrue() {
        Bullet bullet = new Bullet(enemy.getPositionX(), enemy.getPositionY(), 1);
        assertTrue(enemy.getShot(bullet));
    }

    @Test
    public void testHitPlayerFalse() {
        Player player = new Player();
        assertFalse(enemy.hitPlayer(player));
    }


    @Test
    public void testHitPlayerTrue() {
        GameData game = new GameData();
        for (int i = 0; i < 50; i++) {
            if ((enemy.getPositionX() >= (game.getPlayer().getPositionX() - game.getPlayer().getSize()))
                && (enemy.getPositionX() <= (game.getPlayer().getPositionX() + game.getPlayer().getSize()))
                && (enemy.getPositionY() >= (game.getPlayer().getPositionY() - game.getPlayer().getSize()))
                && (enemy.getPositionY() >= (game.getPlayer().getPositionY() - game.getPlayer().getSize()))) {
                assertTrue(enemy.hitPlayer(game.getPlayer()));
            }
            game.update();
        }

    }

}
