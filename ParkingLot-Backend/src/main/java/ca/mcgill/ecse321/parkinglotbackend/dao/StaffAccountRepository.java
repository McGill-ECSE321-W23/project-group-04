package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import org.springframework.data.repository.CrudRepository;

public interface StaffAccountRepository extends CrudRepository<StaffAccount, Long> {
    StaffAccount getStaffAccountBySalary(float salary);
}