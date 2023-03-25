package ca.mcgill.ecse321.parkinglotbackend.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountIntegrationTest {

    @Autowired
    private TestRestTemplate client;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setUpTest() {
        accountRepository.deleteAll();
        personRepository.deleteAll();

        Person person = new Person();
        person.setName("John");
        person.setPhoneNumber("1234567890");
        personRepository.save(person);
    }

    @AfterEach
    public void clearDatabase() {
        accountRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void x() {
        Map<String,String> data = new HashMap<String,String>();
        data.put("name", "John");
        data.put("phoneNumber", "1234567890");
        data.put("email", "test@gmail.com");
        data.put("password", "123");

        ResponseEntity<Void> response = client.postForEntity("/api/account/register", data, Void.class);

        // assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("", response.getBody());
    }
    
}
