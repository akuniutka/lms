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

    public static void saveStatistics(String path, Collection<Statistics> data) throws IOException {
        LOGGER.debug("trying to create a workbook and file '" + path + "'");
        try (Workbook workbook = WorkbookFactory.create(true);
             FileOutputStream out = new FileOutputStream(path)
        ) {
            Sheet sheet = workbook.createSheet(STATISTICS_SHEET_NAME);
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 13);
            font.setBold(true);
            cellStyle.setFont(font);
            int rowIndex = 0;
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0, CellType.STRING).setCellValue("Профиль обучения");
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1, CellType.STRING).setCellValue("Средний балл");
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2, CellType.STRING).setCellValue("Количество студентов");
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3, CellType.STRING).setCellValue("Количество университетов");
            row.getCell(3).setCellStyle(cellStyle);
            row.createCell(4, CellType.STRING).setCellValue("Университеты");
            row.getCell(4).setCellStyle(cellStyle);
            for (Statistics statistics : data) {
                row = sheet.createRow(rowIndex++);
                row.createCell(0, CellType.STRING).setCellValue(statistics.getStudyProfile().toString());
                row.createCell(1, CellType.NUMERIC).setCellValue(statistics.getAvgExamScore());
                row.createCell(2, CellType.NUMERIC).setCellValue(statistics.getNumberOfStudents());
                row.createCell(3, CellType.NUMERIC).setCellValue(statistics.getNumberOfUniversities());
                row.createCell(4, CellType.STRING).setCellValue(statistics.getUniversityNames());
            }
            LOGGER.debug("writing statistics to file '" + path + "'");
            workbook.write(out);
            LOGGER.debug("statistics successfully wrtiten to file '" + path + "'");
        }
    }
}
