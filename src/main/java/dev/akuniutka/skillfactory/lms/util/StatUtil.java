package dev.akuniutka.skillfactory.lms.util;

import dev.akuniutka.skillfactory.lms.model.Statistics;
import dev.akuniutka.skillfactory.lms.model.Student;
import dev.akuniutka.skillfactory.lms.model.StudyProfile;
import dev.akuniutka.skillfactory.lms.model.University;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class StatUtil {
    private StatUtil() {}

    public static List<Statistics> getStatistics(List<University> universities, List<Student> students) {
        return Arrays.stream(StudyProfile.values())
                .map(studyProfile -> new Statistics().setStudyProfile(studyProfile))
                .map(statistics ->
                        statistics.setNumberOfUniversities(
                                universities.stream()
                                        .filter(university -> university.getMainProfile() == statistics.getStudyProfile())
                                        .count()
                        )
                )
                .filter(statistics -> statistics.getNumberOfUniversities() != 0)
                .peek(statistics ->
                        statistics.setUniversityNames(
                                universities.stream()
                                        .filter(university -> university.getMainProfile() == statistics.getStudyProfile())
                                        .map(University::getFullName)
                                        .collect(Collectors.joining(";"))
                        )
                )
                .peek(statistics ->
                        statistics.setNumberOfStudents(
                                students.stream()
                                        .filter(student ->
                                                universities.stream()
                                                        .anyMatch(u ->
                                                                u.getId().equals(student.getUniversityId())
                                                                && u.getMainProfile() == statistics.getStudyProfile()
                                                        )
                                        )
                                        .count()
                        )
                )
                .peek(statistics ->
                        students.stream()
                                .filter(student ->
                                        universities.stream()
                                        .anyMatch(u ->
                                                u.getId().equals(student.getUniversityId())
                                                && u.getMainProfile() == statistics.getStudyProfile()
                                        )
                                )
                                .flatMapToDouble(student -> DoubleStream.of(student.getAvgExamScore()))
                                .average()
                                .ifPresent(d ->
                                        statistics.setAvgExamScore(
                                                BigDecimal.valueOf(d)
                                                        .setScale(2, RoundingMode.HALF_UP)
                                                        .doubleValue()
                                        )
                                )
                )
                .collect(Collectors.toList());
    }
}
