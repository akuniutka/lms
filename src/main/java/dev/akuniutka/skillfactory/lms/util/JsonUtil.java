package dev.akuniutka.skillfactory.lms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.akuniutka.skillfactory.lms.models.Student;
import dev.akuniutka.skillfactory.lms.models.University;

import java.util.List;

public class JsonUtil {
    private JsonUtil() {}

    public static String serialize(Student student) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(student);
    }

    public static String serialize(University university) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(university);
    }

    public static String serializeStudents(List<Student> students) {
        return serialize(students);
    }

    public static String serializeUniversities(List<University> universities) {
        return serialize(universities);
    }

    private static <T> String serialize(List<T> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(list);
    }
}
