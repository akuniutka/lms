package dev.akuniutka.skillfactory.lms.models;

import dev.akuniutka.skillfactory.lms.enums.StudyProfile;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UniversityTest {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();
    private static final int TEST_ITERATIONS = 2;

    @Test
    void whenSetIdTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        Set<String> ids = new HashSet<>();
        while (ids.size() < TEST_ITERATIONS) {
            ids.add(FAKER.idNumber().ssnValid());
        }

        for (String id : ids) {
            assertSame(university, university.setId(id));
        }
    }

    @Test
    void whenSetIdGetIdShouldReturnThatValue() {
        University university = new University();
        Set<String> ids = new HashSet<>();
        while (ids.size() < TEST_ITERATIONS) {
            ids.add(FAKER.idNumber().ssnValid());
        }

        for (String id : ids) {
            university.setId(id);
            assertEquals(id, university.getId());
        }
    }

    @Test
    void whenSetFullNameTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        Set<String> fullNames = new HashSet<>();
        while (fullNames.size() < TEST_ITERATIONS) {
            fullNames.add(FAKER.university().name());
        }

        for (String fullName : fullNames) {
            assertSame(university, university.setFullName(fullName));
        }
    }

    @Test
    void whenSetFullNameGetFullNameShouldReturnThatValue() {
        University university = new University();
        Set<String> fullNames = new HashSet<>();
        while (fullNames.size() < TEST_ITERATIONS) {
            fullNames.add(FAKER.university().name());
        }

        for (String fullName : fullNames) {
            university.setFullName(fullName);
            assertEquals(fullName, university.getFullName());
        }
    }

    @Test
    void whenSetShortNameTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        Set<String> shortNames = new HashSet<>();
        while (shortNames.size() < TEST_ITERATIONS) {
            shortNames.add(FAKER.university().prefix());
        }

        for (String shortName : shortNames) {
            assertSame(university, university.setShortName(shortName));
        }
    }

    @Test
    void whenSetShortNameGetShortNameShouldReturnThatValue() {
        University university = new University();
        Set<String> shortNames = new HashSet<>();
        while (shortNames.size() < TEST_ITERATIONS) {
            shortNames.add(FAKER.university().prefix());
        }

        for (String shortName : shortNames) {
            university.setShortName(shortName);
            assertEquals(shortName, university.getShortName());
        }
    }

    @Test
    void whenSetYearOfFoundationTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        Set<Integer> yearsOfFoundation = new HashSet<>();
        while (yearsOfFoundation.size() < TEST_ITERATIONS) {
            yearsOfFoundation.add(FAKER.number().numberBetween(1800, 2000));
        }

        for (Integer yearOfFoundation : yearsOfFoundation) {
            assertSame(university, university.setYearOfFoundation(yearOfFoundation));
        }
    }

    @Test
    void whenSetYearOfFoundationGetYearOfFoundationShouldReturnThatValue() {
        University university = new University();
        Set<Integer> yearsOfFoundation = new HashSet<>();
        while (yearsOfFoundation.size() < TEST_ITERATIONS) {
            yearsOfFoundation.add(FAKER.number().numberBetween(1800, 2000));
        }

        for (Integer yearOfFoundation : yearsOfFoundation) {
            university.setYearOfFoundation(yearOfFoundation);
            assertEquals(yearOfFoundation, university.getYearOfFoundation());
        }
    }

    @Test
    void whenSetMainProfileTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        StudyProfile[] allProfiles = StudyProfile.values();
        Set<StudyProfile> testProfiles = new HashSet<>();
        while (testProfiles.size() < Math.min(TEST_ITERATIONS, allProfiles.length)) {
            testProfiles.add(allProfiles[RANDOM.nextInt(allProfiles.length + 1)]);
        }

        for (StudyProfile studyProfile : testProfiles) {
            assertSame(university, university.setMainProfile(studyProfile));
        }
    }

    @Test
    void whenSetMainProfileGetMainProfileShouldReturnThatValue() {
        University university = new University();
        StudyProfile[] allProfiles = StudyProfile.values();
        Set<StudyProfile> testProfiles = new HashSet<>();
        while (testProfiles.size() < Math.min(TEST_ITERATIONS, allProfiles.length)) {
            testProfiles.add(allProfiles[RANDOM.nextInt(allProfiles.length)]);
        }

        for (StudyProfile studyProfile : testProfiles) {
            university.setMainProfile(studyProfile);
            assertSame(studyProfile, university.getMainProfile());
        }
    }

    @Test
    void toStringShouldReturnIdFullNameShortNameYearOfFoundationMainProfile() {
        University university = new University();
        String id;
        String fullName;
        String shortName;
        int yearOfFoundation;
        StudyProfile mainProfile;
        StudyProfile[] allProfiles = StudyProfile.values();
        Set<String> usedIds = new HashSet<>();
        Set<String> usedFullNames = new HashSet<>();
        Set<String> usedShortNames = new HashSet<>();
        Set<Integer> usedYearsOfFoundation = new HashSet<>();
        Set<StudyProfile> usedMainProfiles = new HashSet<>();

        for (int i = 0; i < TEST_ITERATIONS; i++) {
            do {
                id = FAKER.idNumber().ssnValid();
            } while (!usedIds.add(id));
            do {
                fullName = FAKER.university().name();
            } while (!usedFullNames.add(fullName));
            do {
                shortName = FAKER.university().prefix();
            } while (!usedShortNames.add(shortName));
            do {
                yearOfFoundation = FAKER.number().numberBetween(1800, 2000);
            } while (!usedYearsOfFoundation.add(yearOfFoundation));
            do {
                mainProfile = allProfiles[RANDOM.nextInt(allProfiles.length)];
            } while (!usedMainProfiles.add(mainProfile)
                    && usedMainProfiles.size() < allProfiles.length
            );

            String expected = "University{" +
                    "id='" + id + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", shortName='" + shortName + '\'' +
                    ", yearOfFoundation=" + yearOfFoundation +
                    ", mainProfile=" + mainProfile.getProfileName() +
                    '}';
            String actual = university.setId(id)
                    .setFullName(fullName)
                    .setShortName(shortName)
                    .setYearOfFoundation(yearOfFoundation)
                    .setMainProfile(mainProfile)
                    .toString();
            assertEquals(expected, actual);
        }
    }
}