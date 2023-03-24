package ca.mcgill.ecse321.parkinglotbackend.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private static final Person P1 = new Person(1, "5141231234", "My Name");

    // Test data


    @BeforeEach
    public void setMockOutput() {
        lenient().when(personRepository.findPersonByPersonID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            // lambda
            return null;
        });
    }

}
