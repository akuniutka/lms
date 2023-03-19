package dev.akuniutka.skillfactory.lms.util;

import dev.akuniutka.skillfactory.lms.TestData;
import dev.akuniutka.skillfactory.lms.enums.StudyProfile;
import dev.akuniutka.skillfactory.lms.models.Student;
import dev.akuniutka.skillfactory.lms.models.University;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {
    private static final int TEST_ITERATIONS = 2;
    private final TestData testData = new TestData();

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenSerializeStudentShouldReturnCorrectString() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String fullName = testData.getNextStudentFullName();
            String universityId = testData.getNextUniversityID();
            int currentCourseNumber = testData.getNextStudentCurrentCourseNumber();
            float avgExamScore = testData.getNextStudentAvgExamScore();
            String expected = "{" +
                    "\n  \"fullName\": \"" + fullName + '"' +
                    ",\n  \"universityId\": \"" + universityId + '"' +
                    ",\n  \"currentCourseNumber\": " + currentCourseNumber +
                    ",\n  \"avgExamScore\": " + avgExamScore +
                    "\n}";
            Student student = new Student()
                    .setFullName(fullName)
                    .setUniversityId(universityId)
                    .setCurrentCourseNumber(currentCourseNumber)
                    .setAvgExamScore(avgExamScore);
            String actual = JsonUtil.serialize(student);
            assertEquals(expected, actual);
        }
    }

    @Test
    void whenSerializeUniversityShouldReturnCorrectString() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String id = testData.getNextUniversityID();
            String fullName = testData.getNextUniversityFullName();
            String shortName = testData.getNextUniversityShortName();
            int yearOfFoundation = testData.getNextUniversityYearOfFoundation();
            StudyProfile mainProfile = testData.getNextUniversityMainProfile();
            String expected = "{" +
                    "\n  \"id\": \"" + id + '"' +
                    ",\n  \"fullName\": \"" + fullName + '"' +
                    ",\n  \"shortName\": \"" + shortName + '"' +
                    ",\n  \"yearOfFoundation\": " + yearOfFoundation +
                    ",\n  \"mainProfile\": \"" + mainProfile + '"' +
                    "\n}";
            University university = new University()
                    .setId(id)
                    .setFullName(fullName)
                    .setShortName(shortName)
                    .setYearOfFoundation(yearOfFoundation)
                    .setMainProfile(mainProfile);
            String actual = JsonUtil.serialize(university);
            assertEquals(expected, actual);
        }
    }

    @Test
    void whenSerializeListOfStudentsShouldReturnCorrectString() {
        List<Student> students = new ArrayList<>();
        StringBuilder expected = new StringBuilder("[");
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String fullName = testData.getNextStudentFullName();
            String universityId = testData.getNextUniversityID();
            int currentCourseNumber = testData.getNextStudentCurrentCourseNumber();
            float avgExamScore = testData.getNextStudentAvgExamScore();
            if (students.size() != 0) {
                expected.append(",");
            }
            expected.append("\n  {")
                    .append("\n    \"fullName\": \"").append(fullName).append('"')
                    .append(",\n    \"universityId\": \"").append(universityId).append('"')
                    .append(",\n    \"currentCourseNumber\": ").append(currentCourseNumber)
                    .append(",\n    \"avgExamScore\": ").append(avgExamScore)
                    .append("\n  }");
            Student student = new Student()
                    .setFullName(fullName)
                    .setUniversityId(universityId)
                    .setCurrentCourseNumber(currentCourseNumber)
                    .setAvgExamScore(avgExamScore);
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
            String id = testData.getNextUniversityID();
            String fullName = testData.getNextUniversityFullName();
            String shortName = testData.getNextUniversityShortName();
            int yearOfFoundation = testData.getNextUniversityYearOfFoundation();
            StudyProfile mainProfile = testData.getNextUniversityMainProfile();
            if (universities.size() != 0) {
                expected.append(",");
            }
            expected.append("\n  {")
                    .append("\n    \"id\": \"").append(id).append('"')
                    .append(",\n    \"fullName\": \"").append(fullName).append('"')
                    .append(",\n    \"shortName\": \"").append(shortName).append('"')
                    .append(",\n    \"yearOfFoundation\": ").append(yearOfFoundation)
                    .append(",\n    \"mainProfile\": \"").append(mainProfile).append('"')
                    .append("\n  }");
            University university = new University()
                    .setId(id)
                    .setFullName(fullName)
                    .setShortName(shortName)
                    .setYearOfFoundation(yearOfFoundation)
                    .setMainProfile(mainProfile);
            universities.add(university);
        }
        expected.append("\n]");
        String actual = JsonUtil.serializeUniversities(universities);
        assertEquals(expected.toString(), actual);
    }
}