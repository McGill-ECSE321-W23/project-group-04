package ca.mcgill.ecse321.ParkingLotBackend.service;

import ca.mcgill.ecse321.ParkingLotBackend.config.UserRepository;
import ca.mcgill.ecse321.ParkingLotBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUser(String name) {
        return userRepository.findUserByName(name);
    }
}
