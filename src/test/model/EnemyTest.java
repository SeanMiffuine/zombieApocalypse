package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private Enemy enemy;
    private Enemy enemyB;
    private Enemy enemyC;

    @BeforeEach
    public void setUp() {
        enemy = new Enemy();
        enemyB = new Enemy();
        enemyC = new Enemy();


    }

    @Test
    public void testConstruct() {
        assertEquals(enemy.getSpeed(), 3);
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
        Bullet bulletUp = new Bullet(enemy.getPositionX() + 3, enemy.getPositionY(), 1);
        assertTrue(enemy.getShot(bulletUp));
        Bullet bulletDown = new Bullet(enemy.getPositionX() - 3, enemy.getPositionY(), 2);
        assertTrue(enemy.getShot(bulletDown));
        Bullet bulletLeft = new Bullet(enemy.getPositionX(), enemy.getPositionY() + 3, 3);
        assertTrue(enemy.getShot(bulletLeft));
        Bullet bulletRight = new Bullet(enemy.getPositionX(), enemy.getPositionY() - 3, 4);
        assertTrue(enemy.getShot(bulletRight));
        Bullet bulletOn = new Bullet(enemy.getPositionX(), enemy.getPositionY(), 1);
        assertTrue(enemy.getShot(bulletOn));
        Bullet bulletEdge = new Bullet(enemy.getPositionX() + 3, enemy.getPositionY() + 3, 1);
        assertTrue(enemy.getShot(bulletEdge));
        Bullet bulletEdgeMinus = new Bullet(enemy.getPositionX() - 3, enemy.getPositionY() - 3, 1);
        assertTrue(enemy.getShot(bulletEdgeMinus));
    }

    @Test
    public void testHitPlayerFalse() {
        Player player = new Player();
        assertFalse(enemy.hitPlayer(player));
    }

    @Test
    public void testHitPlayerTrue() {
        GameData game = new GameData();
        for (int i = 0; i < 150; i++) {
            if ((enemy.getPositionX() >= (game.getPlayer().getPositionX() - game.getPlayer().getSize()))
                && (enemy.getPositionX() <= (game.getPlayer().getPositionX() + game.getPlayer().getSize()))
                && (enemy.getPositionY() >= (game.getPlayer().getPositionY() - game.getPlayer().getSize()))
                && (enemy.getPositionY() >= (game.getPlayer().getPositionY() - game.getPlayer().getSize()))) {
                assertTrue(enemy.hitPlayer(game.getPlayer()));
            }
            if ((enemyB.getPositionX() >= (game.getPlayer().getPositionX() - game.getPlayer().getSize()))
                    && (enemyB.getPositionX() <= (game.getPlayer().getPositionX() + game.getPlayer().getSize()))
                    && (enemyB.getPositionY() >= (game.getPlayer().getPositionY() - game.getPlayer().getSize()))
                    && (enemyB.getPositionY() >= (game.getPlayer().getPositionY() - game.getPlayer().getSize()))) {
                assertTrue(enemyB.hitPlayer(game.getPlayer()));
            }
            if ((enemyC.getPositionX() >= (game.getPlayer().getPositionX() - game.getPlayer().getSize()))
                    && (enemyC.getPositionX() <= (game.getPlayer().getPositionX() + game.getPlayer().getSize()))
                    && (enemyC.getPositionY() >= (game.getPlayer().getPositionY() - game.getPlayer().getSize()))
                    && (enemyC.getPositionY() >= (game.getPlayer().getPositionY() - game.getPlayer().getSize()))) {
                assertTrue(enemyC.hitPlayer(game.getPlayer()));
            }
            game.update();
        }

    }

}
