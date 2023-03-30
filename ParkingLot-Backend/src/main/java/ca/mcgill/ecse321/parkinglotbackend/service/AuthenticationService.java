package ca.mcgill.ecse321.parkinglotbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.ManagerAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;

@Service
public class AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StaffAccountRepository staffAccountRepository;

    @Autowired
    private ManagerAccountRepository managerAccountRepository;
    
    /**
     * Authenticate an account with email and password
     * @param email
     * @param password
     * @return Account
     * @author Lin Wei Li
     */
    @Transactional
    public Account authenticate(String email, String password) throws Exception {

        // Check input
        if (email == null || email.trim().length() == 0 || password == null || password.trim().length() == 0) {
            throw new Exception("Please enter an email and a password");
        }

        // Try to get account
        Account account = accountRepository.findAccountByEmail(email);
        StaffAccount staffAccount = staffAccountRepository.getStaffAccountByEmail(email);
        ManagerAccount managerAccount = managerAccountRepository.getManagerAccountByEmail(email);

        // Check if account exists
        if (account != null) {
            // Check if password is correct
            if (account.getPassword().equals(password)) {
                // Return account
                return account;
            } else {
                throw new Exception("Incorrect password");
            }
        } else if (staffAccount != null) {
            // Check if password is correct
            if (staffAccount.getPassword().equals(password)) {
                // Return account
                return staffAccount;
            } else {
                throw new Exception("Incorrect password");
            }
        } else if (managerAccount != null) {
            // Check if password is correct
            if (managerAccount.getPassword().equals(password)) {
                // Return account
                return managerAccount;
            } else {
                throw new Exception("Incorrect password");
            }
        } else {
            throw new Exception("No account with provided email was found");
        }

    }

}
