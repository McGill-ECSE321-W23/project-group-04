package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountRepositoryTest {
    @Autowired AccountRepository accountRepository;

    @BeforeEach
    void setup() {
        accountRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadAccount() {
        String accountId = "123";
        Person person = new Person("11", "5554443333", "Jon");
        Account account = new Account(accountId, "garage@mail.mcgill.ca", "123", person);

        accountRepository.save(account);
        Account retrievedAccount = accountRepository.getAccountByAccountID(accountId);

        assertNotNull(retrievedAccount);
        assertEquals(accountId, retrievedAccount.getAccountID());
    }
}
