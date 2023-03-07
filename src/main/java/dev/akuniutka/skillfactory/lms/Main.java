package dev.akuniutka.skillfactory.lms;

import dev.akuniutka.skillfactory.lms.enums.StudyProfile;
import dev.akuniutka.skillfactory.lms.models.Student;
import dev.akuniutka.skillfactory.lms.models.University;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<University> universities = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        universities.add( new University()
                .setId("high-0001")
                .setFullName("Московский государственный университет имени М.В.Ломоносова")
                .setShortName("МГУ")
                .setYearOfFoundation(1855)
                .setMainProfile(StudyProfile.MATHEMATICS)
        );
        universities.add( new University()
                .setId("high-0002")
                .setFullName("Национальный исследовательский университет \"Высшая школа экономики\"")
                .setShortName("НИУ ВШЭ")
                .setYearOfFoundation(1992)
                .setMainProfile(StudyProfile.ECONOMICS)
        );
        universities.add( new University()
                .setId("high-0003")
                .setFullName("Московский государственный технический университет имени Н. Э. Баумана")
                .setShortName("МГТУ им. Н.Э. Баумана")
                .setYearOfFoundation(1830)
                .setMainProfile(StudyProfile.PHYSICS)
        );
        students.add(new Student()
                .setFullName("Иванов Иван Иванович")
                .setUniversityId(universities.get(0).getId())
                .setCurrentCourseNumber(3)
                .setAvgExamScore(4.67f)
        );
        students.add(new Student()
                .setFullName("Антонова Антонина Антоновна")
                .setUniversityId(universities.get(0).getId())
                .setCurrentCourseNumber(2)
                .setAvgExamScore(4.76f)
        );
        students.add(new Student()
                .setFullName("Петров Петр Петрович")
                .setUniversityId(universities.get(1).getId())
                .setCurrentCourseNumber(1)
                .setAvgExamScore(4.8f)
        );
        students.add(new Student()
                .setFullName("Сергеева Светлана Сергеевна")
                .setUniversityId(universities.get(1).getId())
                .setCurrentCourseNumber(4)
                .setAvgExamScore(4.5f)
        );
        students.add(new Student()
                .setFullName("Григорян Григорий Григорьевич")
                .setUniversityId(universities.get(2).getId())
                .setCurrentCourseNumber(3)
                .setAvgExamScore(4.92f)
        );
        students.add(new Student()
                .setFullName("Викторова Виктория Викторовна")
                .setUniversityId(universities.get(2).getId())
                .setCurrentCourseNumber(2)
                .setAvgExamScore(4.95f)
        );

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
    }
}
