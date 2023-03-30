package ca.mcgill.ecse321.parkinglotbackend.dto;

public class PersonDto {
    
    // Attributes
    private Long personID;
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
    public Long getPersonID() {
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

    public static boolean isValid(PersonDto personDto) {
        return personDto != null &&
            personDto.getPersonID() != null &&
            personDto.getName() != null &&
            personDto.getPhoneNumber() != null;
    }
}
