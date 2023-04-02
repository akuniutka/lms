package dev.akuniutka.skillfactory.lms.io;

import dev.akuniutka.skillfactory.lms.model.Statistics;
import dev.akuniutka.skillfactory.lms.model.StudyProfile;
import dev.akuniutka.skillfactory.lms.model.TestData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XlsWriterTest {
    private static final int TEST_ITERATIONS = 2;
    private static final String STATISTICS_FILE_NAME = "/statisticsTestData.xlsx";
    private static final String STATISTICS_SHEET_NAME = "Статистика";
    private static final short FONT_SIZE = 13;
    private static final TestData TEST_DATA = new TestData();
    private static final List<Statistics> EXPECTED = new ArrayList<>();
    private static Workbook workbook;
    private static Sheet sheet;

    @BeforeAll
    static void whenSaveStatisticsFileShouldExistAndContainStatisticsSheet() throws IOException {
        URL url = XlsReaderTest.class.getResource(STATISTICS_FILE_NAME);
        if (url == null) {
            fail("cannot find test data file");
            return;
        }
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            EXPECTED.add(TEST_DATA.createRandomStatistics());
        }
        XlsWriter.saveStatistics(url.getPath(), EXPECTED);
        try (FileInputStream in = new FileInputStream(url.getPath())) {
            workbook = new XSSFWorkbook(in);
        } catch (IOException exception) {
            fail("output file not found or corrupted");
        }
        sheet = workbook.getSheet(STATISTICS_SHEET_NAME);
        assertNotNull(sheet);
    }

    @Test
    void whenSaveStatisticsSheetShouldContainCorrectHeadingsRow() {
        Row row = sheet.getRow(0);
        assertAll(
                () -> assertEquals("Профиль обучения", row.getCell(0).getStringCellValue()),
                () -> assertEquals("Средний балл", row.getCell(1).getStringCellValue()),
                () -> assertEquals("Количество студентов", row.getCell(2).getStringCellValue()),
                () -> assertEquals("Количество университетов", row.getCell(3).getStringCellValue()),
                () -> assertEquals("Университеты", row.getCell(4).getStringCellValue())
        );
    }

    @Test
    void whenSaveStatisticsHeadingsRowShouldBeBold() {
        Row row = sheet.getRow(0);
        assertAll(
                () -> assertTrue(workbook.getFontAt(row.getCell(0).getCellStyle().getFontIndex()).getBold()),
                () -> assertTrue(workbook.getFontAt(row.getCell(1).getCellStyle().getFontIndex()).getBold()),
                () -> assertTrue(workbook.getFontAt(row.getCell(2).getCellStyle().getFontIndex()).getBold()),
                () -> assertTrue(workbook.getFontAt(row.getCell(3).getCellStyle().getFontIndex()).getBold()),
                () -> assertTrue(workbook.getFontAt(row.getCell(4).getCellStyle().getFontIndex()).getBold())
        );
    }

    @Test
    void whenSaveStatisticsHeadingsFontShouldBeOfCorrectSize() {
        Row row = sheet.getRow(0);
        assertAll(
                () -> assertEquals(
                        FONT_SIZE,
                        workbook.getFontAt(row.getCell(0).getCellStyle().getFontIndex()).getFontHeightInPoints()
                ),
                () -> assertEquals(
                        FONT_SIZE,
                        workbook.getFontAt(row.getCell(1).getCellStyle().getFontIndex()).getFontHeightInPoints()
                ),
                () -> assertEquals(
                        FONT_SIZE,
                        workbook.getFontAt(row.getCell(2).getCellStyle().getFontIndex()).getFontHeightInPoints()
                ),
                () -> assertEquals(
                        FONT_SIZE,
                        workbook.getFontAt(row.getCell(3).getCellStyle().getFontIndex()).getFontHeightInPoints()
                ),
                () -> assertEquals(
                        FONT_SIZE,
                        workbook.getFontAt(row.getCell(4).getCellStyle().getFontIndex()).getFontHeightInPoints()
                )
        );
    }

    @Test
    void whenSaveStatisticSheetShouldContainCorrectData() {
        List<Statistics> actual = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() != 0) {
                Statistics statistics = new Statistics()
                        .setStudyProfile(StudyProfile.valueOf(row.getCell(0).getStringCellValue()))
                        .setAvgExamScore((float) row.getCell(1).getNumericCellValue())
                        .setNumberOfStudents((int) row.getCell(2).getNumericCellValue())
                        .setNumberOfUniversities((int) row.getCell(3).getNumericCellValue())
                        .setUniversityNames(row.getCell(4).getStringCellValue());
                actual.add(statistics);
            }
        }
        assertEquals(EXPECTED.size(), actual.size());
        for (int i = 0; i < EXPECTED.size(); i++) {
            assertEquals(EXPECTED.get(i).toString(), actual.get(i).toString());
            assertEquals(EXPECTED.get(i).getStudyProfile(), actual.get(i).getStudyProfile());
        }
    }

    @AfterAll
    static void closeTestWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}