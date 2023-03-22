package ca.mcgill.ecse321.parkinglotbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.model.Account;
import ca.mcgill.ecse321.parkinglotbackend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody String username, @RequestBody String password) {

        // Check if already logged in
        if (AuthenticationUtility.isLoggedIn(request)) {
            return ResponseEntity.badRequest().body("Already logged in");
        }

        // Attempt to authenticate
        try {
            Account account = authenticationService.authenticate(username, password);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();

    }
    
}
