package ca.mcgill.ecse321.parkinglotbackend.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.DtoUtility;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.service.AccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private PersonService personService;

    /**
     * Register a new Account
     * @param request -anyone can access this method
     * @param email - email address of the account
     * @param password - the account password
     * @param name - the name of the person
     * @param phoneNumber - the phone number of the person
     * @return error message is encountered
     * @author Lin Wei Li
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(HttpServletRequest request, @RequestParam String email,
    @RequestParam String password, @RequestParam String name, @RequestParam String phoneNumber) {
        // Check if person exists
        Person person = personService.getPersonByPhoneNumber(phoneNumber);
        if (person != null && person.getName().equals(name)) {
            // Person exists
            try {
                // Check if that person doesn't already have an Account
                if (accountService.getAccountByPersonID(person.getPersonID()) != null) {
                    // Already has an Account
                    return ResponseEntity.badRequest().body("An account is already associated with this person");
                }
                
            } catch (Exception e) {
                // Person exists and doesn't have an Account
            }

            // Person exists and doesn't have an Account
            try {
                accountService.createAccount(email, password, person);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            
        }

        // Person does not exist
        try {
            person = personService.createPerson(name, phoneNumber);
            accountService.createAccount(email, password, person);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Update an existing Account
     * @param request -only staff or customer accessing own account can access this method
     * @param id - accound id
     * @param email - account email address
     * @param password - account password
     * @return error message if encountered
     * @author Lin Wei Li
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(HttpServletRequest request, @PathVariable(value = "id") long id,
    @RequestParam String email, @RequestParam String password) {
        // Check authorization (own account or staff)
        try {
            if (!AuthenticationUtility.isStaff(request) &&
                AuthenticationUtility.getAccountId(request) != id) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        // Authorized
        try {
            accountService.updateAccount(id, email, password);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Delete an existing Account
     * @param request -only staff or customer accessing own account can access this method
     * @param id - account id
     * @return error message if encountered
     * @author Lin Wei Li
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(HttpServletRequest request, @PathVariable(value = "id") long id) {
        // Check authorization (own account or staff)
        try {
            long accountId = AuthenticationUtility.getAccountId(request);
            if (accountId != id && !AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        // Authorized
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get an existing Account
     * @param request - only staff or customer accessing own account can access this method
     * @param id - account id
     * @return error message if encountered
     * @author Lin Wei Li
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAccount(HttpServletRequest request, @PathVariable(value = "id") long id) {
        // Check authorization (own account or staff)
        try {
            long accountId = AuthenticationUtility.getAccountId(request);
            if (accountId != id && !AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        // Authorized
        try {
            Account account = accountService.getAccountByID(id);
            return ResponseEntity.ok().body(DtoUtility.convertToDto(account));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get all existing Accounts
     * @param request -only staff or customer accessing own account can access this method
     * @return error message if encountered
     * @author Lin Wei Li
     */
    @GetMapping("/get")
    public ResponseEntity<?> getAllAccounts(HttpServletRequest request) {
        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        // Authorized
        return ResponseEntity.ok().body(accountService.getAllAccounts().stream().map(DtoUtility::convertToDto).collect(Collectors.toList()));
    }

}
