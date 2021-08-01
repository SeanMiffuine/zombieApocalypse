package persistence;

import model.GameData;
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

    public GameData read() throws IOException {
        String jsonData = readFile(address);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameData(jsonObject);
    }

    private String readFile(String source) throws  IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    private GameData parseGameData(JSONObject jsonObject) {
        GameData gd = new GameData();
        // setters ot new game data.
        setNewData(gd, jsonObject);
        return gd;
    }

    private void setNewData(GameData gd, JSONObject jsonObject) {
        int health = jsonObject.getInt("health");
        int ammo = jsonObject.getInt("ammo");
        int round = jsonObject.getInt("round");
        gd.setHealth(health);
        gd.setAmmo(ammo);
        gd.setRound(round);
    }
}