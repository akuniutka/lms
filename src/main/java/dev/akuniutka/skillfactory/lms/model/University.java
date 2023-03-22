package dev.akuniutka.skillfactory.lms.model;

import com.google.gson.annotations.SerializedName;

public class University {
    @SerializedName("universityId")
    private String id;
    @SerializedName("universityFullName")
    private String fullName;
    @SerializedName("universityShortName")
    private String shortName;
    @SerializedName("universityYearOfFoundation")
    private int yearOfFoundation;
    @SerializedName("universityMainProfile")
    private StudyProfile mainProfile;


    public University() {
    }


    public String getId() {
        return id;
    }

    public University setId(String id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public University setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public University setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public University setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
        return this;
    }

    public StudyProfile getMainProfile() {
        return mainProfile;
    }

    public University setMainProfile(StudyProfile mainProfile) {
        this.mainProfile = mainProfile;
        return this;
    }


    @Override
    public String toString() {
        return "University{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", yearOfFoundation=" + yearOfFoundation +
                ", mainProfile='" + mainProfile.getProfileName() + '\'' +
                '}';
    }
}
