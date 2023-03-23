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
import ca.mcgill.ecse321.parkinglotbackend.service.AccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private AccountService accountService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerPerson(HttpServletRequest request,
    @RequestBody String name, @RequestBody String phoneNumber) {
        try {
            personService.createPerson(name, phoneNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePerson(HttpServletRequest request, @PathVariable(value = "id") long id,
    @RequestBody String name, @RequestBody String phoneNumber) {
        // Check authorization (own person or staff)
        try {
            long personID = accountService.getAccountByID(id).getPerson().getPersonID();
            if (personID != id && !AuthenticationUtility.isStaff(request)) {
                // Not authorized
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).body("Not authorized");
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        // Authorized
        try {
            personService.updatePerson(id, name, phoneNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(HttpServletRequest request, @PathVariable(value = "id") long id) {
        // Check authorization (own person or staff)
        try {
            long personID = accountService.getAccountByID(id).getPerson().getPersonID();
            if (personID != id && !AuthenticationUtility.isStaff(request)) {
                // Not authorized
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).body("Not authorized");
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        // Authorized
        try {
            personService.deletePerson(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPerson(HttpServletRequest request, @PathVariable(value = "id") long id) {
        // Check authorization (own person or staff)
        try {
            long personID = accountService.getAccountByID(id).getPerson().getPersonID();
            if (personID != id && !AuthenticationUtility.isStaff(request)) {
                // Not authorized
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).body("Not authorized");
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        // Authorized
        try {
            return ResponseEntity.ok().body(personService.getPersonByName("John"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllPersons(HttpServletRequest request) {
        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                // Not authorized
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).body("Not authorized");
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        // Authorized
        return ResponseEntity.ok().body(personService.getAllPersons());
    }

}
