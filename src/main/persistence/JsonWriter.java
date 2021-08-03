package persistence;

import model.GameData;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//writes to the json file for save state
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //modifies: this
    //effects: open writer file
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //modifies: this
    //effects: write game data
    public void write(GameData gd) {
        JSONObject json = gd.toJson();
        saveToFile(json.toString(TAB));
    }

    //modifies: this
    //effects: close writer
    public void close() {
        writer.close();
    }

    //modifies: this
    //effects: save to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
