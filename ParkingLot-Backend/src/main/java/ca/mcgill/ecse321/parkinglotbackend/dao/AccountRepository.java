package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    boolean existsAccountByAccountID(Long accountId);
    Account findAccountByAccountID(Long accountId);
    boolean existsAccountByEmail(String email);
    Account findAccountByEmail(String email);
    boolean existsAccountByEmailAndPassword(String email, String password);
    Account findAccountByEmailAndPassword(String email, String password);
}
