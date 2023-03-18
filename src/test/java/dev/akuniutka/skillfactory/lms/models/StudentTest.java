package dev.akuniutka.skillfactory.lms.models;

import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();
    private static final int TEST_ITERATIONS = 2;

    @Test
    void whenSetFullNameTheSameStudentObjectShouldBeReturned() {
        Student student = new Student();
        Set<String> fullNames = new HashSet<>();
        while (fullNames.size() < TEST_ITERATIONS) {
            fullNames.add(FAKER.name().fullName());
        }

        for (String fullName : fullNames) {
            assertSame(student, student.setFullName(fullName));
        }
    }

    @Test
    void whenSetFullNameGetFullNameShouldReturnThatValue() {
        Student student = new Student();
        Set<String> fullNames = new HashSet<>();
        while (fullNames.size() < TEST_ITERATIONS) {
            fullNames.add(FAKER.name().fullName());
        }

        for (String fullName : fullNames) {
            student.setFullName(fullName);
            assertEquals(fullName, student.getFullName());
        }
    }

    @Test
    void whenSetUniversityIdTheSameStudentObjectShouldBeReturned() {
        Student student = new Student();
        Set<String> universityIds = new HashSet<>();
        while (universityIds.size() < TEST_ITERATIONS) {
            universityIds.add(FAKER.idNumber().ssnValid());
        }

        for (String universityId : universityIds) {
            assertSame(student, student.setUniversityId(universityId));
        }
    }

    @Test
    void whenSetUniversityIdGetUnivesityIdShouldReturnThatValue() {
        Student student = new Student();
        Set<String> universityIds = new HashSet<>();
        while (universityIds.size() < TEST_ITERATIONS) {
            universityIds.add(FAKER.idNumber().ssnValid());
        }

        for (String universityId : universityIds) {
            student.setUniversityId(universityId);
            assertEquals(universityId, student.getUniversityId());
        }
    }

    @Test
    void whenSetCurrentCourseNumberTheSameStudentObjectShouldBeReturned() {
        Student student = new Student();
        Set<Integer> currentCourseNumbers = new HashSet<>();
        while (currentCourseNumbers.size() < Math.min(TEST_ITERATIONS, 4)) {
            currentCourseNumbers.add(FAKER.number().numberBetween(1, 5));
        }

        for (Integer currentCourseNumber : currentCourseNumbers) {
            assertSame(student, student.setCurrentCourseNumber(currentCourseNumber));
        }
    }

    @Test
    void whenSetCurrentCourseNumberGetCurrentCourseNumberShouldReturnThatValue() {
        Student student = new Student();
        Set<Integer> currentCourseNumbers = new HashSet<>();
        while (currentCourseNumbers.size() < Math.min(TEST_ITERATIONS, 4)) {
            currentCourseNumbers.add(FAKER.number().numberBetween(1, 5));
        }

        for (Integer currentCourseNumber : currentCourseNumbers) {
            student.setCurrentCourseNumber(currentCourseNumber);
            assertEquals(currentCourseNumber, student.getCurrentCourseNumber());
        }
    }

    @Test
    void whenSetAvgExamScoreTheSameStudentObjectShouldBeReturned() {
        Student student = new Student();
        Set<Float> avgExamScores = new HashSet<>();
        while (avgExamScores.size() < TEST_ITERATIONS) {
            avgExamScores.add(RANDOM.nextFloat() * 4 + 1);
        }

        for (Float avgExamScore : avgExamScores) {
            assertSame(student, student.setAvgExamScore(avgExamScore));
        }
    }

    @Test
    void whenSetAvgExamScoreGetAvgExamScoreShouldReturnThatValue() {
        Student student = new Student();
        Set<Float> avgExamScores = new HashSet<>();
        while (avgExamScores.size() < TEST_ITERATIONS) {
            avgExamScores.add(RANDOM.nextFloat() * 4 + 1);
        }

        for (Float avgExamScore : avgExamScores) {
            student.setAvgExamScore(avgExamScore);
            assertEquals(avgExamScore, student.getAvgExamScore());
        }
    }

    @Test
    void toStringShouldReturnFullNameUniversityIdCurrentCourseNumberAvgExamScore() {
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
            String expected = "Student{" +
                    "fullName='" + fullName + '\'' +
                    ", universityId='" + universityId + '\'' +
                    ", currentCourseNumber=" + currentCourseNumber +
                    ", avgExamScore=" + avgExamScore +
                    '}';
            String actual = student.setFullName(fullName)
                    .setUniversityId(universityId)
                    .setCurrentCourseNumber(currentCourseNumber)
                    .setAvgExamScore(avgExamScore)
                    .toString();
            assertEquals(expected, actual);
        }
    }
}