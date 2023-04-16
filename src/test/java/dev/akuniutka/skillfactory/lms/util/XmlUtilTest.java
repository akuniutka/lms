package dev.akuniutka.skillfactory.lms.util;

import dev.akuniutka.skillfactory.lms.comparator.StudentComparatorType;
import dev.akuniutka.skillfactory.lms.comparator.UniversityComparatorType;
import dev.akuniutka.skillfactory.lms.io.XlsReader;
import dev.akuniutka.skillfactory.lms.model.LmsData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class XmlUtilTest {
    private static final String OUTPUT_DIR = "./xmlReqs";
    private static final String FILE_NAME_PREFIX = "req ";
    private static final String FILE_NAME_SUFFIX = ".xml";
    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH_mm_ss.SSSZ";
    private static Date testStartDate;
    private static Date testFinishDate;
    private static String fileName;
    private static Date fileDate;
    private static final String TEST_DATA_FILE_NAME = "/universityInfo.xlsx";
    private static final String SAMPLE_FILE_NAME = "/sample.xml";


    @BeforeAll
    static void whenMarshalShouldCreateOneNewFile() {
        LmsData lmsData = new LmsData();
        try {
            lmsData.setStudents(XlsReader.getStudentsList(TEST_DATA_FILE_NAME));
            lmsData.setUniversities(XlsReader.getUniversitiesList(TEST_DATA_FILE_NAME));
        } catch (RuntimeException e) {
            fail("cannot read test data");
            return;
        }
        lmsData.getStudents().sort(
                Comparators.getComparator(StudentComparatorType.BY_AVG_EXAM_SCORE_DESC));
        lmsData.getUniversities().sort(
                Comparators.getComparator(UniversityComparatorType.BY_YEAR_OF_FOUNDATION));
        lmsData.setStatistics(StatUtil.getStatistics(
                lmsData.getUniversities(),
                lmsData.getStudents()
        ));
        File file = new File(OUTPUT_DIR);
        String[] filesBefore = file.list();
        testStartDate = new Date();
        try {
            XmlUtil.marshal(lmsData);
        } catch (FileNotFoundException e) {
            fail("XMLUtil.marshal() threw an exception");
        }
        testFinishDate = new Date();
        String[] filesAfter = file.list();
        assertNotNull(filesAfter);
        List<String> newFiles = new ArrayList<>(Arrays.asList(filesAfter));
        if (filesBefore != null) {
            newFiles.removeAll(new ArrayList<>(Arrays.asList(filesBefore)));
        }
        assertEquals(1, newFiles.size());
        fileName = newFiles.get(0);
    }

    @Test
    void whenMarshalFileShouldhaveCorrectName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        int timestampLength = dateFormat.format(new Date()).length();
        assertEquals(FILE_NAME_PREFIX.length() + timestampLength +
                FILE_NAME_SUFFIX.length(), fileName.length());
        assertTrue(fileName.startsWith(FILE_NAME_PREFIX));
        assertTrue(fileName.endsWith(FILE_NAME_SUFFIX));
        String timestamp = fileName.substring(FILE_NAME_PREFIX.length(),
                FILE_NAME_PREFIX.length() + timestampLength);
        dateFormat.setLenient(false);
        ParsePosition parsePosition = new ParsePosition(0);
        fileDate = dateFormat.parse(timestamp, parsePosition);
        assertNotNull(fileDate);
        assertEquals(timestampLength, parsePosition.getIndex());
        assertTrue(testStartDate.compareTo(fileDate) <= 0);
        assertTrue(testFinishDate.compareTo(fileDate) >= 0);
    }

    @AfterAll
    static void whenMarshalFileShouldContainCorrectData() {
        URL url = XmlUtilTest.class.getResource(SAMPLE_FILE_NAME);
        if (url == null) {
            fail("cannot read sample xml file");
        }
        int currentLine = 1;
        try (
                Scanner sampleFile = new Scanner(new File(url.getPath()));
                Scanner generatedFile = new Scanner(new File(OUTPUT_DIR + "/" + fileName))
        ) {
            while (sampleFile.hasNextLine()) {
                assertTrue(generatedFile.hasNextLine());
                String expected = sampleFile.nextLine();
                if (expected.startsWith("    <processedAt>")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSXXX");
                    expected = "    <processedAt>" +
                            dateFormat.format(fileDate).replace(" ", "T") +
                            "</processedAt>";
                }
                assertEquals(expected, generatedFile.nextLine(), "in line " + currentLine++);
            }
            assertFalse(generatedFile.hasNextLine());
        } catch (FileNotFoundException e) {
            fail("cannot compare sample and generated files");
        }
    }
}