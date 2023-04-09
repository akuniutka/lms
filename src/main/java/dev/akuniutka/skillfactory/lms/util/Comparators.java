package dev.akuniutka.skillfactory.lms.util;

import dev.akuniutka.skillfactory.lms.comparator.*;

public class Comparators {
    private Comparators() {}

    public static StudentComparator getComparator(StudentComparatorType comparatorType) {
        switch (comparatorType) {
            case BY_FULL_NAME:
                return new StudentFullNameComparator();
            case BY_UNIVERSITY_ID:
                return new StudentUniversityIdComparator();
            case BY_CURRENT_COURSE_NUMBER:
                return new StudentCurrentCourseNumberComparator();
            case BY_AVG_EXAM_SCORE_DESC:
                return new StudentAvgExamScoreComparator();
        }
        throw new IllegalArgumentException("illegal comparator type passed");
    }

    public static UniversityComparator getComparator(UniversityComparatorType comparatorType) {
        switch (comparatorType) {
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
        throw new IllegalArgumentException("illegal comparator type passed");
    }
}
