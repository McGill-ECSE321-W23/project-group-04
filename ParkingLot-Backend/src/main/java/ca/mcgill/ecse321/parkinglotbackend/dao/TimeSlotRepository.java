package ca.mcgill.ecse321.parkinglotbackend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long>{
    TimeSlot findTimeSlotByTimeSlotID(Long timeSlotID);
    List<TimeSlot> findByAccountID(Long accountID);
    List<TimeSlot> findByStaffAccount(StaffAccount staffAccount);
}
