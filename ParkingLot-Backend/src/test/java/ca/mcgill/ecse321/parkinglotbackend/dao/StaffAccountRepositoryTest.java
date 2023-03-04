package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StaffAccountRepositoryTest {
    @Autowired StaffAccountRepository staffAccountRepository;

    @BeforeEach
    void setup() {
        staffAccountRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadStaffAccount() {
        String accountId = "123";
        float salary = 999;
        Person person = new Person("11", "5554443333", "Jon");
        StaffAccount account = new StaffAccount(accountId, "garage@mail.mcgill.ca", "123", person, salary);

        staffAccountRepository.save(account);
        StaffAccount retrievedAccount = staffAccountRepository.getStaffAccountBySalary(salary);

        assertNotNull(retrievedAccount);
        assertEquals(salary, retrievedAccount.getSalary());
    }
}
