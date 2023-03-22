package dev.akuniutka.skillfactory.lms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private static final int TEST_ITERATIONS = 2;
    private final TestData testData = new TestData();

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenSetFullNameTheSameStudentObjectShouldBeReturned() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String fullName = testData.getNextStudentFullName();
            assertSame(student, student.setFullName(fullName));
        }
    }

    @Test
    void whenSetFullNameGetFullNameShouldReturnThatValue() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String fullName = testData.getNextStudentFullName();
            student.setFullName(fullName);
            assertEquals(fullName, student.getFullName());
        }
    }

    @Test
    void whenSetUniversityIdTheSameStudentObjectShouldBeReturned() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String universityId = testData.getNextUniversityID();
            assertSame(student, student.setUniversityId(universityId));
        }
    }

    @Test
    void whenSetUniversityIdGetUnivesityIdShouldReturnThatValue() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String universityId = testData.getNextUniversityID();
            student.setUniversityId(universityId);
            assertEquals(universityId, student.getUniversityId());
        }
    }

    @Test
    void whenSetCurrentCourseNumberTheSameStudentObjectShouldBeReturned() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            int currentCourseNumber = testData.getNextStudentCurrentCourseNumber();
            assertSame(student, student.setCurrentCourseNumber(currentCourseNumber));
        }
    }

    @Test
    void whenSetCurrentCourseNumberGetCurrentCourseNumberShouldReturnThatValue() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            int currentCourseNumber = testData.getNextStudentCurrentCourseNumber();
            student.setCurrentCourseNumber(currentCourseNumber);
            assertEquals(currentCourseNumber, student.getCurrentCourseNumber());
        }
    }

    @Test
    void whenSetAvgExamScoreTheSameStudentObjectShouldBeReturned() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            float avgExamScore = testData.getNextStudentAvgExamScore();
            assertSame(student, student.setAvgExamScore(avgExamScore));
        }
    }

    @Test
    void whenSetAvgExamScoreGetAvgExamScoreShouldReturnThatValue() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            float avgExamScore = testData.getNextStudentAvgExamScore();
            student.setAvgExamScore(avgExamScore);
            assertEquals(avgExamScore, student.getAvgExamScore());
        }
    }

    @Test
    void toStringShouldReturnFullNameUniversityIdCurrentCourseNumberAvgExamScore() {
        Student student = new Student();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String fullName = testData.getNextStudentFullName();
            String universityId = testData.getNextUniversityID();
            int currentCourseNumber = testData.getNextStudentCurrentCourseNumber();
            float avgExamScore = testData.getNextStudentAvgExamScore();
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