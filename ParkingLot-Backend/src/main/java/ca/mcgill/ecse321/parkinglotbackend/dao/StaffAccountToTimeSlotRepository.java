package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccountToTimeSlot;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;

public interface StaffAccountToTimeSlotRepository extends CrudRepository<StaffAccountToTimeSlot, String> {
    StaffAccountToTimeSlot getStaffAccountToTimeSlotByStaffAccount_AccountID(String accountId);

    StaffAccountToTimeSlot getStaffAccountToTimeSlotByTimeSlot_DayOfTheWeek(DayOfWeek dayOfWeek);

    StaffAccountToTimeSlot getStaffAccountToTimeSlotByTimeSlot_TimeSlotID(String timeSlotId);
}
