package dev.akuniutka.skillfactory.lms.connectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dev.akuniutka.skillfactory.lms.enums.StudyProfile;
import dev.akuniutka.skillfactory.lms.models.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class XLSXConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(XLSXConnector.class);

    private XLSXConnector() {}

    public static List<Student> getStudentsList(String fileName) throws IOException {
        List<Student> students = new ArrayList<>();
        LOGGER.debug("Opening XLSX workbook from file '" + fileName + "'");
//        XSSFWorkbook workbook = new XSSFWorkbook(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(XLSXConnector.class.getResourceAsStream(fileName));
        LOGGER.debug("Locating worksheet #0");
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        // Skip headings row
        if (rows.hasNext()) {
            Row row = rows.next();
            LOGGER.debug("Skipped row #" + row.getRowNum());
        }
        while (rows.hasNext()) {
            Row row = rows.next();
            Student student = new Student()
                    .setUniversityId(row.getCell(0).getStringCellValue())
                    .setFullName(row.getCell(1).getStringCellValue())
                    .setCurrentCourseNumber((int)(row.getCell(2).getNumericCellValue()))
                    .setAvgExamScore((float)(row.getCell(3).getNumericCellValue()));
            LOGGER.debug("Read row #" + row.getRowNum());
            students.add(student);
        }
        workbook.close();
        return students;
    }

    public static List<University> getUniversitiesList(String fileName) throws IOException {
        List<University> universities = new ArrayList<>();
        LOGGER.debug("Opening XLSX workbook from file '" + fileName + "'");
//        XSSFWorkbook workbook = new XSSFWorkbook(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(XLSXConnector.class.getResourceAsStream(fileName));
        LOGGER.debug("Locating worksheet #1");
        XSSFSheet sheet = workbook.getSheetAt(1);
        Iterator<Row> rows = sheet.iterator();
        // Skip headings row
        if (rows.hasNext()) {
            Row row = rows.next();
            LOGGER.debug("Skipped row #" + row.getRowNum());
        }
        while (rows.hasNext()) {
            Row row = rows.next();
            University university = new University()
                    .setId(row.getCell(0).getStringCellValue())
                    .setFullName(row.getCell(1).getStringCellValue())
                    .setShortName(row.getCell(2).getStringCellValue())
                    .setYearOfFoundation((int)(row.getCell(3).getNumericCellValue()))
                    .setMainProfile(StudyProfile.valueOf(row.getCell(4).getStringCellValue()));
            LOGGER.debug("Read row #" + row.getRowNum());
            universities.add(university);
        }
        workbook.close();
        return universities;
    }
}
