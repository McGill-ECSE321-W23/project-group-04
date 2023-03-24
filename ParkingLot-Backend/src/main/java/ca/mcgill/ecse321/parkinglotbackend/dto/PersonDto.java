package ca.mcgill.ecse321.parkinglotbackend.dto;

public class PersonDto {
    
    // Attributes
    private long personID;
    private String phoneNumber;
    private String name;

    // Constructors
    public PersonDto() {
    }

    public PersonDto(long personID, String phoneNumber, String name) {
        this.personID = personID;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    // Getters and Setters
    public long getPersonID() {
        return personID;
    }

    public void setPersonID(long personID) {
        this.personID = personID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
