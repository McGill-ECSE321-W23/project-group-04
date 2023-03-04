package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ManagerAccountRepositoryTest {
    @Autowired ManagerAccountRepository managerAccountRepository;

    @BeforeEach
    void setup() {
        managerAccountRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadManagerAccount() {
        String accountId = "123";
        Person person = new Person("11", "5554443333", "Jon");
        ManagerAccount account = new ManagerAccount(accountId, "garage@mail.mcgill.ca", "123", person, 999);

        managerAccountRepository.save(account);
        ManagerAccount retrievedAccount = managerAccountRepository.getManagerAccountByAccountIDIsNotNull();

        assertNotNull(retrievedAccount);
    }
}
