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
    public void testGetShot() {
        Bullet bullet = new Bullet(125, 125, 1);
        assertFalse(enemy.getShot(bullet));
    }

}
