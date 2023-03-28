package ca.mcgill.ecse321.parkinglotbackend.dto;

public class ManagerAccountDto extends StaffAccountDto {
    public ManagerAccountDto() {}

    public ManagerAccountDto(long accountID, String email, String password, PersonDto person, float salary) {
        super(accountID, email, password, person, salary);
    }
}
