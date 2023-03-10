package dev.akuniutka.skillfactory.lms.connectors;

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
    private XLSXConnector() {}

    public static List<Student> getStudentsList(String fileName) throws IOException {
        List<Student> students = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(fileName);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        // Skip headings row
        if (rows.hasNext()) {
            rows.next();
        }
        while (rows.hasNext()) {
            Row row = rows.next();
            Student student = new Student()
                    .setUniversityId(row.getCell(0).getStringCellValue())
                    .setFullName(row.getCell(1).getStringCellValue())
                    .setCurrentCourseNumber((int)(row.getCell(2).getNumericCellValue()))
                    .setAvgExamScore((float)(row.getCell(3).getNumericCellValue()));
            students.add(student);
        }
        workbook.close();
        return students;
    }

    public static List<University> getUniversitiesList(String fileName) throws IOException {
        List<University> universities = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(fileName);
        XSSFSheet sheet = workbook.getSheetAt(1);
        Iterator<Row> rows = sheet.iterator();
        // Skip headings row
        if (rows.hasNext()) {
            rows.next();
        }
        while (rows.hasNext()) {
            Row row = rows.next();
            University university = new University()
                    .setId(row.getCell(0).getStringCellValue())
                    .setFullName(row.getCell(1).getStringCellValue())
                    .setShortName(row.getCell(2).getStringCellValue())
                    .setYearOfFoundation((int)(row.getCell(3).getNumericCellValue()))
                    .setMainProfile(StudyProfile.valueOf(row.getCell(4).getStringCellValue()));
            universities.add(university);
        }
        workbook.close();
        return universities;
    }
}
