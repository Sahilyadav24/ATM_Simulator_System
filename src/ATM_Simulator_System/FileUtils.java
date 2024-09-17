package ATM_Simulator_System;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    private static final String FILE_PATH = "localStorage.json"; // Common storage file

    // Method to write a JsonObject to a file
    public static void writeToFile(JsonObject data, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(new Gson().toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read a JsonObject from a file
    public static JsonObject readFromFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonObject();
    }
}
