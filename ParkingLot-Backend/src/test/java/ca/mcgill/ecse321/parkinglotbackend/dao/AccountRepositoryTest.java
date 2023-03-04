package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.data.AccountMockBuilder;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountRepositoryTest {
    @Autowired AccountRepository accountRepository;

    @AfterEach
    void setup() {
        accountRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadAccount() {
        Account acc = AccountMockBuilder.builder().build();

        Account savedAcc = accountRepository.save(acc);
        Account retrievedAccount = accountRepository.getAccountByAccountID(savedAcc.getAccountID());

        assertNotNull(retrievedAccount);
        assertEquals("aa", retrievedAccount.getPassword());
    }
}
