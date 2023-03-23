package ca.mcgill.ecse321.parkinglotbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.service.AccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private PersonService personService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(HttpServletRequest request, @RequestBody String email,
    @RequestBody String password, @RequestBody String name, @RequestBody String phoneNumber) {
        // Check if person exists
        Person person = personService.getPersonByName(name);
        if (person != null && person.getPhoneNumber().equals(phoneNumber)) {
            // Person exists
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

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(HttpServletRequest request, @PathVariable(value = "id") long id,
    @RequestBody String email, @RequestBody String password) {
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
            accountService.updateAccount(id, email, password);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
            return ResponseEntity.ok().body(accountService.getAccountByID(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }

}
