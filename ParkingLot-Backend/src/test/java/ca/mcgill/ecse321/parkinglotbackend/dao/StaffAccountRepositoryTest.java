package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.data.PersonMockBuilder;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StaffAccountRepositoryTest {
    @Autowired StaffAccountRepository staffAccountRepository;

    @Autowired PersonRepository personRepository;

    @AfterEach
    void setup() {
        staffAccountRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadStaffAccount() {
        // create account
        float salary = 999;
        Person person = PersonMockBuilder.builder().build();
        StaffAccount account = new StaffAccount(person, "garage@mail.mcgill.ca", "123", salary);

        // save and retrieve account
        staffAccountRepository.save(account);
        StaffAccount retrievedAccount = staffAccountRepository.getStaffAccountBySalary(salary);

        // verify correct account is retrieved
        assertNotNull(retrievedAccount);
        assertEquals(salary, retrievedAccount.getSalary());
    }

    @Test
    @Transactional
    void testDeleteStaffAccount() {
        // create account
        Long personId = null;
        Person person = new Person("5554443333", "newJon" );
        StaffAccount account = new StaffAccount(person, "garage@mail.mcgill.ca", "123", 999);

        // deleted account and retrieve related person
        account = staffAccountRepository.save(account);
        personId = account.getPerson().getPersonID();
        staffAccountRepository.deleteStaffAccountByAccountID(account.getAccountID());
        account = staffAccountRepository.getStaffAccountByAccountID(account.getAccountID());
        person = personRepository.findPersonByPersonID(personId);

        // check account is deleted but person persists
        assertNull(account);
        assertNotNull(person);
        assertEquals(personId, person.getPersonID());
    }

    @Test
    void testModifyManagerAccountAttributes() {
        // create and save account
        String newEmail = "newEmail@email.com";
        Person person = PersonMockBuilder.builder().build();
        StaffAccount account = new StaffAccount(person, "garage@mail.mcgill.ca", "123", 999);
        account = staffAccountRepository.save(account);

        // retrieve the saved account and modify its email
        StaffAccount retrievedAccount = staffAccountRepository.getStaffAccountByAccountID(account.getAccountID());
        retrievedAccount.setEmail(newEmail);
        staffAccountRepository.save(retrievedAccount);
        retrievedAccount = staffAccountRepository.getStaffAccountByAccountID(retrievedAccount.getAccountID());

        // check the email has been modified
        assertNotNull(retrievedAccount);
        assertEquals(newEmail, retrievedAccount.getEmail());
    }

    @Test
    void testModifyManagerAccountReferences() {
        // create and save account
        Person newPerson = new Person("5554443333", "newJon" );
        Person person =  new Person("5554443333", "oldJon" );
        StaffAccount account = new StaffAccount(person, "garage@mail.mcgill.ca", "123", 999);
        account = staffAccountRepository.save(account);

        // retrieve account and link to a new person
        StaffAccount retrievedAccount = staffAccountRepository.getStaffAccountByAccountID(account.getAccountID());
        retrievedAccount.setPerson(newPerson);
        staffAccountRepository.save(retrievedAccount);
        retrievedAccount = staffAccountRepository.getStaffAccountByAccountID(retrievedAccount.getAccountID());

        // verify new relationship is persisted
        assertNotNull(retrievedAccount);
        assertEquals(newPerson.getName(), retrievedAccount.getPerson().getName());
    }
}
