package dev.akuniutka.skillfactory.lms;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import dev.akuniutka.skillfactory.lms.comparator.StudentComparatorType;
import dev.akuniutka.skillfactory.lms.comparator.UniversityComparatorType;
import dev.akuniutka.skillfactory.lms.model.*;
import dev.akuniutka.skillfactory.lms.io.*;
import dev.akuniutka.skillfactory.lms.util.*;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String INPUT_FILE_NAME = "/universityInfo.xlsx";
    private static final String OUTPUT_FILE_NAME = "statistics.xlsx";

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            LOGGER.warning("Cannot read logging properties");
        }
        LOGGER.info("application started");

        List<University> universities = XlsReader.getUniversitiesList(INPUT_FILE_NAME);
        List<Student> students = XlsReader.getStudentsList(INPUT_FILE_NAME);

        universities.sort(Comparators.getComparator(UniversityComparatorType.BY_YEAR_OF_FOUNDATION));
        students.sort(Comparators.getComparator(StudentComparatorType.BY_AVG_EXAM_SCORE_DESC));

        List<Statistics> statistics = StatUtil.getStatistics(universities, students);

        XlsWriter.saveStatistics(OUTPUT_FILE_NAME, statistics);

        LmsData lmsData = new LmsData();
        lmsData.setStudents(students);
        lmsData.setUniversities(universities);
        lmsData.setStatistics(statistics);

        XmlUtil.marshal(lmsData);
        JsonUtil.serializeToFIle(lmsData);

        LOGGER.info("application stopped");
    }
}
