package dev.akuniutka.skillfactory.lms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.akuniutka.skillfactory.lms.model.TestData;
import dev.akuniutka.skillfactory.lms.model.Student;
import dev.akuniutka.skillfactory.lms.model.University;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {
    private static final int TEST_ITERATIONS = 2;
    private final TestData testData = new TestData();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenSerializeStudentShouldReturnCorrectString() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Student student = testData.createRandomStudent();
            String expected = "{" +
                    "\n  \"studentFullName\": \"" + student.getFullName() + '"' +
                    ",\n  \"studentUniversityId\": \"" + student.getUniversityId() + '"' +
                    ",\n  \"studentCurrentCourseNumber\": " + student.getCurrentCourseNumber() +
                    ",\n  \"studentAvgExamScore\": " + student.getAvgExamScore() +
                    "\n}";
            String actual = JsonUtil.serializeStudent(student);
            assertEquals(expected, actual);
        }
    }

    @Test
    void whenSerializeUniversityShouldReturnCorrectString() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            University university = testData.createRandomUniversity();
            String expected = "{" +
                    "\n  \"universityId\": \"" + university.getId() + '"' +
                    ",\n  \"universityFullName\": \"" + university.getFullName() + '"' +
                    ",\n  \"universityShortName\": \"" + university.getShortName() + '"' +
                    ",\n  \"universityYearOfFoundation\": " + university.getYearOfFoundation() +
                    ",\n  \"universityMainProfile\": \"" + university.getMainProfile() + '"' +
                    "\n}";
            String actual = JsonUtil.serializeUniversity(university);
            assertEquals(expected, actual);
        }
    }

    @Test
    void whenSerializeListOfStudentsShouldReturnCorrectString() {
        List<Student> students = new ArrayList<>();
        StringBuilder expected = new StringBuilder("[");
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Student student = testData.createRandomStudent();
            expected.append(students.size() == 0 ? "" : ",").append("\n  {")
                    .append("\n    \"studentFullName\": \"").append(student.getFullName()).append('"')
                    .append(",\n    \"studentUniversityId\": \"").append(student.getUniversityId()).append('"')
                    .append(",\n    \"studentCurrentCourseNumber\": ").append(student.getCurrentCourseNumber())
                    .append(",\n    \"studentAvgExamScore\": ").append(student.getAvgExamScore())
                    .append("\n  }");
            students.add(student);
        }
        expected.append("\n]");
        String actual = JsonUtil.serializeStudents(students);
        assertEquals(expected.toString(), actual);
    }

    @Test
    void whenSerializeListOfUniversitiesShouldReturnCorrectString() {
        List<University> universities = new ArrayList<>();
        StringBuilder expected = new StringBuilder("[");
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            University university = testData.createRandomUniversity();
            expected.append(universities.size() == 0 ? "" : ",").append("\n  {")
                    .append("\n    \"universityId\": \"").append(university.getId()).append('"')
                    .append(",\n    \"universityFullName\": \"").append(university.getFullName()).append('"')
                    .append(",\n    \"universityShortName\": \"").append(university.getShortName()).append('"')
                    .append(",\n    \"universityYearOfFoundation\": ").append(university.getYearOfFoundation())
                    .append(",\n    \"universityMainProfile\": \"").append(university.getMainProfile()).append('"')
                    .append("\n  }");
            universities.add(university);
        }
        expected.append("\n]");
        String actual = JsonUtil.serializeUniversities(universities);
        assertEquals(expected.toString(), actual);
    }

    @Test
    void whenDeserializeStudentShouldReturnCorrectStudentObject() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Student expected = testData.createRandomStudent();
            String json = gson.toJson(expected);
            Student actual = JsonUtil.deserializeStudent(json);
            assertEquals(expected.toString(), actual.toString());
        }
    }

    @Test
    void whenDeserializeUniversityShouldReturnCorrectUniversityObject() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            University expected = testData.createRandomUniversity();
            String json = gson.toJson(expected);
            University actual = JsonUtil.deserializeUniversity(json);
            assertEquals(expected.toString(), actual.toString());
            assertEquals(expected.getMainProfile(), actual.getMainProfile());
        }
    }

    @Test
    void whenDeserializeListOfStudentsShouldReturnCorrectList() {
        List<Student> expected = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            expected.add(testData.createRandomStudent());
        }
        String json = gson.toJson(expected);
        List<Student> actual = JsonUtil.deserializeStudents(json);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }

    @Test
    void whenDeserializeListOfUniversitiesShouldReturnCorrectList() {
        List<University> expected = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            expected.add(testData.createRandomUniversity());
        }
        String json = gson.toJson(expected);
        List<University> actual = JsonUtil.deserializeUniversities(json);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
            assertEquals(expected.get(i).getMainProfile(), actual.get(i).getMainProfile());
        }
    }
}