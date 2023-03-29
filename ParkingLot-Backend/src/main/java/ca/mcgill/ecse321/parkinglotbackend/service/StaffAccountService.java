package ca.mcgill.ecse321.parkinglotbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;

@Service
public class StaffAccountService {
    @Autowired
    public StaffAccountRepository staffAccountRepository;

    @Transactional
    public StaffAccount getStaffAccount(long id) {
        StaffAccount account = staffAccountRepository.getStaffAccountByAccountID(id);
        return account;
    }
}
