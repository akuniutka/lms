package dev.akuniutka.skillfactory.lms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {
    private static final int TEST_ITERATIONS = 2;
    private final TestData testData = new TestData();

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenSetStudyProfileTheSameStatisticsObjectShouldBeReturned() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            StudyProfile studyProfile = testData.getNextUniversityMainProfile();
            assertSame(statistics, statistics.setStudyProfile(studyProfile));
        }
    }

    @Test
    void whenSetStudyProfileGetStudyProfileShouldReturnThatValue() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            StudyProfile studyProfile = testData.getNextUniversityMainProfile();
            statistics.setStudyProfile(studyProfile);
            assertEquals(studyProfile, statistics.getStudyProfile());
        }
    }

    @Test
    void whenSetAvgExamScoreTheSameStatisticsObjectShouldBeReturned() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            double avgExamScore = testData.getNextStudentAvgExamScore();
            assertSame(statistics, statistics.setAvgExamScore(avgExamScore));
        }
    }

    @Test
    void whenSetAvgExamScoreGetAvgExamScoreShouldReturnThatValue() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            double avgExamScore = testData.getNextStudentAvgExamScore();
            statistics.setAvgExamScore(avgExamScore);
            assertEquals(avgExamScore, statistics.getAvgExamScore());
        }
    }

    @Test
    void whenSetNumberOfStudentsTheSameStatisticsObjectShouldBeReturned() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            long numberOfStudents = testData.getNextNumberOfStudents();
            assertSame(statistics, statistics.setNumberOfStudents(numberOfStudents));
        }
    }

    @Test
    void whenSetNumberOfStudentsGetNumberOfStudentsShouldReturnThatValue() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            long numberOfStudents = testData.getNextNumberOfStudents();
            statistics.setNumberOfStudents(numberOfStudents);
            assertEquals(numberOfStudents, statistics.getNumberOfStudents());
        }
    }

    @Test
    void whenSetNumberOfUniversitiesTheSameStatisticsObjectShouldBeReturned() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            long numberOfUniversities = testData.getNextNumberOfUniversities();
            assertSame(statistics, statistics.setNumberOfUniversities(numberOfUniversities));
        }
    }

    @Test
    void whenSetNumberOfUniversitiesGetNumberOfUniversitiesShouldReturnThatValue() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            long numberOfUniversities = testData.getNextNumberOfUniversities();
            statistics.setNumberOfUniversities(numberOfUniversities);
            assertEquals(numberOfUniversities, statistics.getNumberOfUniversities());
        }
    }

    @Test
    void whenSetUniversityNamesTheSameStatisticsObjectShouldBeReturned() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            List<String> universityNames = new ArrayList<>();
            for (int j = 0; j < TEST_ITERATIONS; j++) {
                universityNames.add(testData.getNextUniversityFullName());
            }
            String expected = String.join(";", universityNames);
            assertSame(statistics, statistics.setUniversityNames(expected));
        }
    }

    @Test
    void whenSetUniversityNamesGetUniversityNamesShouldReturnCorrectList() {
        Statistics statistics = new Statistics();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            List<String> universityNames = new ArrayList<>();
            for (int j = 0; j < TEST_ITERATIONS; j++) {
                universityNames.add(testData.getNextUniversityFullName());
            }
            String expected = String.join(";", universityNames);
            statistics.setUniversityNames(expected);
            assertEquals(expected, statistics.getUniversityNames());
        }
    }

    @Test
    void toStringShouldReturnStudyProfileAvgExamScoreNumberOfStudentsNumberOfUniversitiesUniversityNames() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            StudyProfile studyProfile = testData.getNextUniversityMainProfile();
            double avgExamScore = testData.getNextStudentAvgExamScore();
            long numberOfStudents = testData.getNextNumberOfStudents();
            long numberOfUniversities = testData.getNextNumberOfUniversities();
            List<String> universityNames = new ArrayList<>();
            for (long j = 1; j < numberOfUniversities; j++) {
                universityNames.add(testData.getNextUniversityFullName());
            }
            String expected = "Statistics{" +
                    "studyProfile='" + studyProfile.getProfileName() + '\'' +
                    ", avgExamScore=" + avgExamScore +
                    ", numberOfStudents=" + numberOfStudents +
                    ", numberOfUniversities=" + numberOfUniversities +
                    ", universityNames='" + String.join(";", universityNames) + '\'' +
                    '}';
            String actual = new Statistics()
                    .setStudyProfile(studyProfile)
                    .setAvgExamScore(avgExamScore)
                    .setNumberOfStudents(numberOfStudents)
                    .setNumberOfUniversities(numberOfUniversities)
                    .setUniversityNames(String.join(";", universityNames))
                    .toString();
            assertEquals(expected, actual);
        }
    }
}