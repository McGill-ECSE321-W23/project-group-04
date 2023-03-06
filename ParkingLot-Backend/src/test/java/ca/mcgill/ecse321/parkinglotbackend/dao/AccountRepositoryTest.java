package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.data.AccountMockBuilder;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountRepositoryTest {
    @Autowired AccountRepository accountRepository;

    @Autowired PersonRepository personRepository;

    @AfterEach
    void setup() {
        accountRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadAccount() {
        // Create an account
        Account account  = new AccountMockBuilder().build();

        // Save the account
        Account savedAccount = accountRepository.save(account);

        // Check that the account was saved correctly
        assertNotNull(savedAccount);
        assertEquals(account.getAccountID(), savedAccount.getAccountID());
        assertEquals(account.getEmail(), savedAccount.getEmail());
        assertEquals(account.getPassword(), savedAccount.getPassword());
        assertEquals(account.getPerson(), savedAccount.getPerson());

        // Retrieve the account
        Account retrievedAccount = accountRepository.findAccountByAccountID(savedAccount.getAccountID());

        // Check that the account was saved and retrieved correctly
        assertNotNull(retrievedAccount);
        assertEquals(savedAccount.getAccountID(), retrievedAccount.getAccountID());
        assertEquals(savedAccount.getEmail(), retrievedAccount.getEmail());
        assertEquals(savedAccount.getPassword(), retrievedAccount.getPassword());
        assertEquals(savedAccount.getPerson(), retrievedAccount.getPerson());
    }

    @Test
    void testLogin() {
        // Create an account
        Account account  = new AccountMockBuilder().build();

        // Save the account
        Account savedAccount = accountRepository.save(account);

        // Check that the account was saved correctly
        assertNotNull(savedAccount);
        assertEquals(account.getAccountID(), savedAccount.getAccountID());
        assertEquals(account.getEmail(), savedAccount.getEmail());
        assertEquals(account.getPassword(), savedAccount.getPassword());
        assertEquals(account.getPerson(), savedAccount.getPerson());

        // Login
        String email = savedAccount.getEmail();
        String password = savedAccount.getPassword();
        boolean exists = accountRepository.existsAccountByEmailAndPassword(email, password);
        Account retrievedAccount = accountRepository.findAccountByEmailAndPassword(email, password);

        // Check that the account was saved and retrieved correctly
        assertTrue(exists);
        assertNotNull(retrievedAccount);
        assertEquals(savedAccount.getAccountID(), retrievedAccount.getAccountID());
        assertEquals(savedAccount.getEmail(), retrievedAccount.getEmail());
        assertEquals(savedAccount.getPassword(), retrievedAccount.getPassword());
        assertEquals(savedAccount.getPerson(), retrievedAccount.getPerson());
    }

    @Test
    void testLoginFail() {
        // Create an account
        Account account  = new AccountMockBuilder().build();

        // Save the account
        Account savedAccount = accountRepository.save(account);

        // Check that the account was saved correctly
        assertNotNull(savedAccount);
        assertEquals(account.getAccountID(), savedAccount.getAccountID());
        assertEquals(account.getEmail(), savedAccount.getEmail());
        assertEquals(account.getPassword(), savedAccount.getPassword());
        assertEquals(account.getPerson(), savedAccount.getPerson());

        // Login
        String email = savedAccount.getEmail();
        String password = "wrong password";
        boolean exists = accountRepository.existsAccountByEmailAndPassword(email, password);
        Account retrievedAccount = accountRepository.findAccountByEmailAndPassword(email, password);

        // Check that login failed
        assertTrue(!exists);
        assertNull(retrievedAccount);
    }
}
