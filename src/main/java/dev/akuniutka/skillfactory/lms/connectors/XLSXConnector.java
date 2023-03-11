package dev.akuniutka.skillfactory.lms.connectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dev.akuniutka.skillfactory.lms.enums.StudyProfile;
import dev.akuniutka.skillfactory.lms.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class XLSXConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(XLSXConnector.class);
    private static final String STUDENTS_SHEET_NAME = "Студенты";
    private static final String UNIVERSITIES_SHEET_NAME = "Университеты";

    private XLSXConnector() {}

    public static List<Student> getStudentsList(String fileName) throws IOException {
        List<Student> students = new ArrayList<>();
        LOGGER.debug("trying to read data from sheet '" + STUDENTS_SHEET_NAME + "' in file '" + fileName + "'");
        try (InputStream is = XLSXConnector.class.getResourceAsStream(fileName);
                Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet(STUDENTS_SHEET_NAME);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    LOGGER.debug("skipped row #0");
                    continue;
                }
                Student student = new Student()
                        .setUniversityId(row.getCell(0).getStringCellValue())
                        .setFullName(row.getCell(1).getStringCellValue())
                        .setCurrentCourseNumber((int) (row.getCell(2).getNumericCellValue()))
                        .setAvgExamScore((float) (row.getCell(3).getNumericCellValue()));
                LOGGER.debug("read row #" + row.getRowNum());
                students.add(student);
            }
        }
        return students;
    }

    public static List<University> getUniversitiesList(String fileName) throws IOException {
        List<University> universities = new ArrayList<>();
        LOGGER.debug("trying to read data from sheet '" + UNIVERSITIES_SHEET_NAME + "' in file '" + fileName + "'");
        try (InputStream is = XLSXConnector.class.getResourceAsStream(fileName);
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet(UNIVERSITIES_SHEET_NAME);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    LOGGER.debug("skipped row #0");
                    continue;
                }
                University university = new University()
                        .setId(row.getCell(0).getStringCellValue())
                        .setFullName(row.getCell(1).getStringCellValue())
                        .setShortName(row.getCell(2).getStringCellValue())
                        .setYearOfFoundation((int)(row.getCell(3).getNumericCellValue()))
                        .setMainProfile(StudyProfile.valueOf(row.getCell(4).getStringCellValue()));
                LOGGER.debug("read row #" + row.getRowNum());
                universities.add(university);
            }
        }
        return universities;
    }
}
