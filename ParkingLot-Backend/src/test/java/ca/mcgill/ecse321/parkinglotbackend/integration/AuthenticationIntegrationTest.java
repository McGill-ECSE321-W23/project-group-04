package ca.mcgill.ecse321.parkinglotbackend.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.ManagerAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationIntegrationTest {

    @Autowired
    private TestRestTemplate client;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StaffAccountRepository staffAccountRepository;

    @Autowired
    private ManagerAccountRepository managerAccountRepository;

    @Autowired
    private PersonRepository personRepository;

    // Test data
    private static final String CUSTOMER_EMAIL = "customer@gmail.com";
    private static final String CUSTOMER_PASSWORD = "123";
    private static final String CUSTOMER_NAME = "customer";
    private static final String CUSTOMER_PHONE_NUMBER = "1234567890";

    private static final String STAFF_EMAIL = "employee@pls.com";
    private static final String STAFF_PASSWORD = "456";
    private static final String STAFF_NAME = "employee";
    private static final String STAFF_PHONE_NUMBER = "0987654321";

    private static final String MANAGER_EMAIL = "manager@pls.com";
    private static final String MANAGER_PASSWORD = "789";
    private static final String MANAGER_NAME = "manager";
    private static final String MANAGER_PHONE_NUMBER = "0123456789";
/*
    @BeforeEach
    public void setUpTest() {
        // Clear database
        accountRepository.deleteAll();
        personRepository.deleteAll();

        // Customer
        Person customerPerson = new Person();
        // customerPerson.setPersonID(0);
        customerPerson.setName(CUSTOMER_NAME);
        customerPerson.setPhoneNumber(CUSTOMER_PHONE_NUMBER);

        Person savedCustomerPerson = personRepository.save(customerPerson);
        Person retrievedCustomerPerson = personRepository.findPersonByPersonID(savedCustomerPerson.getPersonID());

        Account customerAccount = new Account();
        // customerAccount.setAccountID(0);
        customerAccount.setEmail(CUSTOMER_EMAIL);
        customerAccount.setPassword(CUSTOMER_PASSWORD);
        customerAccount.setPerson(retrievedCustomerPerson);

        accountRepository.save(customerAccount);

        // Staff
        Person staffPerson = new Person();
        // staffPerson.setPersonID(1);
        staffPerson.setName(STAFF_NAME);
        staffPerson.setPhoneNumber(STAFF_PHONE_NUMBER);

        Person savedStaffPerson = personRepository.save(staffPerson);
        Person retrievedStaffPerson = personRepository.findPersonByPersonID(savedStaffPerson.getPersonID());

        StaffAccount staffAccount = new StaffAccount();
        // staffAccount.setAccountID(1);
        staffAccount.setEmail(STAFF_EMAIL);
        staffAccount.setPassword(STAFF_PASSWORD);
        staffAccount.setPerson(retrievedStaffPerson);

        staffAccountRepository.save(staffAccount);

        // Manager
        Person managerPerson = new Person();
        // managerPerson.setPersonID(2);
        managerPerson.setName(MANAGER_NAME);
        managerPerson.setPhoneNumber(MANAGER_PHONE_NUMBER);

        Person savedManagerPerson = personRepository.save(managerPerson);
        Person retrievedManagerPerson = personRepository.findPersonByPersonID(savedManagerPerson.getPersonID());

        ManagerAccount managerAccount = new ManagerAccount();
        // managerAccount.setAccountID(2);
        managerAccount.setEmail(MANAGER_EMAIL);
        managerAccount.setPassword(MANAGER_PASSWORD);
        managerAccount.setPerson(retrievedManagerPerson);

        managerAccountRepository.save(managerAccount);
    }
*/

    @BeforeEach
    public void setUpTest() {
        accountRepository.deleteAll();
        personRepository.deleteAll();

        // Customer
        Person customerPerson = new Person();
        customerPerson.setName(CUSTOMER_NAME);
        customerPerson.setPhoneNumber(CUSTOMER_PHONE_NUMBER);

        // Person savedCustomerPerson = personRepository.save(customerPerson);
        // Person retrievedCustomerPerson = personRepository.findPersonByPhoneNumber(savedCustomerPerson.getPhoneNumber());

        Account customerAccount = new Account();
        customerAccount.setEmail(CUSTOMER_EMAIL);
        customerAccount.setPassword(CUSTOMER_PASSWORD);
        customerAccount.setPerson(customerPerson);

        accountRepository.save(customerAccount);
    }

    @AfterEach
    public void clearDatabase() {
        accountRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void x() {
        int countPersons = 0;
        for (Person p : personRepository.findAll()) {
            countPersons++;
        }
        int countAccounts = 0;
        for (Account a : accountRepository.findAll()) {
            countAccounts++;
        }
        assertEquals(1, countPersons);
        assertEquals(1, countAccounts);
    }

    @Test
    public void testLogin_Success() {
        // Login
        Account data = new Account();
        data.setEmail(CUSTOMER_EMAIL);
        data.setPassword(CUSTOMER_PASSWORD);
        ResponseEntity<Void> response = client.postForEntity("/api/auth/login", data, Void.class);

        // Check response
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
}
