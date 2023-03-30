package ca.mcgill.ecse321.parkinglotbackend.dto;

public class StaffAccountDto extends AccountDto {
    private Float salary;

    public StaffAccountDto() {}

    public StaffAccountDto(long accountID, String email, String password, PersonDto person, float salary) {
        super(accountID, email, password, person);
        this.salary = salary;
    }

    public Float getSalary() {
        return this.salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public static boolean isValid(StaffAccountDto staffAccountDto) {
        return staffAccountDto != null &&
                staffAccountDto.getSalary() != null &&
                staffAccountDto.getAccountID() != null &&
                staffAccountDto.getEmail() != null &&
                staffAccountDto.getPassword() != null &&
                PersonDto.isValid(staffAccountDto.getPerson());
    }

}
