package ca.mcgill.ecse321.parkinglotbackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private AuthenticationService service;
    
    
}
