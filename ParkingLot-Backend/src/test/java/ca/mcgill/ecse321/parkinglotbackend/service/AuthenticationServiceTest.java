package ca.mcgill.ecse321.parkinglotbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.ManagerAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private StaffAccountRepository staffAccountRepository;

    @Mock
    private ManagerAccountRepository managerAccountRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    // Mock DB
    private static final String MANAGER_EMAIL = "manager@pls.com";
    private static final String STAFF_EMAIL = "staff01@pls.ca";
    private static final String ACCOUNT_EMAIL = "john@gmail.com";
    private static final String VALID_PASSWORD = "password";
    private static final Person PERSON = new Person(1, "5141231234", "My Name");

    // Test data
    private static final String INVALID_ACCOUNT_EMAIL = "FAKEjohn@gmail.com";
    private static final String INVALID_PASSWORD = "FAKEpassword";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(accountRepository.findAccountByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ACCOUNT_EMAIL)) {
                Account account = new Account();
                account.setEmail(ACCOUNT_EMAIL);
                account.setPassword(VALID_PASSWORD);
                account.setPerson(PERSON);
                return account;
            } else {
                return null;
            }
        });

        lenient().when(staffAccountRepository.getStaffAccountByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(STAFF_EMAIL)) {
                StaffAccount account = new StaffAccount();
                account.setEmail(STAFF_EMAIL);
                account.setPassword(VALID_PASSWORD);
                account.setPerson(PERSON);
                return account;
            } else {
                return null;
            }
        });

        lenient().when(managerAccountRepository.getManagerAccountByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MANAGER_EMAIL)) {
                ManagerAccount account = new ManagerAccount();
                account.setEmail(MANAGER_EMAIL);
                account.setPassword(VALID_PASSWORD);
                account.setPerson(PERSON);
                return account;
            } else {
                return null;
            }
        });
    }

    @Test
    public void testValidCustomerAuth() {
        String eMsg = "";
        Account account = null;

        // Authenticate
        try {
            account = authenticationService.authenticate(ACCOUNT_EMAIL, VALID_PASSWORD);
        } catch (Exception e) {
            eMsg = e.getMessage();
        }

        // Check
        assertEquals("", eMsg);
        assertNotNull(account);
        assertEquals(Account.class, account.getClass());
        assertEquals(ACCOUNT_EMAIL, account.getEmail());
        assertEquals(VALID_PASSWORD, account.getPassword());
        assertEquals(PERSON.getPersonID(), account.getPerson().getPersonID());
    }

    @Test
    public void testValidStaffAuth() {
        String eMsg = "";
        Account account = null;

        // Authenticate
        try {
            account = authenticationService.authenticate(STAFF_EMAIL, VALID_PASSWORD);
        } catch (Exception e) {
            eMsg = e.getMessage();
        }

        // Check
        assertEquals("", eMsg);
        assertNotNull(account);
        assertEquals(StaffAccount.class, account.getClass());
        assertEquals(STAFF_EMAIL, account.getEmail());
        assertEquals(VALID_PASSWORD, account.getPassword());
        assertEquals(PERSON.getPersonID(), account.getPerson().getPersonID());
    }

    @Test
    public void testValidManagerAuth() {
        String eMsg = "";
        Account account = null;

        // Authenticate
        try {
            account = authenticationService.authenticate(MANAGER_EMAIL, VALID_PASSWORD);
        } catch (Exception e) {
            eMsg = e.getMessage();
        }

        // Check
        assertEquals("", eMsg);
        assertNotNull(account);
        assertEquals(ManagerAccount.class, account.getClass());
        assertEquals(MANAGER_EMAIL, account.getEmail());
        assertEquals(VALID_PASSWORD, account.getPassword());
        assertEquals(PERSON.getPersonID(), account.getPerson().getPersonID());
    }

    @Test
    public void testNullEmailAuth() {
        String eMsg = "";
        Account account = null;

        // Authenticate
        try {
            account = authenticationService.authenticate(null, VALID_PASSWORD);
        } catch (Exception e) {
            eMsg = e.getMessage();
        }

        // Check
        assertEquals("Please enter an email and a password", eMsg);
        assertNull(account);
    }

    @Test
    public void testNullPasswordAuth() {
        String eMsg = "";
        Account account = null;

        // Authenticate
        try {
            account = authenticationService.authenticate(ACCOUNT_EMAIL, null);
        } catch (Exception e) {
            eMsg = e.getMessage();
        }

        // Check
        assertEquals("Please enter an email and a password", eMsg);
        assertNull(account);
    }

    @Test
    public void testCustomerInvalidEmailAuth() {
        String eMsg = "";
        Account account = null;

        // Authenticate
        try {
            account = authenticationService.authenticate(INVALID_ACCOUNT_EMAIL, VALID_PASSWORD);
        } catch (Exception e) {
            eMsg = e.getMessage();
        }

        // Check
        assertEquals("No account with provided email was found", eMsg);
        assertNull(account);
    }

    @Test
    public void testStaffInvalidPasswordAuth() {
        String eMsg = "";
        Account account = null;

        // Authenticate
        try {
            account = authenticationService.authenticate(STAFF_EMAIL, INVALID_PASSWORD);
        } catch (Exception e) {
            eMsg = e.getMessage();
        }

        // Check
        assertEquals("Incorrect password", eMsg);
        assertNull(account);
    }
    
}
