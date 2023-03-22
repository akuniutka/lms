package dev.akuniutka.skillfactory.lms.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StudyProfileTest {
    private static final String SRC_PATH = "/studyprofiles.txt";
    private static List<TestStudyProfile> testStudyProfiles = null;

    static class TestStudyProfile {
        final String value;
        final String profileCode;
        final String profileName;

        TestStudyProfile(String initString) {
            String[] strings = initString.split(";");
            if (strings.length != 3) {
                throw new IllegalArgumentException("initString must contain three strings, separated by ';'");
            }
            value = strings[0];
            profileCode = strings[1];
            profileName = strings[2];
        }
    }

    @BeforeAll
    static void loadProfiles() {
        List<TestStudyProfile> tempTestStudyProfiles = new ArrayList<>();
        InputStream is = StudyProfileTest.class.getResourceAsStream(SRC_PATH);
        if (is == null) {
            fail("cannot read test data");
            return;
        }
        try (Scanner in = new Scanner(is)) {
            while (in.hasNextLine()) {
                tempTestStudyProfiles.add(new TestStudyProfile(in.nextLine()));
            }
        }
        testStudyProfiles = tempTestStudyProfiles;
    }

    @Test
    void enumShouldContainFixedSetOfValues() {
        assertEquals(testStudyProfiles.size(), StudyProfile.values().length);
        for (TestStudyProfile testStudyProfile : testStudyProfiles) {
            assertNotNull(StudyProfile.valueOf(testStudyProfile.value));
        }
    }

    @Test
    void valueOfCodeShouldReturnCorrectStudyProfileObject() {
        for (TestStudyProfile testStudyProfile : testStudyProfiles) {
            assertSame(StudyProfile.valueOf(testStudyProfile.value),
                    StudyProfile.valueOfCode(testStudyProfile.profileCode));
        }
    }

    @Test
    void valueOfNameShouldReturnCorrectStudyProfileObject() {
        for (TestStudyProfile testStudyProfile : testStudyProfiles) {
            assertSame(StudyProfile.valueOf(testStudyProfile.value),
                    StudyProfile.valueOfName(testStudyProfile.profileName)
            );
        }
    }

    @Test
    void getProfileCodeShouldReturnCorrectProfileCode() {
        for (TestStudyProfile testStudyProfile : testStudyProfiles) {
            assertEquals(testStudyProfile.profileCode,
                    StudyProfile.valueOf(testStudyProfile.value).getProfileCode()
            );
        }
    }

    @Test
    void getProfileNameShouldReturnCorrectProfileName() {
        for (TestStudyProfile testStudyProfile : testStudyProfiles) {
            assertEquals(testStudyProfile.profileName,
                    StudyProfile.valueOf(testStudyProfile.value).getProfileName()
            );
        }
    }
}