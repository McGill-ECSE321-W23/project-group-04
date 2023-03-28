package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffAccountService {
    @Autowired
    private StaffAccountRepository staffAccountRepository;

    public StaffAccount getStaffAccount(long id) {
        StaffAccount account = staffAccountRepository.getStaffAccountByAccountID(id);
        return account;
    }

    public StaffAccount updateStaffAccount(Long employeeId, String email, String password, Person person, Float salary) {
        StaffAccount staffAccount = new StaffAccount(employeeId, email, password, person, salary);
        return staffAccountRepository.save(staffAccount);
    }

    public StaffAccount createStaffAccount(String email, String password, Person person, Float salary) {
        StaffAccount staffAccount = new StaffAccount(email, password, person, salary);
        return staffAccountRepository.save(staffAccount);
    }

    public Integer deleteStaffAccount(long accountId) {
        return staffAccountRepository.deleteStaffAccountByAccountID(accountId);
    }
}
