package persistence;

import model.Enemy;
import model.GameData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String address;

    public JsonReader(String source) {
        this.address = source;
    }

    // effects: read game data from file
    public GameData read() throws IOException {
        String jsonData = readFile(address);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameData(jsonObject);
    }

    //modifies: this
    //effects: reads json file
    private String readFile(String source) throws  IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    //effects: parses the game data
    private GameData parseGameData(JSONObject jsonObject) {
        GameData gd = new GameData();
        // setters ot new game data.
        setNewData(gd, jsonObject);
        return gd;
    }

    //modifies: game data
    //effects: sets the game data
    private void setNewData(GameData gd, JSONObject jsonObject) {
        int health = jsonObject.getInt("health");
        int ammo = jsonObject.getInt("ammo");
        int round = jsonObject.getInt("round");
        gd.setHealth(health);
        gd.setAmmo(ammo);
        gd.setRound(round);
        JSONArray jsonArray = jsonObject.getJSONArray("enemies");
        for (Object json : jsonArray) {
            JSONObject enemy = (JSONObject) json;
            addEnemy(gd, enemy);
        }
    }

    //modifies: game data
    //effects: iterate through enemy list and
    private void addEnemy(GameData gd, JSONObject jsonObject) {
        int x = jsonObject.getInt("X");
        int y = jsonObject.getInt("Y");
        gd.addEnemy(new Enemy(x, y));
    }
}
