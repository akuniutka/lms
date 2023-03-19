package dev.akuniutka.skillfactory.lms;

import dev.akuniutka.skillfactory.lms.models.*;
import dev.akuniutka.skillfactory.lms.enums.*;
import dev.akuniutka.skillfactory.lms.comparators.*;
import dev.akuniutka.skillfactory.lms.connectors.XLSXConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String DATA_FILE_NAME = "/universityinfo.xlsx";

    public static void main(String[] args) throws IOException {
        LOGGER.info("application started");

        List<University> universities = XLSXConnector.getUniversitiesList(DATA_FILE_NAME);
        List<Student> students= XLSXConnector.getStudentsList(DATA_FILE_NAME);
        UniversityComparator universityComparator;
        StudentComparator studentComparator;


        universityComparator = Comparators.getComparator(UniversityComparatorType.BY_ID);
        printListSorted(universities, universityComparator, "Universities by id:");

        universityComparator = Comparators.getComparator(UniversityComparatorType.BY_FULL_NAME);
        printListSorted(universities, universityComparator, "Universities by full name:");

        universityComparator = Comparators.getComparator(UniversityComparatorType.BY_SHORT_NAME);
        printListSorted(universities, universityComparator, "Universities by short name:");

        universityComparator = Comparators.getComparator(UniversityComparatorType.BY_YEAR_OF_FOUNDATION);
        printListSorted(universities, universityComparator, "Universities by year of foundation:");

        universityComparator = Comparators.getComparator(UniversityComparatorType.BY_MAIN_PROFILE);
        printListSorted(universities, universityComparator, "Universities by main profile:");


        studentComparator = Comparators.getComparator(StudentComparatorType.BY_FULL_NAME);
        printListSorted(students, studentComparator, "Students by full name:");

        studentComparator = Comparators.getComparator(StudentComparatorType.BY_UNIVERSITY_ID);
        printListSorted(students, studentComparator, "Students by university id:");

        studentComparator = Comparators.getComparator(StudentComparatorType.BY_CURRENT_COURSE_NUMBER);
        printListSorted(students, studentComparator, "Students by current course number:");

        studentComparator = Comparators.getComparator(StudentComparatorType.BY_AVG_EXAM_SCORE_DESC);
        printListSorted(students, studentComparator, "Students by average exam score:");

        LOGGER.info("application stopped");
    }

    private static <T> void printListSorted(List<T> list, Comparator<T> comparator, String heading) {
        String delimiter = "------------------------------";
        System.out.println(heading);
        System.out.println(delimiter);
        list.stream()
                .sorted(comparator)
                .forEach(System.out::println);
        System.out.println();
    }
}
