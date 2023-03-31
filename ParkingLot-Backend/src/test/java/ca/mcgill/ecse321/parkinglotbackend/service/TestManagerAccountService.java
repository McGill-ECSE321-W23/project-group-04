package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ManagerAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TestManagerAccountService {

    @InjectMocks
    ManagerAccountService managerAccountService;

    @MockBean
    ManagerAccountRepository managerAccountRepository;

    ManagerAccount managerAccount;

    @BeforeEach
    void setup() {
        managerAccount = new ManagerAccount();
    }

    @Test
    void Get_Should_ReturnAManagerAccount() {
        when(managerAccountRepository.getManagerAccountByAccountIDIsNotNull()).thenReturn(managerAccount);

        ManagerAccount foundAccount = managerAccountService.getManagerAccount();

        assertNotNull(foundAccount);
    }
}
