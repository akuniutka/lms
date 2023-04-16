package dev.akuniutka.skillfactory.lms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.akuniutka.skillfactory.lms.comparator.StudentComparatorType;
import dev.akuniutka.skillfactory.lms.comparator.UniversityComparatorType;
import dev.akuniutka.skillfactory.lms.io.XlsReader;
import dev.akuniutka.skillfactory.lms.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {
    private static final int TEST_ITERATIONS = 2;
    private static final String OUTPUT_DIR = "./jsonReqs";
    private static final String FILE_NAME_PREFIX = "req ";
    private static final String FILE_NAME_SUFFIX = ".json";
    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH_mm_ss.SSSZ";
    private static String fileName;
    private static Date fileDate;
    private static final String TEST_DATA_FILE_NAME = "/universityInfo.xlsx";
    private static final String SAMPLE_FILE_NAME = "/sample.json";
    private final TestData testData = new TestData();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @BeforeAll
    static void whenSerializeToFileShouldCreateOneNewFile() {
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
        JsonUtil.serializeToFIle(lmsData);
        String[] filesAfter = file.list();
        assertNotNull(filesAfter);
        List<String> newFiles = new ArrayList<>(Arrays.asList(filesAfter));
        if (filesBefore != null) {
            newFiles.removeAll(new ArrayList<>(Arrays.asList(filesBefore)));
        }
        assertEquals(1, newFiles.size());
        fileName = newFiles.get(0);
    }

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenSerializeStudentShouldReturnCorrectString() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Student student = testData.createRandomStudent();
            String expected = "{" +
                    "\n  \"studentFullName\": \"" + student.getFullName() + '"' +
                    ",\n  \"studentUniversityId\": \"" + student.getUniversityId() + '"' +
                    ",\n  \"studentCurrentCourseNumber\": " + student.getCurrentCourseNumber() +
                    ",\n  \"studentAvgExamScore\": " + student.getAvgExamScore() +
                    "\n}";
            String actual = JsonUtil.serialize(student);
            assertEquals(expected, actual);
        }
    }

    @Test
    void whenSerializeUniversityShouldReturnCorrectString() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            University university = testData.createRandomUniversity();
            String expected = "{" +
                    "\n  \"universityId\": \"" + university.getId() + '"' +
                    ",\n  \"universityFullName\": \"" + university.getFullName() + '"' +
                    ",\n  \"universityShortName\": \"" + university.getShortName() + '"' +
                    ",\n  \"universityYearOfFoundation\": " + university.getYearOfFoundation() +
                    ",\n  \"universityMainProfile\": \"" + university.getMainProfile() + '"' +
                    "\n}";
            String actual = JsonUtil.serialize(university);
            assertEquals(expected, actual);
        }
    }

    @Test
    void whenSerializeListOfStudentsShouldReturnCorrectString() {
        List<Student> students = new ArrayList<>();
        StringBuilder expected = new StringBuilder("[");
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Student student = testData.createRandomStudent();
            expected.append(students.size() == 0 ? "" : ",").append("\n  {")
                    .append("\n    \"studentFullName\": \"").append(student.getFullName()).append('"')
                    .append(",\n    \"studentUniversityId\": \"").append(student.getUniversityId()).append('"')
                    .append(",\n    \"studentCurrentCourseNumber\": ").append(student.getCurrentCourseNumber())
                    .append(",\n    \"studentAvgExamScore\": ").append(student.getAvgExamScore())
                    .append("\n  }");
            students.add(student);
        }
        expected.append("\n]");
        String actual = JsonUtil.serialize(students);
        assertEquals(expected.toString(), actual);
    }

    @Test
    void whenSerializeListOfUniversitiesShouldReturnCorrectString() {
        List<University> universities = new ArrayList<>();
        StringBuilder expected = new StringBuilder("[");
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            University university = testData.createRandomUniversity();
            expected.append(universities.size() == 0 ? "" : ",").append("\n  {")
                    .append("\n    \"universityId\": \"").append(university.getId()).append('"')
                    .append(",\n    \"universityFullName\": \"").append(university.getFullName()).append('"')
                    .append(",\n    \"universityShortName\": \"").append(university.getShortName()).append('"')
                    .append(",\n    \"universityYearOfFoundation\": ").append(university.getYearOfFoundation())
                    .append(",\n    \"universityMainProfile\": \"").append(university.getMainProfile()).append('"')
                    .append("\n  }");
            universities.add(university);
        }
        expected.append("\n]");
        String actual = JsonUtil.serialize(universities);
        assertEquals(expected.toString(), actual);
    }

    @Test
    void whenDeserializeStudentShouldReturnCorrectStudentObject() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Student expected = testData.createRandomStudent();
            String json = gson.toJson(expected);
            Student actual = JsonUtil.deserialize(json, new TypeToken<Student>() {
            }.getType());
            assertEquals(expected.toString(), actual.toString());
        }
    }

    @Test
    void whenDeserializeUniversityShouldReturnCorrectUniversityObject() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            University expected = testData.createRandomUniversity();
            String json = gson.toJson(expected);
            University actual = JsonUtil.deserialize(json, new TypeToken<University>() {
            }.getType());
            assertEquals(expected.toString(), actual.toString());
            assertEquals(expected.getMainProfile(), actual.getMainProfile());
        }
    }

    @Test
    void whenDeserializeListOfStudentsShouldReturnCorrectList() {
        List<Student> expected = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            expected.add(testData.createRandomStudent());
        }
        String json = gson.toJson(expected);
        List<Student> actual = JsonUtil.deserialize(json, new TypeToken<ArrayList<Student>>() {
        }.getType());
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }

    @Test
    void whenDeserializeListOfUniversitiesShouldReturnCorrectList() {
        List<University> expected = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            expected.add(testData.createRandomUniversity());
        }
        String json = gson.toJson(expected);
        List<University> actual = JsonUtil.deserialize(json, new TypeToken<ArrayList<University>>() {
        }.getType());
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
            assertEquals(expected.get(i).getMainProfile(), actual.get(i).getMainProfile());
        }
    }

    @Test
    void whenSerializeToFileFileShouldHaveCorrectName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        String expected = FILE_NAME_PREFIX + dateFormat.format(fileDate) + FILE_NAME_SUFFIX;
        assertEquals(expected, fileName);
    }

    @Test
    void whenSerializeToFileFileShouldContainCorrectData() {
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
                if (expected.startsWith("  \"processedAt\": ")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy, H:mm:ss a", Locale.US);
                    expected = "  \"processedAt\": \"" + dateFormat.format(fileDate) + "\"";
                }
                assertEquals(expected, generatedFile.nextLine(), "in line " + currentLine++);
            }
            assertFalse(generatedFile.hasNextLine());
        } catch (FileNotFoundException e) {
            fail("cannot compare sample and generated files");
        }
    }
}