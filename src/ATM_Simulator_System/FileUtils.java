package ATM_Simulator_System;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileUtils {
    private static final String FILE_PATH = "localStorage.json";

    public static void writeToFile(JsonObject data) {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(data, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JsonObject readFromFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }
}
