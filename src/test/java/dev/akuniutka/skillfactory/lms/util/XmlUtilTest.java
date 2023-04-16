package dev.akuniutka.skillfactory.lms.util;

import dev.akuniutka.skillfactory.lms.comparator.StudentComparatorType;
import dev.akuniutka.skillfactory.lms.comparator.UniversityComparatorType;
import dev.akuniutka.skillfactory.lms.io.XlsReader;
import dev.akuniutka.skillfactory.lms.model.LmsData;
import dev.akuniutka.skillfactory.lms.model.Statistics;
import dev.akuniutka.skillfactory.lms.model.Student;
import dev.akuniutka.skillfactory.lms.model.University;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class XmlUtilTest {
    private static final String OUTPUT_DIR = "./xmlReqs";
    private static final String FILE_NAME_PREFIX = "req ";
    private static final String FILE_NAME_SUFFIX = ".xml";
    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH_mm_ss.SSSZ";
    private static String fileName;
    private static Date fileDate;
    private static final String TEST_DATA_FILE_NAME = "/universityInfo.xlsx";
    private static final String SAMPLE_FILE_NAME = "/sample.xml";


    @BeforeAll
    static void whenMarshalShouldCreateOneNewFile()  {
        List<Student> students;
        List<University> universities;
        try {
            students = XlsReader.getStudentsList(TEST_DATA_FILE_NAME);
            universities = XlsReader.getUniversitiesList(TEST_DATA_FILE_NAME);
        } catch (RuntimeException e) {
            fail("cannot read test data");
            return;
        }
        students.sort(Comparators.getComparator(StudentComparatorType.BY_AVG_EXAM_SCORE_DESC));
        universities.sort(Comparators.getComparator(UniversityComparatorType.BY_YEAR_OF_FOUNDATION));
        List<Statistics> statistics = StatUtil.getStatistics(universities, students);
        LmsData lmsData = new LmsData();
        lmsData.setStudents(students);
        lmsData.setUniversities(universities);
        lmsData.setStatistics(statistics);
        fileDate = lmsData.getProcessedAt();
        File file = new File(OUTPUT_DIR);
        String[] filesBefore = file.list();
        try {
            XmlUtil.marshal(lmsData);
        } catch (FileNotFoundException | JAXBException e) {
            fail("XMLUtil.marshal() threw an exception", e);
        }
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
        String expected = FILE_NAME_PREFIX + dateFormat.format(fileDate) + FILE_NAME_SUFFIX;
        assertEquals(expected, fileName);
    }

    @Test
    void whenMarshalFileShouldContainCorrectData() {
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