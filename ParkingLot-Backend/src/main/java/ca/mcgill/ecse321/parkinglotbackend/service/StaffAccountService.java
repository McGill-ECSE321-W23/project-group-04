package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffAccountService {
    @Autowired
    private StaffAccountRepository staffAccountRepository;

    /**
     * get a staff account by id
     * @param id - account id
     * @return account found
     * @author Edwin You Zhou
     */
    public StaffAccount getStaffAccount(long id) {
        StaffAccount account = staffAccountRepository.getStaffAccountByAccountID(id);
        return account;
    }

    /*
     * get all staff accounts
     */
    public List<StaffAccount> getAllStaffAccounts() {
        return toList(staffAccountRepository.findAll());
    }

    /**
     * update an existing staff account
     * @param employeeId - employee id
     * @param email - account email
     * @param password - account password
     * @param person - person object
     * @param salary - person salary
     * @return updated account
     * @author Edwin You Zhou
     */
    public StaffAccount updateStaffAccount(Long employeeId, String email, String password, Person person, Float salary) {
        StaffAccount staffAccount = new StaffAccount(employeeId, email, password, person, salary);
        return staffAccountRepository.save(staffAccount);
    }

    /**
     * perform null check for arguments and save the new account
     * @param name person name
     * @param phone - account phone number
     * @param password - account password
     * @param email - account email
     * @param salary - account salary
     * @return created staff account
     * @author Edwin You Zhou
     */
    public StaffAccount createStaffAccount(String name, String phone, String password, String email, Float salary) {
        if (name != null && phone != null && password != null && email != null && salary != null) {
            Person person = new Person();
            person.setPhoneNumber(phone);
            person.setName(name);
            StaffAccount staffAccount = new StaffAccount(email, password, person, salary);
            return staffAccountRepository.save(staffAccount);
        }
        throw new IllegalArgumentException("field is null");
    }

    /**
     * delete a staff account
     * @param accountId account id
     * @return
     * @author Edwin You Zhou
     */
    public Integer deleteStaffAccount(Long accountId) {
        if (accountId != null) {

            return staffAccountRepository.deleteStaffAccountByAccountID(accountId);
        }
        throw new IllegalArgumentException("id is null");
    }

    /**
     * helper method to convert to list of objects
     * @param iterable
     * @param <T>
     * @return list of objects
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
