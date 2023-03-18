package dev.akuniutka.skillfactory.lms.comparators;

import dev.akuniutka.skillfactory.lms.enums.StudentComparators;
import dev.akuniutka.skillfactory.lms.enums.UniversityComparators;

public class Comparators {
    private Comparators() {
    }

    public static StudentComparator getComparator(StudentComparators comparatorId) {
        switch (comparatorId) {
            case BY_FULL_NAME:
                return new StudentFullNameComparator();
            case BY_UNIVERSITY_ID:
                return new StudentUniversityIdComparator();
            case BY_CURRENT_COURSE_NUMBER:
                return new StudentCurrentCourseNumberComparator();
            case BY_AVG_EXAM_SCORE_DESC:
                return new StudentAvgExamScoreComparator();
        }
        throw new IllegalArgumentException("illegal comparator id passed");
    }

    public static UniversityComparator getComparator(UniversityComparators comparatorId) {
        switch (comparatorId) {
            case BY_ID:
                return new UniversityIdComparator();
            case BY_FULL_NAME:
                return new UniversityFullNameComparator();
            case BY_SHORT_NAME:
                return new UniversityShortNameComparator();
            case BY_YEAR_OF_FOUNDATION:
                return new UniversityYearOfFoundationComparator();
            case BY_MAIN_PROFILE:
                return new UniversityMainProfileComparator();
        }
        throw new IllegalArgumentException("illegal comparator id passed");
    }
}
