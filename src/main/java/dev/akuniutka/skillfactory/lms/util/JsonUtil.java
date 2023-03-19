package dev.akuniutka.skillfactory.lms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.akuniutka.skillfactory.lms.models.Student;

public class JsonUtil {
    private JsonUtil() {}

    public static String serialize(Student student) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(student);
    }
}
