package project.nathi.tut.ac.za.qualauthapp.model;


import com.google.gson.annotations.SerializedName;

public class Learner {
    private int learnerId;
    private String nationalId;
    private String firstName;
    private String secondName;
    private String lastName;
    private int personId;
    private String captureDate;
    private String contatcNumber;
    private String address;
    private int zipCode;
    private String emailAddress;
    private String gender;
    private String thumbnailUrl;


    @SerializedName("released")
    boolean isReleased;


    public Learner() {

    }


    public Learner(int learnerId, String nationalId, String firstName, String secondName, String lastName, int personId, String captureDate, String contatcNumber, String address, int zipCode, String emailAddress, String gender, String thumbnailUrl) {
        this.learnerId = learnerId;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.personId = personId;
        this.captureDate = captureDate;
        this.contatcNumber = contatcNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(int learnerId) {
        this.learnerId = learnerId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(String captureDate) {
        this.captureDate = captureDate;
    }

    public String getContatcNumber() {
        return contatcNumber;
    }

    public void setContatcNumber(String contatcNumber) {
        this.contatcNumber = contatcNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean released) {
        isReleased = released;
    }
}
