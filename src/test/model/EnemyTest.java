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
        //testing random...
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
            assertEquals(enemy.getPositionY(), enemyBeforePosX + enemy.getSpeed());
        } else if (enemy.getPositionY() > player.getPositionY()) {
            assertEquals(enemy.getPositionY(), enemyBeforePosY - enemy.getSpeed());
        }
    }

    @Test
    public void testGetShot() {
        Bullet bullet = new Bullet(125, 125, 1);
        assertFalse(enemy.getShot(bullet));
    }

}
