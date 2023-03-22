package dev.akuniutka.skillfactory.lms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversityTest {
    private static final int TEST_ITERATIONS = 2;
    private final TestData testData = new TestData();

    @BeforeEach
    void resetTestData() {
        testData.reset();
    }

    @Test
    void whenSetIdTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String id = testData.getNextUniversityID();
            assertSame(university, university.setId(id));
        }
    }

    @Test
    void whenSetIdGetIdShouldReturnThatValue() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String id = testData.getNextUniversityID();
            university.setId(id);
            assertEquals(id, university.getId());
        }
    }

    @Test
    void whenSetFullNameTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String fullName = testData.getNextUniversityFullName();
            assertSame(university, university.setFullName(fullName));
        }
    }

    @Test
    void whenSetFullNameGetFullNameShouldReturnThatValue() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String fullName = testData.getNextUniversityFullName();
            university.setFullName(fullName);
            assertEquals(fullName, university.getFullName());
        }
    }

    @Test
    void whenSetShortNameTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String shortName = testData.getNextUniversityShortName();
            assertSame(university, university.setShortName(shortName));
        }
    }

    @Test
    void whenSetShortNameGetShortNameShouldReturnThatValue() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String shortName = testData.getNextUniversityShortName();
            university.setShortName(shortName);
            assertEquals(shortName, university.getShortName());
        }
    }

    @Test
    void whenSetYearOfFoundationTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            int yearOfFoundation = testData.getNextUniversityYearOfFoundation();
            assertSame(university, university.setYearOfFoundation(yearOfFoundation));
        }
    }

    @Test
    void whenSetYearOfFoundationGetYearOfFoundationShouldReturnThatValue() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            int yearOfFoundation = testData.getNextUniversityYearOfFoundation();
            university.setYearOfFoundation(yearOfFoundation);
            assertEquals(yearOfFoundation, university.getYearOfFoundation());
        }
    }

    @Test
    void whenSetMainProfileTheSameUniversityObjectShouldBeReturned() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            StudyProfile studyProfile = testData.getNextUniversityMainProfile();
            assertSame(university, university.setMainProfile(studyProfile));
        }
    }

    @Test
    void whenSetMainProfileGetMainProfileShouldReturnThatValue() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            StudyProfile studyProfile = testData.getNextUniversityMainProfile();
            university.setMainProfile(studyProfile);
            assertSame(studyProfile, university.getMainProfile());
        }
    }

    @Test
    void toStringShouldReturnIdFullNameShortNameYearOfFoundationMainProfile() {
        University university = new University();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            String id = testData.getNextUniversityID();
            String fullName = testData.getNextUniversityFullName();
            String shortName = testData.getNextUniversityShortName();
            int yearOfFoundation = testData.getNextUniversityYearOfFoundation();
            StudyProfile mainProfile = testData.getNextUniversityMainProfile();
            String expected = "University{" +
                    "id='" + id + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", shortName='" + shortName + '\'' +
                    ", yearOfFoundation=" + yearOfFoundation +
                    ", mainProfile='" + mainProfile.getProfileName() + '\'' +
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