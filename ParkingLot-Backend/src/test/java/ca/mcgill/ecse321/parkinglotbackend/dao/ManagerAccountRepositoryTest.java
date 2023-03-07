package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.data.PersonMockBuilder;
import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ManagerAccountRepositoryTest {
    @Autowired ManagerAccountRepository managerAccountRepository;

    @Autowired PersonRepository personRepository;

    @AfterEach
    void setup() {
        managerAccountRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadManagerAccount() {
        // create accounts
        String managerEmail = "garage@mail.mcgill.ca";
        Person person = new Person("5554443333", "Jon" );
        ManagerAccount account = new ManagerAccount(person, managerEmail, "123", 999);

        // save and retrieve accounts
        managerAccountRepository.save(account);
        ManagerAccount retrievedAccount = managerAccountRepository.getManagerAccountByAccountIDIsNotNull();

        // check correct account is loaded
        assertNotNull(retrievedAccount);
        assertEquals(managerEmail, retrievedAccount.getEmail());
    }

    @Test
    @Transactional
    void testDeleteManagerAccount() {
        // create account
        Long personId = null;
        Person person = new Person("5554443333", "newJon" );
        ManagerAccount account = new ManagerAccount(person, "garage@mail.mcgill.ca", "123", 999);

        // deleted account and retrieve related person
        account = managerAccountRepository.save(account);
        personId = account.getPerson().getPersonID();
        managerAccountRepository.deleteManagerAccountByAccountIDNotNull();
        account = managerAccountRepository.getManagerAccountByAccountIDIsNotNull();
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
        ManagerAccount account = new ManagerAccount(person, "garage@mail.mcgill.ca", "123", 999);
        managerAccountRepository.save(account);

        // retrieve the saved account and modify its email
        ManagerAccount retrievedAccount = managerAccountRepository.getManagerAccountByAccountIDIsNotNull();
        retrievedAccount.setEmail(newEmail);
        managerAccountRepository.save(retrievedAccount);
        retrievedAccount = managerAccountRepository.getManagerAccountByAccountIDIsNotNull();

        // check the email has been modified
        assertNotNull(retrievedAccount);
        assertEquals(newEmail, retrievedAccount.getEmail());
    }

    @Test
    void testModifyManagerAccountReferences() {
        // create and save account
        Person newPerson = new Person("5554443333", "newJon" );
        Person person =  new Person("5554443333", "oldJon" );
        ManagerAccount account = new ManagerAccount(person, "garage@mail.mcgill.ca", "123", 999);
        managerAccountRepository.save(account);

        // retrieve account and link to a new person
        ManagerAccount retrievedAccount = managerAccountRepository.getManagerAccountByAccountIDIsNotNull();
        retrievedAccount.setPerson(newPerson);
        managerAccountRepository.save(retrievedAccount);
        retrievedAccount = managerAccountRepository.getManagerAccountByAccountIDIsNotNull();

        // verify new relationship is persisted
        assertNotNull(retrievedAccount);
        assertEquals(newPerson.getName(), retrievedAccount.getPerson().getName());
    }
}
