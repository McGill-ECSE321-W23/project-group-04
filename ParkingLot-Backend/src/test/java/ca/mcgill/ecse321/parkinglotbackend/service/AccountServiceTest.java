package ca.mcgill.ecse321.parkinglotbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    // Mock DB
    private static final Person P1 = new Person(1, "1111", "Person 1");
    private static final Person P2 = new Person(2, "2222", "Person 2");
    private static final Person P3 = new Person(3, "3333", "Person 3");

    private static final Account A1 = new Account(1, "p1@gmail.com", "pass1", P1);
    private static final Account A2 = new Account(2, "p2@gmail.com", "pass2", P2);
    private static final Account A3 = new Account(3, "p3@gmail.com", "pass3", P3);

    // Test data
    private static final long TEST_ID = 4;
    private static final String TEST_EMAIL = "p4@gmail.com";
    private static final String TEST_PASSWORD = "pass4";
    private static final Person TEST_PERSON = new Person(4, "4444", "Person 4");

    @BeforeEach
    public void setMockOutput() {
        lenient().when(accountRepository.findAccountByAccountID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(A1.getAccountID())) {
                return A1;
            } else if (invocation.getArgument(0).equals(A2.getAccountID())) {
                return A2;
            } else if (invocation.getArgument(0).equals(A3.getAccountID())) {
                return A3;
            } else {
                return null;
            }
        });

        lenient().when(accountRepository.findAccountByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(A1.getEmail())) {
                return A1;
            } else if (invocation.getArgument(0).equals(A2.getEmail())) {
                return A2;
            } else if (invocation.getArgument(0).equals(A3.getEmail())) {
                return A3;
            } else {
                return null;
            }
        });

        lenient().when(accountRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            return Arrays.asList(A1, A2, A3);
        });

        lenient().when(accountRepository.save(any(Account.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        // Cannot highjack delete because it returns void
    }

    @AfterEach
    public void resetMockDB() {
        A1.setAccountID(1);
        A1.setEmail("p1@gmail.com");
        A1.setPassword("pass1");
        A1.setPerson(P1);

        A2.setAccountID(2);
        A2.setEmail("p2@gmail.com");
        A2.setPassword("pass2");
        A2.setPerson(P2);

        A3.setAccountID(3);
        A3.setEmail("p3@gmail.com");
        A3.setPassword("pass3");
        A3.setPerson(P3);
    }

    @Test
    public void testGetAccountByID_Success() {
        String eMSG = "";
        Account account = null;

        // Get account
        try {
            account = accountService.getAccountByID(A1.getAccountID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("", eMSG);
        assertNotNull(account);
        assertEquals(A1, account);
    }

    @Test
    public void testGetAccountByID_InvvalidID() {
        String eMSG = "";
        Account account = null;

        // Get account
        try {
            account = accountService.getAccountByID(TEST_ID);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("No account with this id exists!", eMSG);
        assertNull(account);
    }

    @Test
    public void testGetAccountByEmail_Success() {
        String eMSG = "";
        Account account = null;

        // Get account
        try {
            account = accountService.getAccountByEmail(A1.getEmail());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("", eMSG);
        assertNotNull(account);
        assertEquals(A1, account);
    }

    @Test
    public void testGetAccountByEmail_InvalidEmail() {
        String eMSG = "";
        Account account = null;

        // Get account
        try {
            account = accountService.getAccountByEmail(TEST_EMAIL);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("No account with this email exists!", eMSG);
        assertNull(account);
    }

    @Test
    public void testGetAllAccounts() {
        String eMSG = "";
        List<Account> accounts = null;

        // Get accounts
        try {
            accounts = accountService.getAllAccounts();
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("", eMSG);
        assertNotNull(accounts);
        assertEquals(3, accounts.size());
        assertTrue(accounts.contains(A1));
        assertTrue(accounts.contains(A2));
        assertTrue(accounts.contains(A3));
    }

    @Test
    public void testCreateAccount_Success() {
        String eMSG = "";
        Account account = null;

        // Create account
        try {
            account = accountService.createAccount(TEST_EMAIL, TEST_PASSWORD, TEST_PERSON);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("", eMSG);
        assertNotNull(account);
        assertEquals(TEST_EMAIL, account.getEmail());
        assertEquals(TEST_PASSWORD, account.getPassword());
        assertEquals(TEST_PERSON, account.getPerson());
    }

    // TODO: Prevent duplicate emails

    /*
    @Test
    public void testCreateAccount_DuplicateEmail() {
        String eMSG = "";
        Account account = null;

        // Create account
        try {
            account = accountService.createAccount(A1.getEmail(), TEST_PASSWORD, TEST_PERSON);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("An account with this email already exists!", eMSG);
        assertNull(account);
    }
    */

    @Test
    public void testCreateAccount_InvalidEmail() {
        String eMSG = "";
        Account account = null;

        // Create account
        try {
            account = accountService.createAccount("", TEST_PASSWORD, TEST_PERSON);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("Email cannot be empty!", eMSG);
        assertNull(account);
    }

    @Test
    public void testCreateAccount_InvalidPassword() {
        String eMSG = "";
        Account account = null;

        // Create account
        try {
            account = accountService.createAccount(TEST_EMAIL, "", TEST_PERSON);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("Password cannot be empty!", eMSG);
        assertNull(account);
    }

    @Test
    public void testCreateAccount_InvalidPerson() {
        String eMSG = "";
        Account account = null;

        // Create account
        try {
            account = accountService.createAccount(TEST_EMAIL, TEST_PASSWORD, null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("Person cannot be empty!", eMSG);
        assertNull(account);
    }

    @Test
    public void testDeleteAccount_Success() {
        String eMSG = "";
        Account account = null;

        // Delete account
        try {
            account = accountService.deleteAccount(A1.getAccountID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("", eMSG);
        assertNotNull(account);
        assertEquals(A1, account);
    }

    @Test
    public void testDeleteAccount_InvalidID() {
        String eMSG = "";
        Account account = null;

        // Delete account
        try {
            account = accountService.deleteAccount(TEST_ID);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("No account with this id exists!", eMSG);
        assertNull(account);
    }

    @Test
    public void testUpdateAccount_Success() {
        String eMSG = "";
        Account account = null;

        // Update account
        try {
            account = accountService.updateAccount(A1.getAccountID(), TEST_EMAIL, TEST_PASSWORD);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("", eMSG);
        assertNotNull(account);
        assertEquals(TEST_EMAIL, account.getEmail());
        assertEquals(TEST_PASSWORD, account.getPassword());
    }

    @Test
    public void testUpdateAccount_InvalidID() {
        String eMSG = "";
        Account account = null;

        // Update account
        try {
            account = accountService.updateAccount(TEST_ID, TEST_EMAIL, TEST_PASSWORD);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("No account with this id exists!", eMSG);
        assertNull(account);
    }

    @Test
    public void testUpdateAccount_InvalidEmail() {
        String eMSG = "";
        Account account = null;

        // Update account
        try {
            account = accountService.updateAccount(A1.getAccountID(), "", TEST_PASSWORD);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("Email cannot be empty!", eMSG);
        assertNull(account);
    }

    @Test
    public void testUpdateAccount_InvalidPassword() {
        String eMSG = "";
        Account account = null;

        // Update account
        try {
            account = accountService.updateAccount(A1.getAccountID(), TEST_EMAIL, "");
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        // Check
        assertEquals("Password cannot be empty!", eMSG);
        assertNull(account);
    }

}
