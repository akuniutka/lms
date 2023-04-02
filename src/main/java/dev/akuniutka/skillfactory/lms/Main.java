package dev.akuniutka.skillfactory.lms;

import dev.akuniutka.skillfactory.lms.io.XlsWriter;
import dev.akuniutka.skillfactory.lms.model.*;
import dev.akuniutka.skillfactory.lms.io.XlsReader;

import dev.akuniutka.skillfactory.lms.util.StatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String INPUT_FILE_NAME = "/universityinfo.xlsx";
    private static final String OUTPUT_FILE_NAME = "statistics.xlsx";

    public static void main(String[] args) throws IOException {
        LOGGER.info("application started");

        LOGGER.info("loading data from XLSX file");

        List<University> universities = XlsReader.getUniversitiesList(INPUT_FILE_NAME);
        List<Student> students = XlsReader.getStudentsList(INPUT_FILE_NAME);

        LOGGER.info("calculculating statistics");

        List<Statistics> statistics = StatUtil.getStatistics(universities, students);

        LOGGER.info("writing statistics to XLSX file");

        XlsWriter.saveStatistics(OUTPUT_FILE_NAME, statistics);

        LOGGER.info("application stopped");
    }
}
