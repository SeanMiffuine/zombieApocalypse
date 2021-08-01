package persistence;

import model.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    public void enemyPositionTest(int positionX, int positionY, Enemy enemy) {
        assertEquals(positionX, enemy.getPositionX());
        assertEquals(positionY, enemy.getPositionY());
    }
}
