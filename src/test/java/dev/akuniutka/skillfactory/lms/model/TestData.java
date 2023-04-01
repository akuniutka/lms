package dev.akuniutka.skillfactory.lms.model;

import com.github.javafaker.Faker;

import java.util.*;

public class TestData {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();
    private final static StudyProfile[] STUDY_PROFILES = StudyProfile.values();
    private final Set<String> usedUniverityIds = new HashSet<>();
    private final Set<String> usedUniversityFullNames = new HashSet<>();
    private final Set<String> usedUniversityShortNames = new HashSet<>();
    private final Set<Integer> usedUniversityYearsOfFoundation = new HashSet<>();
    private final Set<StudyProfile> usedUniversityMainProfiles = new HashSet<>();
    private final Set<String> usedStudentFullNames = new HashSet<>();
    private final Set<Integer> usedStudentCurrentCourseNumbers = new HashSet<>();
    private final Set<Float> usedStudentAvgExamScores = new HashSet<>();
    private final Set<Integer> usedNumbersOfStudents = new HashSet<>();
    private final Set<Integer> usedNumbersOfUniversities = new HashSet<>();

    public void reset() {
        usedUniverityIds.clear();
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

    public Integer getNextNumberOfStudents() {
        int numberOfStudents;
        do {
            numberOfStudents = RANDOM.nextInt(0, 50);
        } while (!usedNumbersOfStudents.add(numberOfStudents)
                && usedNumbersOfStudents.size() < 49
        );
        return numberOfStudents;
    }

    public Integer getNextNumberOfUniversities() {
        int numberOfUniversities;
        do {
            numberOfUniversities = RANDOM.nextInt(1, 50);
        } while (!usedNumbersOfUniversities.add(numberOfUniversities)
                && usedNumbersOfUniversities.size() < 48
        );
        return numberOfUniversities;
    }

    public String getNextUniversityID() {
        String universityId;
        do {
            universityId = FAKER.idNumber().ssnValid();
        } while (!usedUniverityIds.add(universityId));
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
            studentAvgExamScore = 5 - RANDOM.nextFloat() * 3;
        } while (!usedStudentAvgExamScores.add(studentAvgExamScore));
        return studentAvgExamScore;
    }

    public Student createRandomStudent() {
        return new Student()
                .setFullName(getNextStudentFullName())
                .setUniversityId(getNextUniversityID())
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

    public Statistics createRandomStatistics() {
        int numberOfUniversities = getNextNumberOfUniversities();
        List<String> universityNames = new ArrayList<>();
        for (int i = 0; i < numberOfUniversities; i++) {
            universityNames.add(getNextUniversityFullName());
        }
        return new Statistics()
                .setStudyProfile(getNextUniversityMainProfile())
                .setAvgExamScore(getNextStudentAvgExamScore())
                .setNumberOfStudents(getNextNumberOfStudents())
                .setNumberOfUniversities(numberOfUniversities)
                .setUniversityNames(universityNames);
    }
}
