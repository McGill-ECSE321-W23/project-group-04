package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import org.springframework.data.repository.CrudRepository;

public interface ManagerAccountRepository extends CrudRepository<ManagerAccount, String> {
    ManagerAccount getManagerAccountByAccountIDIsNotNull();
}
