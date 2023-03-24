package ca.mcgill.ecse321.parkinglotbackend.dto;

public class AccountDto {
    
    // Attributes
    private long accountID;
    private String email;
    private String password;
    private PersonDto person;

    // Constructors
    public AccountDto() {
    }

    public AccountDto(long accountID, String email, String password, PersonDto person) {
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.person = person;
    }

    // Getters and Setters
    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

}
