package dev.akuniutka.skillfactory.lms;

import dev.akuniutka.skillfactory.lms.model.*;
import dev.akuniutka.skillfactory.lms.comparator.*;
import dev.akuniutka.skillfactory.lms.util.Comparators;
import dev.akuniutka.skillfactory.lms.io.XLSXConnector;

import dev.akuniutka.skillfactory.lms.util.JsonUtil;
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

        LOGGER.info("loading data from XLSX file");

        List<University> universities = XLSXConnector.getUniversitiesList(DATA_FILE_NAME);
        List<Student> students = XLSXConnector.getStudentsList(DATA_FILE_NAME);
        UniversityComparator universityComparator;
        StudentComparator studentComparator;

        LOGGER.info("running comparators");

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

        LOGGER.info("running serialization/deserialization");

        System.out.println("\nSerialised data: universities");
        System.out.println("------------------------------");
        String jsonUniversities = JsonUtil.serializeUniversities(universities);
        System.out.println(jsonUniversities);
        System.out.println("\nSerialised data: students");
        System.out.println("------------------------------");
        String jsonStudents = JsonUtil.serializeStudents(students);
        System.out.println(jsonStudents);

        List<University> newUniversities = JsonUtil.deserializeUniversities(jsonUniversities);
        System.out.println("\nChecking deserialization of universities data:");
        System.out.println("# of elements in the original set: " + universities.size());
        System.out.println("# of elements deserialized: " + newUniversities.size());
        System.out.println(universities.size() == newUniversities.size() ? "OK" : "ERROR");

        List<Student> newStudents = JsonUtil.deserializeStudents(jsonStudents);
        System.out.println("\nChecking deserialization of students data:");
        System.out.println("# of elements in the original set: " + students.size());
        System.out.println("# of elements deserialized: " + newStudents.size());
        System.out.println(students.size() == newStudents.size() ? "OK" : "ERROR");

        System.out.println("\nSerializing/deserializing universities data with stream:");
        System.out.println("------------------------------");
        universities.stream()
                .map(JsonUtil::serializeUniversity)
                .peek(System.out::println)
                .map(JsonUtil::deserializeUniversity)
                .forEach(System.out::println);

        System.out.println("\nSerializing/deserializing students data with stream:");
        System.out.println("------------------------------");
        students.stream()
                .map(JsonUtil::serializeStudent)
                .peek(System.out::println)
                .map(JsonUtil::deserializeStudent)
                .forEach(System.out::println);

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
