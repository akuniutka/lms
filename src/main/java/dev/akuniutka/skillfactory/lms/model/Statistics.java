package dev.akuniutka.skillfactory.lms.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class Statistics {
    @XmlElement(name = "universityProfile")
    private StudyProfile studyProfile;
    @XmlElement(name = "avgScore")
    private double avgExamScore;
    @XmlTransient
    private long numberOfStudents;
    @XmlTransient
    private long numberOfUniversities;
    @XmlTransient
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

    public double getAvgExamScore() {
        return avgExamScore;
    }

    public Statistics setAvgExamScore(double avgExamScore) {
        this.avgExamScore = avgExamScore;
        return this;
    }

    public long getNumberOfStudents() {
        return numberOfStudents;
    }

    public Statistics setNumberOfStudents(long numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
        return this;
    }

    public long getNumberOfUniversities() {
        return numberOfUniversities;
    }

    public Statistics setNumberOfUniversities(long numberOfUniversities) {
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
                ", avgExamScore=" + avgExamScore +
                ", numberOfStudents=" + numberOfStudents +
                ", numberOfUniversities=" + numberOfUniversities +
                ", universityNames='" + universityNames + '\'' +
                '}';
    }
}
