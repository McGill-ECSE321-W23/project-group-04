package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Find an account by id
     * @param id -account id
     * @return Account
     * @author Lin Wei Li
     */
    public Account getAccountByID(long id) throws Exception {
        Account account = accountRepository.findAccountByAccountID(id);
        if (account == null) {
            throw new Exception("No account with this id exists!");
        }
        return account;
    }

    /**
     * Find an account by person id
     * @param personID -person id
     * @return Account
     * @throws Exception
     * @author Lin Wei Li
     */
    public Account getAccountByPersonID(long personID) throws Exception {
        Account account = accountRepository.findAccountByPerson_PersonID(personID);
        if (account == null) {
            throw new Exception("No account with this person exists!");
        }
        return account;
    }

    /**
     * Find an account by email
     * @param email - account email
     * @return Account
     * @author Lin Wei Li
     */
    public Account getAccountByEmail(String email) throws Exception {
        Account account = accountRepository.findAccountByEmail(email);
        if (account == null) {
            throw new Exception("No account with this email exists!");
        }
        return account;
    }

    /**
     * Get all accounts
     * @return List<Account> - list of all accounts
     * @author Lin Wei Li
     */
    public List<Account> getAllAccounts() {
        return toList(accountRepository.findAll());
    }

    /**
     * Create an account
     * @param email - account email
     * @param password - account password
     * @param person -person object
     * @return Account
     * @author Lin Wei Li
     */
    public Account createAccount(String email, String password, Person person) throws Exception {
        // Check for duplicate emails
        if (accountRepository.existsAccountByEmail(email)) {
            throw new Exception("An account with this email already exists!");
        }

        // Attempt to create account
        if (email == null || email.trim().length() == 0) {
            throw new Exception("Email cannot be empty!");
        }
        if (password == null || password.trim().length() == 0) {
            throw new Exception("Password cannot be empty!");
        }
        if (person == null) {
            throw new Exception("Person cannot be empty!");
        }
        if (accountRepository.existsAccountByEmail(email)) {
            throw new Exception("Account with this email already exists!");
        }
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setPerson(person);
        accountRepository.save(account);
        return account;
    }

    /**
     * Delete an account
     * @param id - account id
     * @return Account
     * @author Lin Wei Li
     */
    public Account deleteAccount(long id) throws Exception {
        Account account = accountRepository.findAccountByAccountID(id);
        if (account == null) {
            throw new Exception("No account with this id exists!");
        }
        accountRepository.delete(account);
        return account;
    }

    /**
     * Update an account
     * @param id - account id
     * @param email - account email
     * @param password - account password
     * @return Account
     * @author Lin Wei Li
     */
    public Account updateAccount(long id, String email, String password) throws Exception {
        // Check for duplicate emails
        if (accountRepository.existsAccountByEmail(email)) {
            throw new Exception("An account with this email already exists!");
        }

        // Attempt to update account
        Account account = accountRepository.findAccountByAccountID(id);
        if (account == null) {
            throw new Exception("No account with this id exists!");
        }
        if (email == null || email.trim().length() == 0) {
            throw new Exception("Email cannot be empty!");
        }
        if (password == null || password.trim().length() == 0) {
            throw new Exception("Password cannot be empty!");
        }
        account.setEmail(email);
        account.setPassword(password);
        accountRepository.save(account);
        return account;
    }

    /**
     * Helper method to convert Iterable to List
     * @param iterable
     * @return List<Account>
     * @author Lin Wei Li
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
