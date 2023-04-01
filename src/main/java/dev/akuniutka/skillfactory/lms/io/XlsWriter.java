package dev.akuniutka.skillfactory.lms.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.apache.poi.ss.usermodel.*;

import dev.akuniutka.skillfactory.lms.model.Statistics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class XlsWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XlsReader.class);
    private static final String STATISTICS_SHEET_NAME = "Статистика";

    private XlsWriter() {}

    public static void saveStatistics(String fileName, Collection<Statistics> data) throws IOException {
        LOGGER.debug("trying to create a workbook and file '" + fileName + "'");
        try (Workbook workbook = WorkbookFactory.create(true);
             FileOutputStream out = new FileOutputStream(fileName)
        ) {
            Sheet sheet = workbook.createSheet(STATISTICS_SHEET_NAME);
            int rowIndex = 0;
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0, CellType.STRING).setCellValue("Профиль обучения");
            row.createCell(1, CellType.STRING).setCellValue("Средний балл");
            row.createCell(2, CellType.STRING).setCellValue("Количество студентов");
            row.createCell(3, CellType.STRING).setCellValue("Количество университетов");
            row.createCell(4, CellType.STRING).setCellValue("Университеты");
            for (Statistics statistics : data) {
                row = sheet.createRow(rowIndex++);
                row.createCell(0, CellType.STRING).setCellValue(statistics.getStudyProfile().toString());
                row.createCell(1, CellType.NUMERIC).setCellValue(statistics.getAvgExamScore());
                row.createCell(2, CellType.NUMERIC).setCellValue(statistics.getNumberOfStudents());
                row.createCell(3, CellType.NUMERIC).setCellValue(statistics.getNumberOfUniversities());
                row.createCell(4, CellType.STRING).setCellValue(String.join(";", statistics.getUniversityNames()));
            }
            LOGGER.debug("writing statistics to file '" + fileName + "'");
            workbook.write(out);
            LOGGER.debug("statistics successfully wrtiten to file '" + fileName + "'");
        }
    }
}
