package dev.akuniutka.skillfactory.lms;

import dev.akuniutka.skillfactory.lms.connectors.XLSXConnector;
import dev.akuniutka.skillfactory.lms.models.Student;
import dev.akuniutka.skillfactory.lms.models.University;

import java.util.List;

public class Main {
    private static final String DATA_FILE_NAME = "universityinfo.xlsx";
    public static void main(String[] args) {
        try {
            List<University> universities = XLSXConnector.getUniversitiesList(DATA_FILE_NAME);
            List<Student> students = XLSXConnector.getStudentsList(DATA_FILE_NAME);

            System.out.println("Universities:");
            System.out.println("------------------------------");
            for (University university : universities) {
                System.out.println(university);
            }

            System.out.println("\nStudents:");
            System.out.println("------------------------------");
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (Exception e) {
            System.err.println("File '" + DATA_FILE_NAME + "' has a wrong format!");
        }
    }
}
