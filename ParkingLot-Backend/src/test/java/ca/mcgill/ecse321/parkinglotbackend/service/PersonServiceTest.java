package ca.mcgill.ecse321.parkinglotbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    // Mock DB
    private static final Person P1 = new Person(1, "1111", "Person 1");
    private static final Person P2 = new Person(2, "2222", "Person 2");
    private static final Person P3 = new Person(3, "3333", "Person 3");

    // Test data
    private static final long TEST_ID = 4;
    private static final String TEST_NAME = "FAKE Person 4";
    private static final String TEST_PHONE = "4444";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(personRepository.findPersonByPersonID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(P1.getPersonID())) {
                return P1;
            } else if (invocation.getArgument(0).equals(P2.getPersonID())) {
                return P2;
            } else if (invocation.getArgument(0).equals(P3.getPersonID())) {
                return P3;
            } else {
                return null;
            }
        });

        lenient().when(personRepository.findPersonByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(P1.getName())) {
                return P1;
            } else if (invocation.getArgument(0).equals(P2.getName())) {
                return P2;
            } else if (invocation.getArgument(0).equals(P3.getName())) {
                return P3;
            } else {
                return null;
            }
        });

        lenient().when(personRepository.findPersonByPhoneNumber(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(P1.getPhoneNumber())) {
                return P1;
            } else if (invocation.getArgument(0).equals(P2.getPhoneNumber())) {
                return P2;
            } else if (invocation.getArgument(0).equals(P3.getPhoneNumber())) {
                return P3;
            } else {
                return null;
            }
        });

        lenient().when(personRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<Person> persons = new ArrayList<>();
            persons.add(P1);
            persons.add(P2);
            persons.add(P3);
            return persons;
        });

        lenient().when(personRepository.existsPersonByPhoneNumber(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(P1.getPhoneNumber())) {
                return true;
            } else if (invocation.getArgument(0).equals(P2.getPhoneNumber())) {
                return true;
            } else if (invocation.getArgument(0).equals(P3.getPhoneNumber())) {
                return true;
            } else {
                return false;
            }
        });

        lenient().when(personRepository.save(any(Person.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        // Cannot highjack delete because it returns void
    }

    @AfterEach
    public void resetMockDB() {
        P1.setPersonID(1);
        P1.setPhoneNumber("1111");
        P1.setName("Person 1");

        P2.setPersonID(2);
        P2.setPhoneNumber("2222");
        P2.setName("Person 2");

        P3.setPersonID(3);
        P3.setPhoneNumber("3333");
        P3.setName("Person 3");
    }

    @Test
    public void testGetPersonByID_Success() {
        String eMSg = "";
        Person person = null;

        try {
            person  = personService.getPersonByID(P1.getPersonID());
        } catch (Exception e) {
            eMSg = e.getMessage();
        }
        
        assertEquals("", eMSg);
        assertNotNull(person);
        assertEquals(P1, person);
    }

    @Test
    public void testGetPersonByID_InvalidID() {
        String eMSg = "";
        Person person = null;

        try {
            person  = personService.getPersonByID(TEST_ID);
        } catch (Exception e) {
            eMSg = e.getMessage();
        }
        
        assertEquals("No person with this id exists!", eMSg);
        assertNull(person);
    }

    @Test
    public void testGetPersonByName_Success() {
        Person person = personService.getPersonByName(P1.getName());
        assertNotNull(person);
        assertEquals(P1, person);
    }

    @Test
    public void testGetPersonByName_InvalidName() {
        assertNull(personService.getPersonByName(TEST_NAME));
    }

    @Test
    public void testGetPersonByPhoneNumber_Succeess() {
        Person person = personService.getPersonByPhoneNumber(P1.getPhoneNumber());
        assertEquals(P1, person);
    }

    @Test
    public void testGetPersonByPhoneNumber_InvalidPhoneNumber() {
        assertNull(personService.getPersonByPhoneNumber(TEST_PHONE));
    }

    @Test
    public void testGetAllPerssons() {
        List<Person> persons = personService.getAllPersons();
        assertEquals(3, persons.size());
        assertEquals(P1, persons.get(0));
        assertEquals(P2, persons.get(1));
        assertEquals(P3, persons.get(2));
    }

    @Test
    public void testCreatePerson_Success() {
        String eMSG = "";
        Person person = null;

        // Create person
        try {
            person = personService.createPerson(TEST_NAME, TEST_PHONE);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was created
        assertEquals("", eMSG);
        assertNotNull(person);
        assertEquals(TEST_NAME, person.getName());
        assertEquals(TEST_PHONE, person.getPhoneNumber());
    }

    @Test
    public void testCreatePerson_InvalidName() {
        String eMSG = "";
        Person person = null;

        // Create person
        try {
            person = personService.createPerson("", TEST_PHONE);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was created
        assertEquals("Name cannot be empty!", eMSG);
        assertEquals(null, person);
    }

    @Test
    public void testCreatePerson_InvalidPhoneNumber() {
        String eMSG = "";
        Person person = null;

        // Create person
        try {
            person = personService.createPerson(TEST_NAME, "");
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was created
        assertEquals("Phone number cannot be empty!", eMSG);
        assertEquals(null, person);
    }

    @Test
    public void testCreatePerson_DuplicatePhoneNumber() {
        String eMSG = "";
        Person person = null;

        // Create person
        try {
            person = personService.createPerson(TEST_NAME, P1.getPhoneNumber());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was created
        assertEquals("Phone number already exists!", eMSG);
        assertEquals(null, person);
    }

    @Test
    public void testDeletePerson_Success() {
        String eMSG = "";
        Person person = null;

        // Delete person
        try {
            person = personService.deletePerson(P1.getPersonID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was deleted
        assertEquals("", eMSG);
        assertNotNull(person);
        assertEquals(P1, person);
    }

    @Test
    public void testDeletePerson_InvalidID() {
        Person person = null;

        // Delete person
        person = personService.deletePerson(TEST_ID);

        // Check if person was deleted
        assertNull(person);
    }

    @Test
    public void testUpdatePerson_Success() {
        String eMSG = "";
        Person person = null;

        // Update person
        try {
            person = personService.updatePerson(P1.getPersonID(), TEST_NAME, TEST_PHONE);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was updated
        assertEquals("", eMSG);
        assertNotNull(person);
        assertEquals(TEST_NAME, person.getName());
        assertEquals(TEST_PHONE, person.getPhoneNumber());
    }

    @Test
    public void testUpdatePerson_InvalidID() {
        String eMSG = "";
        Person person = null;

        // Update person
        try {
            person = personService.updatePerson(TEST_ID, TEST_NAME, TEST_PHONE);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was updated
        assertEquals("No person with this id exists!", eMSG);
        assertNull(person);
    }

    @Test
    public void testUpdatePerson_InvalidName() {
        String eMSG = "";
        Person person = null;

        // Update person
        try {
            person = personService.updatePerson(P1.getPersonID(), "", TEST_PHONE);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was updated
        assertEquals("Name cannot be empty!", eMSG);
        assertNull(person);
    }

    @Test
    public void testUpdatePerson_InvalidPhoneNumber() {
        String eMSG = "";
        Person person = null;

        // Update person
        try {
            person = personService.updatePerson(P1.getPersonID(), TEST_NAME, "");
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was updated
        assertEquals("Phone number cannot be empty!", eMSG);
        assertNull(person);
    }

    @Test
    public void testUpdatePerson_DuplicatePhoneNumber() {
        String eMSG = "";
        Person person = null;

        // Update person
        try {
            person = personService.updatePerson(P1.getPersonID(), TEST_NAME, P2.getPhoneNumber());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        // Check if person was updated
        assertEquals("Phone number already exists!", eMSG);
        assertNull(person);
    }

}
