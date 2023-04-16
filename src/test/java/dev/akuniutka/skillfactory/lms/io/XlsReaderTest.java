package dev.akuniutka.skillfactory.lms.io;

import dev.akuniutka.skillfactory.lms.model.TestData;
import dev.akuniutka.skillfactory.lms.model.Student;
import dev.akuniutka.skillfactory.lms.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XlsReaderTest {
    private static final int TEST_ITERATIONS = 2;
    private static final String STUDENTS_FILE_NAME = "/studentsTestData.xlsx";
    private static final String UNIVERSITIES_FILE_NAME = "/universitiesTestData.xlsx";
    private final TestData testData = new TestData();

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenGetStudentsListShouldReturnCorrectListOfStudents() {
        List<Student> expected = new ArrayList<>();
        List<Student> actual;
        URL url = XlsReaderTest.class.getResource(STUDENTS_FILE_NAME);
        if (url == null) {
            fail("cannot create test data file");
            return;
        }
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            expected.add(testData.createRandomStudent());
        }
        try (TestStudentRepository repository = new TestStudentRepository(url.getPath())) {
            repository.addAll(expected);
        } catch (IOException e) {
            fail("cannot create test data file");
            return;
        }
        try {
            actual = XlsReader.getStudentsList(STUDENTS_FILE_NAME);
        } catch (RuntimeException e) {
            fail("method threw an exception");
            return;
        }
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }

    @Test
    void whenGetUniversitiesListShouldReturnCorrectListOfUniversities() {
        List<University> expected = new ArrayList<>();
        List<University> actual;
        URL url = XlsReaderTest.class.getResource(UNIVERSITIES_FILE_NAME);
        if (url == null) {
            fail("cannot create test data file");
            return;
        }
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            expected.add(testData.createRandomUniversity());
        }
        try (TestUniversityRepository repository = new TestUniversityRepository(url.getPath())) {
            repository.addAll(expected);
        } catch (IOException e) {
            fail("cannot create test data file");
            return;
        }
        try {
            actual = XlsReader.getUniversitiesList(UNIVERSITIES_FILE_NAME);
        } catch (RuntimeException e) {
            fail("method threw an exception");
            return;
        }
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
            assertEquals(expected.get(i).getMainProfile(), actual.get(i).getMainProfile());
        }
    }

}