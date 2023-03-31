package ca.mcgill.ecse321.parkinglotbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import ca.mcgill.ecse321.parkinglotbackend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Login to the system
     * @param request
     * @param email
     * @param password
     * @return Http response
     * @author Lin Wei Li
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestParam String email, @RequestParam String password) {

        // Check if already logged in
        if (AuthenticationUtility.isLoggedIn(request)) {
            return ResponseEntity.badRequest().body("Already logged in");
        }

        // Attempt to authenticate
        try {
            Account account = authenticationService.authenticate(email, password);

            // Respond with success
            HttpSession session = request.getSession(true);

            if (account.getClass().equals(ManagerAccount.class)) {
                session.setAttribute("accountID", account.getAccountID());
                session.setAttribute("role", AuthenticationUtility.Role.MANAGER);
                return ResponseEntity.ok().build();
            }
            else if (account.getClass().equals(StaffAccount.class)) {
                session.setAttribute("accountID", account.getAccountID());
                session.setAttribute("role", AuthenticationUtility.Role.STAFF);
                return ResponseEntity.ok().build();
            }
            else if (account.getClass().equals(Account.class)) {
                session.setAttribute("accountID", account.getAccountID());
                session.setAttribute("role", AuthenticationUtility.Role.CUSTOMER);
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.badRequest().body("Unknown account type");
            }

        } catch (Exception e) {     // Authentication failed
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * Logout of the system
     * @param request
     * @return Http response
     * @author Lin Wei Li
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        // Check if not logged in
        if (!AuthenticationUtility.isLoggedIn(request)) {
            return ResponseEntity.badRequest().body("Not logged in");
        }

        // Logout
        HttpSession session = request.getSession();
        session.invalidate();

        return ResponseEntity.ok().build();

    }

    /**
     * Log in as manager for smoke testing
     * @param request
     * @return Http response
     * @author Lin Wei Li
     */
    @GetMapping("/smokeTest")
    public ResponseEntity<?> smokeTest(HttpServletRequest request) {

        // Make manager
        HttpSession session = request.getSession(true);
        session.setAttribute("accountID", 1);
        session.setAttribute("role", AuthenticationUtility.Role.MANAGER);
        session.setMaxInactiveInterval(60*60*24);

        return ResponseEntity.ok().body(AuthenticationUtility.isLoggedIn(request));

    }

    /**
     * Helper method for smoke testing
     * @param request
     * @return
     */
    @GetMapping("/getRole")
    public ResponseEntity<?> getRole(HttpServletRequest request) {

        // Check if not logged in
        if (!AuthenticationUtility.isLoggedIn(request)) {
            return ResponseEntity.badRequest().body("Not logged in");
        }

        // Get role
        HttpSession session = request.getSession();
        AuthenticationUtility.Role role = (AuthenticationUtility.Role) session.getAttribute("role");

        return ResponseEntity.ok().body(role);

    }

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Add test data for smoke testing
     * @param request
     * @return
     * @author Lin Wei Li
     */
    @GetMapping("/addTestData")
    public ResponseEntity<?> addTestData(HttpServletRequest request) {

        // Person
        Person p1 = new Person("5141231234", "john");
        personRepository.save(p1);

        // Account
        Account a1 = new Account("john@gmail.com", "wasd", p1);
        accountRepository.save(a1);

        return ResponseEntity.ok().body(AuthenticationUtility.isLoggedIn(request));

    }
    
}
