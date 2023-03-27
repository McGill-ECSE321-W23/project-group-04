package ca.mcgill.ecse321.parkinglotbackend.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.TimeSlotRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;

/**
 * @author Qin Xuan Xu
 * using template from tutorials
 */
@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    /**
     * Create a new time slot
     * 
     * @param dayOfTheWeek - day of the week
     * @param startTime - start time
     * @param endTime - end time
     * @param parkingLotSoftwareSystem - system
     * @param staffAccount - staff account
     * @return the created time slot
     * @throws Exception if any of the values are invalid
     */
    @Transactional
    public TimeSlot createTimeSlot(DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime, ParkingLotSoftwareSystem parkingLotSoftwareSystem, StaffAccount staffAccount) throws Exception {
        if (startTime.isBefore(LocalTime.of(0, 0)) || startTime.isAfter(LocalTime.of(23, 59)) || endTime.isBefore(LocalTime.of(0, 0)) || endTime.isAfter(LocalTime.of(23, 59))) {
            throw new Exception("Time must be between 00:00 and 23:59!");
        }
        if (endTime.isBefore(startTime)) {
            throw new Exception("End time cannot be before start time!");
        }
        if (parkingLotSoftwareSystem == null) {
            throw new Exception("System cannot be null!");
        }
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDayOfTheWeek(dayOfTheWeek);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlot.setSystem(parkingLotSoftwareSystem);
        timeSlot.setStaffAccount(staffAccount);
        timeSlotRepository.save(timeSlot);
        return timeSlot;
    }

    /**
     * Get a time slot by id
     * 
     * @param timeSlotID - id of the time slot
     * @return the time slot
     * @throws Exception if no time slot with this id exists
     */
    @Transactional
    public TimeSlot getTimeSlot(long timeSlotID) throws Exception {
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        if (timeSlot == null) {
            throw new Exception("No TimeSlot with this id exists!");
        }
        return timeSlot;
    }

    /**
     * Get all time slots
     * 
     * @return list of all time slots
     */
    @Transactional
    public List<TimeSlot> getAllTimeSlots() {
        return toList(timeSlotRepository.findAll());
    }

    @Transactional
    public List<TimeSlot> getTimeSlotsByAccountID(long accountID) {
        return toList(timeSlotRepository.findByAccountID(accountID));
    }
    
    @Transactional
    public List<TimeSlot> getTimeSlotsByStaffAccount(StaffAccount staffAccount) {
        return toList(timeSlotRepository.findByStaffAccount(staffAccount));
    }

    /**
     * Update a time slot
     * 
     * @param timeSlotID - id of the time slot
     * @param dayOfTheWeek - day of the week
     * @param startTime - start time
     * @param endTime - end time
     * @return the updated time slot
     * @throws Exception if any of the values are invalid
     */
    @Transactional
    public TimeSlot updateTimeSlot(long timeSlotID, DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime) throws Exception {
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        if (timeSlot == null) {
            throw new Exception("No TimeSlot with this id exists!");
        }
        if (startTime.isBefore(LocalTime.of(0, 0)) || startTime.isAfter(LocalTime.of(23, 59)) || endTime.isBefore(LocalTime.of(0, 0)) || endTime.isAfter(LocalTime.of(23, 59))) {
            throw new Exception("Time must be between 00:00 and 23:59!");
        }
        if (endTime.isBefore(startTime)) {
            throw new Exception("End time cannot be before start time!");
        }
        timeSlot.setDayOfTheWeek(dayOfTheWeek);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlotRepository.save(timeSlot);
        return timeSlot;
    }

    /**
     * Delete a time slot
     * 
     * @param timeSlotID - id of the time slot
     * @return the deleted time slot
     * @throws Exception if no time slot with this id exists
     */
    @Transactional
    public TimeSlot deleteTimeSlot(long timeSlotID) throws Exception {
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        if (timeSlot == null) {
            throw new Exception("No TimeSlot with this id exists!");
        }
        timeSlotRepository.delete(timeSlot);
        return timeSlot;
    }

    // Convert Iterable to List
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
