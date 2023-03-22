package dev.akuniutka.skillfactory.lms;

import dev.akuniutka.skillfactory.lms.models.University;
import org.apache.poi.ss.usermodel.*;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TestUniversityRepository implements Closeable {
    private static final String UNIVERSITIES_SHEET_NAME = "Университеты";
    private final String path;
    private Workbook workbook;
    private final Sheet sheet;
    private int currentRow = 0;

    public TestUniversityRepository(String path) throws IOException {
        this.path = path;
        workbook = WorkbookFactory.create(true);
        sheet = workbook.createSheet(UNIVERSITIES_SHEET_NAME);
        Row row = sheet.createRow(currentRow++);
        row.createCell(0, CellType.STRING).setCellValue("id университета");
        row.createCell(1, CellType.STRING).setCellValue("Полное название");
        row.createCell(2, CellType.STRING).setCellValue("Аббревиатура");
        row.createCell(3, CellType.STRING).setCellValue("Год основания");
        row.createCell(4, CellType.STRING).setCellValue("Профиль обучения");
    }

    public void add(University university) {
        Row row = sheet.createRow(currentRow++);
        row.createCell(0, CellType.STRING).setCellValue(university.getId());
        row.createCell(1, CellType.STRING).setCellValue(university.getFullName());
        row.createCell(2, CellType.STRING).setCellValue(university.getShortName());
        row.createCell(3, CellType.NUMERIC).setCellValue(university.getYearOfFoundation());
        row.createCell(4, CellType.STRING).setCellValue(university.getMainProfile().toString());
    }

    public void addAll(List<University> universities) {
        for (University university : universities) {
            add(university);
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
