package dev.akuniutka.skillfactory.lms.model;

import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TestData {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();
    private final static StudyProfile[] STUDY_PROFILES = StudyProfile.values();
    private final Set<String> usedUniversityIds = new HashSet<>();
    private final Set<String> usedUniversityFullNames = new HashSet<>();
    private final Set<String> usedUniversityShortNames = new HashSet<>();
    private final Set<Integer> usedUniversityYearsOfFoundation = new HashSet<>();
    private final Set<StudyProfile> usedUniversityMainProfiles = new HashSet<>();
    private final Set<String> usedStudentFullNames = new HashSet<>();
    private final Set<Integer> usedStudentCurrentCourseNumbers = new HashSet<>();
    private final Set<Float> usedStudentAvgExamScores = new HashSet<>();
    private final Set<Long> usedNumbersOfStudents = new HashSet<>();
    private final Set<Long> usedNumbersOfUniversities = new HashSet<>();

    public void reset() {
        usedUniversityIds.clear();
        usedUniversityFullNames.clear();
        usedUniversityShortNames.clear();
        usedUniversityYearsOfFoundation.clear();
        usedUniversityMainProfiles.clear();
        usedStudentFullNames.clear();
        usedStudentCurrentCourseNumbers.clear();
        usedStudentAvgExamScores.clear();
        usedNumbersOfStudents.clear();
        usedNumbersOfUniversities.clear();
    }

    public long getNextNumberOfStudents() {
        long numberOfStudents;
        do {
            numberOfStudents = RANDOM.nextLong(0, 50);
        } while (!usedNumbersOfStudents.add(numberOfStudents)
                && usedNumbersOfStudents.size() < 49
        );
        return numberOfStudents;
    }

    public long getNextNumberOfUniversities() {
        long numberOfUniversities;
        do {
            numberOfUniversities = RANDOM.nextLong(1, 50);
        } while (!usedNumbersOfUniversities.add(numberOfUniversities)
                && usedNumbersOfUniversities.size() < 48
        );
        return numberOfUniversities;
    }

    public String getNextUniversityID() {
        String universityId;
        do {
            universityId = FAKER.idNumber().ssnValid();
        } while (!usedUniversityIds.add(universityId));
        return universityId;
    }

    public String getNextUniversityFullName() {
        String universityFullName;
        do {
            universityFullName = FAKER.university().name();
        } while (universityFullName.contains("'")
                || !usedUniversityFullNames.add(universityFullName)
        );
        return universityFullName;
    }

    public String getNextUniversityShortName() {
        String universityShortName;
        do {
            universityShortName = FAKER.university().prefix();
        } while (!usedUniversityShortNames.add(universityShortName));
        return universityShortName;
    }

    public int getNextUniversityYearOfFoundation() {
        int universityYearOfFoundation;
        do {
            universityYearOfFoundation = FAKER.number().numberBetween(1800, 2000);
        } while (!usedUniversityYearsOfFoundation.add(universityYearOfFoundation));
        return universityYearOfFoundation;
    }

    public StudyProfile getNextUniversityMainProfile() {
        StudyProfile universityMainProfile;
        do {
            universityMainProfile = STUDY_PROFILES[RANDOM.nextInt(STUDY_PROFILES.length)];
        } while (!usedUniversityMainProfiles.add(universityMainProfile)
                && usedUniversityMainProfiles.size() < STUDY_PROFILES.length
        );
        return universityMainProfile;
    }

    public String getNextStudentFullName() {
        String studentFullName;
        do {
            studentFullName = FAKER.name().fullName();
        } while (studentFullName.contains("'")
                || !usedStudentFullNames.add(studentFullName));
        return studentFullName;
    }

    public int getNextStudentCurrentCourseNumber() {
        int studentCurrentCourseNumber;
        do {
            studentCurrentCourseNumber = FAKER.number().numberBetween(1, 5);
        } while (!usedStudentCurrentCourseNumbers.add(studentCurrentCourseNumber)
                && usedStudentCurrentCourseNumbers.size() < 4
        );
        return studentCurrentCourseNumber;
    }

    public float getNextStudentAvgExamScore() {
        float studentAvgExamScore;
        do {
            studentAvgExamScore = BigDecimal.valueOf(5 - RANDOM.nextDouble() * 3)
                    .setScale(2, RoundingMode.HALF_UP)
                    .floatValue();
        } while (!usedStudentAvgExamScores.add(studentAvgExamScore)
                && usedStudentAvgExamScores.size() < 300
        );
        return studentAvgExamScore;
    }

    public Student createRandomStudent() {
        return new Student()
                .setFullName(getNextStudentFullName())
                .setUniversityId(getNextUniversityID())
                .setCurrentCourseNumber(getNextStudentCurrentCourseNumber())
                .setAvgExamScore(getNextStudentAvgExamScore());
    }

    public Student createRandomStudent(String universityId) {
        return new Student()
                .setFullName(getNextStudentFullName())
                .setUniversityId(universityId)
                .setCurrentCourseNumber(getNextStudentCurrentCourseNumber())
                .setAvgExamScore(getNextStudentAvgExamScore());
    }

    public University createRandomUniversity() {
        return new University()
                .setId(getNextUniversityID())
                .setFullName(getNextUniversityFullName())
                .setShortName(getNextUniversityShortName())
                .setYearOfFoundation(getNextUniversityYearOfFoundation())
                .setMainProfile(getNextUniversityMainProfile());
    }

    public University createRandomUniversity(StudyProfile mainProfile) {
        return new University()
                .setId(getNextUniversityID())
                .setFullName(getNextUniversityFullName())
                .setShortName(getNextUniversityShortName())
                .setYearOfFoundation(getNextUniversityYearOfFoundation())
                .setMainProfile(mainProfile);
    }

    public Statistics createRandomStatistics() {
        long numberOfUniversities = getNextNumberOfUniversities();
        List<String> universityNames = new ArrayList<>();
        for (long i = 0; i < numberOfUniversities; i++) {
            universityNames.add(getNextUniversityFullName());
        }
        if (RANDOM.nextInt(2) == 1) {
            return new Statistics()
                    .setStudyProfile(getNextUniversityMainProfile())
                    .setAvgExamScore(getNextStudentAvgExamScore())
                    .setNumberOfStudents(getNextNumberOfStudents())
                    .setNumberOfUniversities(numberOfUniversities)
                    .setUniversityNames(String.join(";", universityNames));
        } else {
            return new Statistics()
                    .setStudyProfile(getNextUniversityMainProfile())
                    .setNumberOfStudents(getNextNumberOfStudents())
                    .setNumberOfUniversities(numberOfUniversities)
                    .setUniversityNames(String.join(";", universityNames));
        }
    }
}
