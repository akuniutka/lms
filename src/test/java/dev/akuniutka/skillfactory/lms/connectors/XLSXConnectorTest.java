package dev.akuniutka.skillfactory.lms.connectors;

import dev.akuniutka.skillfactory.lms.TestData;
import dev.akuniutka.skillfactory.lms.models.Student;
import dev.akuniutka.skillfactory.lms.models.University;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class XLSXConnectorTest {
    private static final int TEST_ITERATIONS = 2;
    private static final Random RANDOM = new Random();
    private static final String STUDENTS_FILE_NAME = "/studentsTestData.xlsx";
    private static final String STUDENTS_SHEET_NAME = "Студенты";
    private static final String UNIVERSITIES_FILE_NAME = "/universitiesTestData.xlsx";
    private static final String UNIVERSITIES_SHEET_NAME = "Университеты";
    private final TestData testData = new TestData();

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenGetStudentsListShouldReturnCorrectListOfStudents() {
        List<Student> expected = new ArrayList<>();
        List<Student> actual = null;
        String path = XLSXConnectorTest.class.getResource(STUDENTS_FILE_NAME).getPath();
        try (Workbook workbook = WorkbookFactory.create(true);
             FileOutputStream out = new FileOutputStream(path)
        ) {
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                workbook.createSheet();
            }
            workbook.setSheetName(RANDOM.nextInt(TEST_ITERATIONS), STUDENTS_SHEET_NAME);
            Sheet sheet = workbook.getSheet(STUDENTS_SHEET_NAME);
            Row row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("id университета");
            row.createCell(1, CellType.STRING).setCellValue("ФИО");
            row.createCell(2, CellType.STRING).setCellValue("Курс");
            row.createCell(3, CellType.STRING).setCellValue("Средний балл");
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                Student student = testData.createRandomStudent();
                row = sheet.createRow(i + 1);
                row.createCell(0, CellType.STRING).setCellValue(student.getUniversityId());
                row.createCell(1, CellType.STRING).setCellValue(student.getFullName());
                row.createCell(2, CellType.NUMERIC).setCellValue(student.getCurrentCourseNumber());
                row.createCell(3, CellType.NUMERIC).setCellValue(student.getAvgExamScore());
                expected.add(student);
            }
            workbook.write(out);
        } catch (IOException e) {
            fail("cannot create test data file");
        }
        try {
            actual = XLSXConnector.getStudentsList(STUDENTS_FILE_NAME);
        } catch (IOException e) {
            fail("method threw an exception");
        }
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }

    @Test
    void whenGetUniversitiesListShouldReturnCorrectListOfUniversities() {
        List<University> expected = new ArrayList<>();
        List<University> actual = null;
        String path = XLSXConnectorTest.class.getResource(UNIVERSITIES_FILE_NAME).getPath();
        try (Workbook workbook = WorkbookFactory.create(true);
             FileOutputStream out = new FileOutputStream(path)
        ) {
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                workbook.createSheet();
            }
            workbook.setSheetName(RANDOM.nextInt(TEST_ITERATIONS), UNIVERSITIES_SHEET_NAME);
            Sheet sheet = workbook.getSheet(UNIVERSITIES_SHEET_NAME);
            Row row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("id университета");
            row.createCell(1, CellType.STRING).setCellValue("Полное название");
            row.createCell(2, CellType.STRING).setCellValue("Аббревиатура");
            row.createCell(3, CellType.STRING).setCellValue("Год основания");
            row.createCell(4, CellType.STRING).setCellValue("Профиль обучения");
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                University university = testData.createRandomUniversity();
                row = sheet.createRow(i + 1);
                row.createCell(0, CellType.STRING).setCellValue(university.getId());
                row.createCell(1, CellType.STRING).setCellValue(university.getFullName());
                row.createCell(2, CellType.STRING).setCellValue(university.getShortName());
                row.createCell(3, CellType.NUMERIC).setCellValue(university.getYearOfFoundation());
                row.createCell(4, CellType.STRING).setCellValue(university.getMainProfile().toString());
                expected.add(university);
            }
            workbook.write(out);
        } catch (IOException e) {
            fail("cannot create test data file");
        }
        try {
            actual = XLSXConnector.getUniversitiesList(UNIVERSITIES_FILE_NAME);
        } catch (IOException e) {
            fail("method threw an exception");
        }
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
            assertEquals(expected.get(i).getMainProfile(), actual.get(i).getMainProfile());
        }
    }
}