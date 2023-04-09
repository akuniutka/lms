package dev.akuniutka.skillfactory.lms.io;

import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dev.akuniutka.skillfactory.lms.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class XlsReader {
    private static final Logger LOGGER = Logger.getLogger(XlsReader.class.getName());

    private static final String STUDENTS_SHEET_NAME = "Студенты";
    private static final String UNIVERSITIES_SHEET_NAME = "Университеты";

    private XlsReader() {}

    public static List<Student> getStudentsList(String fileName) {
        List<Student> students = new ArrayList<>();
        LOGGER.fine("reading data from sheet '" + STUDENTS_SHEET_NAME + "' in file '" + fileName + "'");
        InputStream is = XlsReader.class.getResourceAsStream(fileName);
        if (is == null) {
            LOGGER.severe("file '" + fileName + "' not found among resources");
            throw new RuntimeException("file '" + fileName + "' not found among resources");
        }
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet(STUDENTS_SHEET_NAME);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    LOGGER.finer("skipped row #0");
                    continue;
                }
                Student student = new Student()
                        .setUniversityId(row.getCell(0).getStringCellValue())
                        .setFullName(row.getCell(1).getStringCellValue())
                        .setCurrentCourseNumber((int) row.getCell(2).getNumericCellValue())
                        .setAvgExamScore((float) row.getCell(3).getNumericCellValue());
                LOGGER.finer("read row #" + row.getRowNum());
                students.add(student);
            }
        } catch (IOException e) {
            LOGGER.severe("reading error in file '" + fileName + "'");
            throw new RuntimeException("error reading from file '" + fileName + "'");
        }
        LOGGER.fine("data succefully read from sheet '" + STUDENTS_SHEET_NAME + "' in file '" + fileName + "'");
        return students;
    }

    public static List<University> getUniversitiesList(String fileName) {
        List<University> universities = new ArrayList<>();
        LOGGER.fine("reading data from sheet '" + UNIVERSITIES_SHEET_NAME + "' in file '" + fileName + "'");
        InputStream is = XlsReader.class.getResourceAsStream(fileName);
        if (is == null) {
            LOGGER.severe("file '" + fileName + "' not found among resources");
            throw new RuntimeException("file '" + fileName + "' not found among resources");
        }
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet(UNIVERSITIES_SHEET_NAME);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    LOGGER.finer("skipped row #0");
                    continue;
                }
                University university = new University()
                        .setId(row.getCell(0).getStringCellValue())
                        .setFullName(row.getCell(1).getStringCellValue())
                        .setShortName(row.getCell(2).getStringCellValue())
                        .setYearOfFoundation((int) row.getCell(3).getNumericCellValue())
                        .setMainProfile(StudyProfile.valueOf(row.getCell(4).getStringCellValue()));
                LOGGER.finer("read row #" + row.getRowNum());
                universities.add(university);
            }
        } catch (IOException e) {
            LOGGER.severe("reading error in file '" + fileName + "'");
            throw new RuntimeException("error reading from file '" + fileName + "'");
        }
        LOGGER.fine("data succefully read from sheet '" + STUDENTS_SHEET_NAME + "' in file '" + fileName + "'");
        return universities;
    }
}
