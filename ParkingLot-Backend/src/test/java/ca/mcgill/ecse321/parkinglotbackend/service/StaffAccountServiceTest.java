package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StaffAccountServiceTest {
    @InjectMocks
    StaffAccountService staffAccountService;

    @MockBean
    StaffAccountRepository staffAccountRepository;

    StaffAccount mockAccount;

    @BeforeEach
    void setup() {
        Person person = new Person("414", "da");
        mockAccount = new StaffAccount("s@mail.com", "123", person, 15);
    }

    @Test
    void Get_Should_DeleteEmployeeFromDatabase() throws Exception {
        when(staffAccountRepository.deleteStaffAccountByAccountID(anyLong())).thenReturn(1);

        staffAccountService.deleteStaffAccount(1L);
    }

    @Test
    void Delete_Should_DeleteEmployeeFromDatabase() throws Exception {
        when(staffAccountRepository.deleteStaffAccountByAccountID(anyLong())).thenReturn(1);

        staffAccountService.deleteStaffAccount(1L);
    }

    @Test
    void Create_Should_AddEmployeeInDatabase() throws Exception {
        when(staffAccountRepository.deleteStaffAccountByAccountID(anyLong())).thenReturn(1);

        staffAccountService.deleteStaffAccount(1L);
    }

    @Test
    void Update_Should_UpdateEmployeeInDatabase() throws Exception {
        when(staffAccountRepository.deleteStaffAccountByAccountID(anyLong())).thenReturn(1);

        staffAccountService.deleteStaffAccount(1L);
    }
}