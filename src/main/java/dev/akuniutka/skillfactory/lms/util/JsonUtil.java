package dev.akuniutka.skillfactory.lms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.akuniutka.skillfactory.lms.model.Student;
import dev.akuniutka.skillfactory.lms.model.University;

import java.util.Collection;
import java.util.List;

public class JsonUtil {
    private JsonUtil() {
    }

    public static String serializeStudent(Student student) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(student);
    }

    public static String serializeUniversity(University university) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(university);
    }

    public static String serializeStudents(Collection<Student> students) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(students);
    }

    public static String serializeUniversities(Collection<University> universities) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(universities);
    }

    public static Student deserializeStudent(String json) {
        return new Gson().fromJson(json, Student.class);
    }

    public static University deserializeUniversity(String json) {
        return new Gson().fromJson(json, University.class);
    }

    public static List<Student> deserializeStudents(String json) {
        return new Gson().fromJson(json, new TypeToken<List<Student>>() {}.getType());
    }

    public static List<University> deserializeUniversities(String json) {
        return new Gson().fromJson(json, new TypeToken<List<University>>() {}.getType());
    }
}
