package dev.akuniutka.skillfactory.lms.io;

import dev.akuniutka.skillfactory.lms.model.Statistics;
import dev.akuniutka.skillfactory.lms.model.StudyProfile;
import dev.akuniutka.skillfactory.lms.model.TestData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XlsWriterTest {
    private static final int TEST_ITERATIONS = 2;
    private static final String STATISTICS_FILE_NAME = "/statisticsTestData.xlsx";
    private static final String STATISTICS_SHEET_NAME = "Статистика";

    private final TestData testData = new TestData();

    @Test
    void whenSaveStatisticShouldCreateCorrectFile() throws IOException {
        URL url = XlsReaderTest.class.getResource(STATISTICS_FILE_NAME);
        if (url == null) {
            fail("cannot find test data file");
            return;
        }
        List<Statistics> expected = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            expected.add(testData.createRandomStatistics());
        }
        XlsWriter.saveStatistics(url.getPath(), expected);
        List<Statistics> actual = new ArrayList<>();
        try (FileInputStream in = new FileInputStream(url.getPath());
             Workbook workbook = new XSSFWorkbook(in)
        ) {
            Sheet sheet = workbook.getSheet(STATISTICS_SHEET_NAME);
            assertNotNull(sheet);
            for (Row row : sheet) {
                int i = row.getRowNum();
                if (i == 0) {
                    assertEquals("Профиль обучения", row.getCell(0).getStringCellValue());
                    assertEquals("Средний балл", row.getCell(1).getStringCellValue());
                    assertEquals("Количество студентов", row.getCell(2).getStringCellValue());
                    assertEquals("Количество университетов", row.getCell(3).getStringCellValue());
                    assertEquals("Университеты", row.getCell(4).getStringCellValue());
                } else {
                    Statistics statistics = new Statistics()
                            .setStudyProfile(StudyProfile.valueOf(row.getCell(0).getStringCellValue()))
                            .setAvgExamScore((float) row.getCell(1).getNumericCellValue())
                            .setNumberOfStudents((int) row.getCell(2).getNumericCellValue())
                            .setNumberOfUniversities((int) row.getCell(3).getNumericCellValue())
                            .setUniversityNames(Arrays.asList(row.getCell(4).getStringCellValue().split(";")));
                    actual.add(statistics);
                }
            }
        }
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
            assertEquals(expected.get(i).getStudyProfile(), actual.get(i).getStudyProfile());

        }
    }
}