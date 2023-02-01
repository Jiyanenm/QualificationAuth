package project.nathi.tut.ac.za.qualauthapp.model;

import com.google.gson.annotations.SerializedName;

public class Qualification {

    private int qualificationId;
    private String qualificationName;
    private String providerName;
    private String providerReference;
    private String status;
    private String enrolmentDate;
    private String achievedDate;
    private String courseName;

    @SerializedName("released")
    boolean isReleased;


    public Qualification() {
    }

    public Qualification(int qualificationId, String qualificationName, String providerName, String providerReference, String status, String enrolmentDate, String achievedDate, String courseName) {
        this.qualificationId = qualificationId;
        this.qualificationName = qualificationName;
        this.providerName = providerName;
        this.providerReference = providerReference;
        this.status = status;
        this.enrolmentDate = enrolmentDate;
        this.achievedDate = achievedDate;
        this.courseName = courseName;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderReference() {
        return providerReference;
    }

    public void setProviderReference(String providerReference) {
        this.providerReference = providerReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnrolmentDate() {
        return enrolmentDate;
    }

    public void setEnrolmentDate(String enrolmentDate) {
        this.enrolmentDate = enrolmentDate;
    }

    public String getAchievedDate() {
        return achievedDate;
    }

    public void setAchievedDate(String achievedDate) {
        this.achievedDate = achievedDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean released) {
        isReleased = released;
    }
}
