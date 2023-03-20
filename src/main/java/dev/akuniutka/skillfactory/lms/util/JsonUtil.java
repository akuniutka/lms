package dev.akuniutka.skillfactory.lms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.akuniutka.skillfactory.lms.models.Student;
import dev.akuniutka.skillfactory.lms.models.University;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonUtil {
    private JsonUtil() {
    }

    public static String serializeStudent(Student student) {
        return serialize(student);
    }

    public static String serializeUniversity(University university) {
        return serialize(university);
    }

    public static String serializeStudents(Collection<Student> students) {
        return serialize(students);
    }

    public static String serializeUniversities(Collection<University> universities) {
        return serialize(universities);
    }

    public static Student deserializeStudent(String json) {
        return deserialize(json, Student.class);
    }

    public static University deserializeUniversity(String json) {
        return deserialize(json, University.class);
    }

    public static List<Student> deserializeStudents(String json) {
        return Arrays.asList(deserialize(json, Student[].class));
    }

    public static List<University> deserializeUniversities(String json) {
        return Arrays.asList(deserialize(json, University[].class));
    }


    private static <T> String serialize(T data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }

    private static <T> T deserialize(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }
}
