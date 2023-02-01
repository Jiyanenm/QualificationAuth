package project.nathi.tut.ac.za.qualauthapp.model;

public class User implements java.io.Serializable{

    private Long id;
    private String fullName;
    private String email;
    private String contactNumber;
    private String address;
    private String password;
    private int active;



    public User() {
        super();
    }

    public User(String fullName, String email, String contactNumber, String address,
                String password, int aActive) {
        super();
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
        //this.zipCode = zipCode;
        this.password = password;
        this.active = active;
    }



    public User(Long id, String fullName, String email, String contactNumber, String address, String password,
                int active) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
        this.password = password;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
