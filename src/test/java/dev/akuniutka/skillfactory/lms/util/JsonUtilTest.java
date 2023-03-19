package dev.akuniutka.skillfactory.lms.util;

import com.github.javafaker.Faker;
import dev.akuniutka.skillfactory.lms.models.Student;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();
    private static final int TEST_ITERATIONS = 2;

    @Test
    void whenSerializeStudentShouldReturnCorrectString() {
        Student student = new Student();
        String fullName;
        String universityId;
        int currentCourseNumber;
        float avgExamScore;
        Set<String> usedFullNames = new HashSet<>();
        Set<String> usedUniversityIds = new HashSet<>();
        Set<Integer> usedCurrentCourseNumbers = new HashSet<>();
        Set<Float> usedAvgExamScores = new HashSet<>();

        for (int i = 0; i < TEST_ITERATIONS; i++) {
            do {
                fullName = FAKER.name().fullName();
            } while (!usedFullNames.add(fullName));
            do {
                universityId = FAKER.idNumber().ssnValid();
            } while (!usedUniversityIds.add(universityId));
            do {
                currentCourseNumber = FAKER.number().numberBetween(1, 5);
            } while (!usedCurrentCourseNumbers.add(currentCourseNumber)
                    && usedCurrentCourseNumbers.size() < 4
            );
            do {
                avgExamScore = RANDOM.nextFloat() * 4 + 1;
            } while (!usedAvgExamScores.add(avgExamScore));
            String expected = "{" +
                    "\n  \"fullName\": \"" + fullName + '"' +
                    ",\n  \"universityId\": \"" + universityId + '"' +
                    ",\n  \"currentCourseNumber\": " + currentCourseNumber +
                    ",\n  \"avgExamScore\": " + avgExamScore +
                    "\n}";
            student.setFullName(fullName)
                    .setUniversityId(universityId)
                    .setCurrentCourseNumber(currentCourseNumber)
                    .setAvgExamScore(avgExamScore);
            String actual = JsonUtil.serialize(student);
            assertEquals(expected, actual);
        }
    }

}