package dev.akuniutka.skillfactory.lms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<University> universities = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        universities.add( new University()
                .setFullName("Московский государственный университет имени М.В.Ломоносова")
                .setShortName("МГУ")
                .setYearOfFoundation(1855)
                .setMainProfile(StudyProfile.MATHEMATICS)
                .setAddress("119991, г. Москва, Ленинские горы, д. 1")
                .setUrl("https://www.msu.ru/")
                .setEmail("info@rector.msu.ru")
                .setPhone("+7 (495) 939-10-00")
        );
        universities.add( new University()
                .setFullName("Национальный исследовательский университет \"Высшая школа экономики\"")
                .setShortName("НИУ ВШЭ")
                .setYearOfFoundation(1992)
                .setMainProfile(StudyProfile.ECONOMICS)
                .setAddress("109028, г. Москва, Покровский бульвар, д. 11")
                .setUrl("https://www.hse.ru/")
                .setEmail("hse@hse.ru")
                .setPhone("+7 (495) 771-32-32")
        );
        universities.add( new University()
                .setFullName("Московский государственный технический университет имени Н. Э. Баумана")
                .setShortName("МГТУ им. Н.Э. Баумана")
                .setYearOfFoundation(1830)
                .setMainProfile(StudyProfile.PHYSICS)
                .setAddress("105005, г. Москва, 2-я Бауманская ул., д. 5, стр. 1")
                .setUrl("https://bmstu.ru/")
                .setEmail("bauman@bmstu.ru")
                .setPhone("+7 (499) 263-63-91")
        );
        students.add(new Student()
                .setFullName("Иванов Иван Иванович")
                .setDateOfBirth(LocalDate.of(2002, 1,25))
                .setUniversityId(universities.get(0).getId())
                .setCurrentCourseNumber(3)
                .setAvgExamScore(4.67f)
                .setEmail("ivanov@mail.ru")
                .setPhone("+7 (901) 123-45-67")
        );
        students.add(new Student()
                .setFullName("Антонова Антонина Антоновна")
                .setDateOfBirth(LocalDate.of(2003, 6, 6))
                .setUniversityId(universities.get(0).getId())
                .setCurrentCourseNumber(2)
                .setAvgExamScore(4.76f)
                .setEmail("antonina@mail.ru")
                .setPhone("+7 (902) 987-65-43")
        );
        students.add(new Student()
                .setFullName("Петров Петр Петрович")
                .setDateOfBirth(LocalDate.of(2004,9,9))
                .setUniversityId(universities.get(1).getId())
                .setCurrentCourseNumber(1)
                .setAvgExamScore(4.8f)
                .setEmail("petrov@mail.ru")
                .setPhone("+7 (903) 444-55-66")
        );
        students.add(new Student()
                .setFullName("Сергеева Светлана Сергеевна")
                .setDateOfBirth(LocalDate.of(2001,12,25))
                .setUniversityId(universities.get(1).getId())
                .setCurrentCourseNumber(4)
                .setAvgExamScore(4.5f)
                .setEmail("svetik@mail.ru")
                .setPhone("+7 (904) 111-22-33")
        );
        students.add(new Student()
                .setFullName("Григорян Григорий Григорьевич")
                .setDateOfBirth(LocalDate.of(2002,8,15))
                .setUniversityId(universities.get(2).getId())
                .setCurrentCourseNumber(3)
                .setAvgExamScore(4.92f)
                .setEmail("grigoryan@mail.ru")
                .setPhone("+7 (905) 555-55-55")
        );
        students.add(new Student()
                .setFullName("Викторова Виктория Викторовна")
                .setDateOfBirth(LocalDate.of(2003,6,12))
                .setUniversityId(universities.get(2).getId())
                .setCurrentCourseNumber(2)
                .setAvgExamScore(4.95f)
                .setEmail("vika@mail.ru")
                .setPhone("+7 (906) 654-32-10")
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
