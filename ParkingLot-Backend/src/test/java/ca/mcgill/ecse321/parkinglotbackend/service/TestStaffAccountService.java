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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Testing Staff Account service class
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_service_unit_testing_setup_with_mockito
 * @author Ed Win
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TestStaffAccountService {
    @InjectMocks
    StaffAccountService staffAccountService;

    @MockBean
    StaffAccountRepository staffAccountRepository;

    Person mockPerson;

    StaffAccount mockAccount;

    @BeforeEach
    void setup() {
        mockPerson = new Person("414", "da");
        mockAccount = new StaffAccount("s@mail.com", "123", mockPerson, 15);
    }

    @Test
    void Get_Should_GetEmployeeFromDatabase() throws Exception {
        when(staffAccountRepository.getStaffAccountByAccountID(anyLong())).thenReturn(mockAccount);

        StaffAccount staffAccount = staffAccountService.getStaffAccount(1L);

        assertEquals("mail is not equal","s@mail.com", staffAccount.getEmail());
        assertEquals("salary is not equal",15f, staffAccount.getSalary());
        assertEquals("password is not equal","123", staffAccount.getPassword());
        assertEquals("name is not equal","da", staffAccount.getPerson().getName());
        assertEquals("phone is not equal","414", staffAccount.getPerson().getPhoneNumber());
    }

    @Test
    void Delete_Should_DeleteEmployeeFromDatabase() throws Exception {
        when(staffAccountRepository.deleteStaffAccountByAccountID(anyLong())).thenReturn(1);

        assertEquals("account not deleted",1, staffAccountService.deleteStaffAccount(1L));
    }

    @Test
    void Create_Should_AddEmployeeInDatabase() throws Exception {
        mockAccount.setAccountID(2L);
        when(staffAccountRepository.save(any())).thenReturn(mockAccount);

        StaffAccount acc = staffAccountService.createStaffAccount("ed", "23", "123", "ed@mail", 15f);

        assertEquals("account not saved", 2L, acc.getAccountID());
    }

    @Test
    void Update_Should_UpdateEmployeeInDatabase() throws Exception {
        mockAccount.setAccountID(2L);
        when(staffAccountRepository.save(any())).thenReturn(mockAccount);

        StaffAccount acc = staffAccountService.updateStaffAccount(2L,"ed@mail", "23", mockPerson, 15f);

        assertEquals("account not saved", 2L, acc.getAccountID());
    }
}