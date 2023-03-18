package dev.akuniutka.skillfactory.lms.enums;

import java.util.HashMap;
import java.util.Map;

public enum StudyProfile {
    MATHEMATICS("01.03.01", "Математика"),
    APPLIED_MATHEMATICS("01.03.04", "Прикладная математика"),
    PHYSICS("03.03.02", "Физика"),
    CHEMISTRY("04.03.01", "Химия"),
    GEOLOGY("05.03.01", "Геология"),
    GEOGRAPHY("05.03.02", "География"),
    BIOLOGY("06.03.01", "Биология"),
    APPLIED_MECHANICS("15.03.03", "Прикладная механика"),
    PSYCHOLOGY("37.03.01", "Психология"),
    ECONOMICS("38.03.01", "Экономика"),
    MEDICINE("31.00.00", "Клиническая медицина"),
    LINGUISTICS("45.03.02", "Лингвистика");

    private final String profileCode;
    private final String profileName;
    private static final Map<String, StudyProfile> BY_NAME = new HashMap<>();
    private static final Map<String, StudyProfile> BY_CODE = new HashMap<>();

    static {
        for (StudyProfile profile : values()) {
            BY_NAME.put(profile.profileName, profile);
            BY_CODE.put(profile.profileCode, profile);
        }
    }

    StudyProfile(String profileCode, String profileName) {
        this.profileCode = profileCode;
        this.profileName = profileName;
    }

    public static StudyProfile valueOfName(String profileName) {
        return BY_NAME.get(profileName);
    }

    public static StudyProfile valueOfCode(String profileCode) {
        return BY_CODE.get(profileCode);
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileCode() {
        return profileCode;
    }
}
