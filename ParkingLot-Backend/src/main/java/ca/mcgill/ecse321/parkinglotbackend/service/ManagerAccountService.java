package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ManagerAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManagerAccountService {
    @Autowired
    ManagerAccountRepository managerAccountRepository;

    public ManagerAccount getManagerAccount() {
        return managerAccountRepository.getManagerAccountByAccountIDIsNotNull();
    }
}