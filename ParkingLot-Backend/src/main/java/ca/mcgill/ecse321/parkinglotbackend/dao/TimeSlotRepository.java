package ca.mcgill.ecse321.parkinglotbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long>{
    TimeSlot findTimeSlotByTimeSlotID(Long timeSlotID);
}
