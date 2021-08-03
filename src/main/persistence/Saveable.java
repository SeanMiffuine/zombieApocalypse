package persistence;

import org.json.JSONObject;

//interface for savable objects
public interface Saveable {
    //effects: returns as JSON object
    JSONObject toJson();
}
