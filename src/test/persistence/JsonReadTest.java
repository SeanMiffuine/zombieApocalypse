package persistence;

import model.GameData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReadTest extends JsonTest{

    @Test
    public void testReaderFail() {
        JsonReader reader = new JsonReader("./data/random.json");
        try {
            GameData gd = reader.read();
            fail("not supposed to work");
        } catch (IOException e) {
            System.out.println("nice");
        }
    }

    @Test
    public void testReadEmpty() {
        JsonReader reader = new JsonReader("./data/testReadEmpty.json");
        try {
            GameData gd = reader.read();
            assertEquals(10, gd.getPlayer().getAmmo());
            assertEquals(10, gd.getPlayer().getHealth());
            assertEquals(10, gd.getRound());
            assertEquals(0, gd.getEnemies().size());
        } catch (IOException e) {
            fail("not supposed to work");
        }
    }

    @Test
    public void testReadStuff() {
        JsonReader reader = new JsonReader("./data/testReadStuff.json");
        try {
            GameData gd = reader.read();
            assertEquals(10, gd.getPlayer().getAmmo());
            assertEquals(10, gd.getPlayer().getHealth());
            assertEquals(10, gd.getRound());
            assertEquals(2, gd.getEnemies().size());
            enemyPositionTest(100, 100, gd.getEnemies().get(0));
            enemyPositionTest(200, 200, gd.getEnemies().get(1));
        } catch (IOException e) {
            fail("not supposed to work");
        }
    }
}
