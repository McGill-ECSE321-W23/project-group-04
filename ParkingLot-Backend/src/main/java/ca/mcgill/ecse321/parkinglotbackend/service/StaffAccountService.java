package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffAccountService {
    @Autowired
    StaffAccountRepository staffAccountRepository;

    StaffAccount findAccountById(Long id) {
        return staffAccountRepository.getStaffAccountByAccountID(id);
    }
}
