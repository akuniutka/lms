package dev.akuniutka.skillfactory.lms.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"fullName", "universityId", "avgExamScore"})
public class Student {
    @SerializedName("studentFullName")
    @XmlElement(name = "studentName")
    private String fullName;
    @SerializedName("studentUniversityId")
    private String universityId;
    @SerializedName("studentCurrentCourseNumber")
    @XmlTransient
    private int currentCourseNumber;
    @SerializedName("studentAvgExamScore")
    @XmlElement(name = "avgScore")
    private float avgExamScore;


    public Student() {
    }


    public String getFullName() {
        return fullName;
    }

    public Student setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUniversityId() {
        return universityId;
    }

    public Student setUniversityId(String universityId) {
        this.universityId = universityId;
        return this;
    }

    public int getCurrentCourseNumber() {
        return currentCourseNumber;
    }

    public Student setCurrentCourseNumber(int currentCourseNumber) {
        this.currentCourseNumber = currentCourseNumber;
        return this;
    }

    public float getAvgExamScore() {
        return avgExamScore;
    }

    public Student setAvgExamScore(float avgExamScore) {
        this.avgExamScore = avgExamScore;
        return this;
    }


    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", universityId='" + universityId + '\'' +
                ", currentCourseNumber=" + currentCourseNumber +
                ", avgExamScore=" + avgExamScore +
                '}';
    }
}
