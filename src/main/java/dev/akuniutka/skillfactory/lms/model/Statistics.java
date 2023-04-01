package dev.akuniutka.skillfactory.lms.model;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private StudyProfile studyProfile;
    private float avgExamScore;
    private int numberOfStudents;
    private int numberOfUniversities;
    private List<String> universityNames = new ArrayList<>();

    public Statistics() {
    }

    public StudyProfile getStudyProfile() {
        return studyProfile;
    }

    public Statistics setStudyProfile(StudyProfile studyProfile) {
        this.studyProfile = studyProfile;
        return this;
    }

    public float getAvgExamScore() {
        return avgExamScore;
    }

    public Statistics setAvgExamScore(float avgExamScore) {
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

    public List<String> getUniversityNames() {
        return universityNames;
    }

    public Statistics setUniversityNames(List<String> universityNames) {
        this.universityNames = universityNames;
        return this;
    }
}
