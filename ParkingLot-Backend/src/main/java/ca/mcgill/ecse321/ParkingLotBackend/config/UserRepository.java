package ca.mcgill.ecse321.ParkingLotBackend.config;

import ca.mcgill.ecse321.ParkingLotBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
}
