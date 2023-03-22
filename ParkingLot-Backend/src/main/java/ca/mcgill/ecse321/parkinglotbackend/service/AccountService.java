package ca.mcgill.ecse321.parkinglotbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.parkinglotbackend.dao.AccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Find a person by name
     * @param name
     * @return Person
     * @author Lin Wei Li
     */

}
