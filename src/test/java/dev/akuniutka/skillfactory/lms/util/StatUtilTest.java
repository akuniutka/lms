package dev.akuniutka.skillfactory.lms.util;

import dev.akuniutka.skillfactory.lms.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatUtilTest {
    private static final int TEST_ITERATIONS = 2;
    private final TestData testData = new TestData();

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenGetStatisticsShouldReturnCorrectStatisticsList() {
        List<University> universities = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Statistics> expected = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS && i < StudyProfile.values().length; i++) {
            StudyProfile studyProfile = testData.getNextUniversityMainProfile();
            Statistics statistics = new Statistics();
            statistics.setStudyProfile(studyProfile);
            List<String> universityNames = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                University university = testData.createRandomUniversity(studyProfile);
                statistics.setNumberOfUniversities(statistics.getNumberOfUniversities() + 1);
                universityNames.add(university.getFullName());
                universities.add(university);
                for (int k = 0; k < j; k++) {
                    Student student = testData.createRandomStudent(university.getId());
                    statistics.setNumberOfStudents(statistics.getNumberOfStudents() + 1);
                    statistics.setAvgExamScore(statistics.getAvgExamScore() + student.getAvgExamScore());
                    students.add(student);
                }
            }
            statistics.setUniversityNames(String.join(";", universityNames));
            if (statistics.getNumberOfStudents() != 0) {
                statistics.setAvgExamScore(statistics.getAvgExamScore() / statistics.getNumberOfStudents());
            }
            expected.add(statistics);
        }
        List<Statistics> actual = StatUtil.getStatistics(universities, students);
        expected.sort(Comparator.comparing(Statistics::getStudyProfile));
        actual.sort(Comparator.comparing(Statistics::getStudyProfile));
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
            assertEquals(expected.get(i).getStudyProfile(), actual.get(i).getStudyProfile());
        }
    }
}