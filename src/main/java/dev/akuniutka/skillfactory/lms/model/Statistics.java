package dev.akuniutka.skillfactory.lms.model;

import java.util.OptionalDouble;

public class Statistics {
    private StudyProfile studyProfile;
    private double avgExamScore = Double.NaN;
    private int numberOfStudents;
    private int numberOfUniversities;
    private String universityNames;

    public Statistics() {
    }

    public StudyProfile getStudyProfile() {
        return studyProfile;
    }

    public Statistics setStudyProfile(StudyProfile studyProfile) {
        this.studyProfile = studyProfile;
        return this;
    }

    public OptionalDouble getAvgExamScore() {
        if (Double.isNaN(avgExamScore)) {
            return OptionalDouble.empty();
        } else {
            return OptionalDouble.of(avgExamScore);
        }
    }

    public Statistics setAvgExamScore(double avgExamScore) {
        this.avgExamScore = avgExamScore;
        return this;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public Statistics setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
        return this;
    }

    public int getNumberOfUniversities() {
        return numberOfUniversities;
    }

    public Statistics setNumberOfUniversities(int numberOfUniversities) {
        this.numberOfUniversities = numberOfUniversities;
        return this;
    }

    public String getUniversityNames() {
        return universityNames;
    }

    public Statistics setUniversityNames(String universityNames) {
        this.universityNames = universityNames;
        return this;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "studyProfile='" + studyProfile.getProfileName() + '\'' +
                ", avgExamScore=" + (getAvgExamScore().isPresent() ? avgExamScore : "null") +
                ", numberOfStudents=" + numberOfStudents +
                ", numberOfUniversities=" + numberOfUniversities +
                ", universityNames='" + universityNames + '\'' +
                '}';
    }
}
