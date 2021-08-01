package persistence;

import model.Enemy;
import model.GameData;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriteTest extends JsonTest {

    @Test
    public void testWriteWrongFile() {
        try {
            GameData gd = new GameData();
            JsonWriter writer = new JsonWriter("./data/u\0hohe.json");
            writer.open();
            fail("Not supposed to happen");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    public void testWriteEmpty() {
        try {
            GameData gd = new GameData();
            JsonWriter writer = new JsonWriter("./data/testWriteEmpty.json");
            writer.open();
            writer.write(gd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmpty.json");
            gd = reader.read();
            assertEquals(10, gd.getPlayer().getAmmo());
            assertEquals(100, gd.getPlayer().getHealth());
            assertEquals(0, gd.getRound());
            assertEquals(0, gd.getEnemies().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriteStuff() {
        try {
            GameData gd = new GameData();
            gd.addEnemy(new Enemy(100, 100));
            gd.addEnemy(new Enemy(200, 200));
            JsonWriter writer = new JsonWriter("./data/testWriteEmpty.json");
            writer.open();
            writer.write(gd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmpty.json");
            gd = reader.read();
            assertEquals(10, gd.getPlayer().getAmmo());
            assertEquals(100, gd.getPlayer().getHealth());
            assertEquals(0, gd.getRound());
            assertEquals(2, gd.getEnemies().size());
            enemyPositionTest(100, 100, gd.getEnemies().get(0));
            enemyPositionTest(200, 200, gd.getEnemies().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
