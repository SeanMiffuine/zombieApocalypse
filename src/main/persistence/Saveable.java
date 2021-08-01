package persistence;

import org.json.JSONObject;

public interface Saveable {
    //effects: returns as JSON object
    JSONObject toJson();
}
