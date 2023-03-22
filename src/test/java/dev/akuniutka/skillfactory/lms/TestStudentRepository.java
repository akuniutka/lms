package dev.akuniutka.skillfactory.lms;

import dev.akuniutka.skillfactory.lms.models.Student;
import org.apache.poi.ss.usermodel.*;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TestStudentRepository implements Closeable {
    private static final String STUDENTS_SHEET_NAME = "Студенты";
    private final String path;
    private Workbook workbook;
    private final Sheet sheet;
    private int currentRow = 0;

    public TestStudentRepository(String path) throws IOException {
        this.path = path;
        workbook = WorkbookFactory.create(true);
        sheet = workbook.createSheet(STUDENTS_SHEET_NAME);
        Row row = sheet.createRow(currentRow++);
        row.createCell(0, CellType.STRING).setCellValue("id университета");
        row.createCell(1, CellType.STRING).setCellValue("ФИО");
        row.createCell(2, CellType.STRING).setCellValue("Курс");
        row.createCell(3, CellType.STRING).setCellValue("Средний балл");
    }

    public void add(Student student) {
        Row row = sheet.createRow(currentRow++);
        row.createCell(0, CellType.STRING).setCellValue(student.getUniversityId());
        row.createCell(1, CellType.STRING).setCellValue(student.getFullName());
        row.createCell(2, CellType.NUMERIC).setCellValue(student.getCurrentCourseNumber());
        row.createCell(3, CellType.NUMERIC).setCellValue(student.getAvgExamScore());
    }

    public void addAll(List<Student> students) {
        for (Student student : students) {
            add(student);
        }
    }

    @Override
    public void close() throws IOException {
        if (workbook == null) {
            return;
        }
        try (FileOutputStream out = new FileOutputStream(path);
             Workbook workbook = this.workbook
        ) {
            workbook.write(out);
        } finally {
            this.workbook = null;
        }
    }
}
