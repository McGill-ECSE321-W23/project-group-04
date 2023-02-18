package ca.mcgill.ecse321.parkinglotbackend;

import ca.mcgill.ecse321.parkinglotbackend.model.User;
import ca.mcgill.ecse321.parkinglotbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public void addUser(@RequestParam String username) {
        User user = new User();
        user.setName(username);
        userService.saveUser(user);
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String username) {
        return userService.getUser(username);
    }
}
