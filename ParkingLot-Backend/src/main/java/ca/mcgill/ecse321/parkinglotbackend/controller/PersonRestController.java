package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.DtoUtility;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.service.AccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/person")
public class PersonRestController {

    @Autowired
    private PersonService personService;

    @Autowired
    private AccountService accountService;
    
    /**
     * Create a new Person
     * @param request - anyone can access this method
     * @param name - person name
     * @param phoneNumber - phone number of the person
     * @return error message if encountered
     * @author Lin Wei Li
     */
    @PostMapping("/create")
    public ResponseEntity<?> createPerson(HttpServletRequest request,
    @RequestParam String name, @RequestParam String phoneNumber) {
        try {
            personService.createPerson(name, phoneNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Update an existing Person
     * @param request - only staff of customer accessing their own info can call this method
     * @param id - person id
     * @param name - new name
     * @param phoneNumber - new phone number
     * @return error message if encountered
     * @author Lin Wei Li
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePerson(HttpServletRequest request, @PathVariable(value = "id") long id,
    @RequestParam String name, @RequestParam String phoneNumber) {
        // Check authorization (own person or staff)
        try {
            // long accountID = accountService.getAccountByPersonID(id).getAccountID();    // id if the acc of the person to update
            if (!AuthenticationUtility.isStaff(request) &&
                accountService.getAccountByPersonID(id).getAccountID() != AuthenticationUtility.getAccountId(request)) {
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

    /**
     * NOT ALLOWED ANYMORE
     * Delete a Person
     * @param request
     * @param id
     * @return
     * @author Lin Wei Li
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(HttpServletRequest request, @PathVariable(value = "id") long id) {

        return ResponseEntity.badRequest().body("Not allowed to delete a Person");
/*
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
*/
    }

    /**
     * Get a Person by their id
     * @param request - only staff of customer accessing their own info can call this method
     * @param id - person id
     * @return error message if encountered
     * @author Lin Wei Li
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPerson(HttpServletRequest request, @PathVariable(value = "id") long id) {
        try {
            // Get the person
            Person person = personService.getPersonByID(id);
            HttpSession session = request.getSession();
            long sessionID = session.getAttribute("accountID") == null ? -1 : (long) Integer.parseInt(session.getAttribute("accountID").toString());

            // Authorize staff
            if (AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.ok().body(DtoUtility.convertToDto(person));
            }
            // Authorize own person
            else if (accountService.getAccountByPersonID(person.getPersonID()).getAccountID() == sessionID) {
                return ResponseEntity.ok().body(DtoUtility.convertToDto(person));
            }
            // Not authorized
            else {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).body("Not authorized");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get all Persons
     * @param request - only staff can access this method
     * @return error message if encountered
     * @author Lin Wei Li
     */
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
        return ResponseEntity.ok().body(personService.getAllPersons().stream().map(DtoUtility::convertToDto).collect(Collectors.toList()));
    }

}
