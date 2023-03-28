package ca.mcgill.ecse321.parkinglotbackend.dto;

public class StaffAccountDto extends AccountDto {
    private float salary;

    public StaffAccountDto() {}

    public StaffAccountDto(long accountID, String email, String password, PersonDto person, float salary) {
        super(accountID, email, password, person);
        this.salary = salary;
    }

    public float getSalary() {
        return this.salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

}
