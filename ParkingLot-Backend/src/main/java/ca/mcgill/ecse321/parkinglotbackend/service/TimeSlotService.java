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
     * @author Qin Xuan Xu
     */
    @Transactional
    public TimeSlot createTimeSlot(DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime, ParkingLotSoftwareSystem parkingLotSoftwareSystem, StaffAccount staffAccount) throws Exception {
        if (endTime.isBefore(startTime)) {
            throw new Exception("End time cannot be before start time!");
        }
        if ((parkingLotSoftwareSystem == null && staffAccount == null) || (parkingLotSoftwareSystem != null && staffAccount != null)) {
            throw new Exception("TimeSlot must be associated with either a ParkingLotSoftwareSystem or a StaffAccount!");
        }
        if (staffAccount == null && parkingLotSoftwareSystem != null) { // creating open hours
            for (TimeSlot timeSlot : getAllOpenHours()) {
                if (parkingLotSoftwareSystem.getParkingLotSoftwareSystemID() == timeSlot.getSystem().getParkingLotSoftwareSystemID() && dayOfTheWeek == timeSlot.getDayOfTheWeek() && ((startTime.isAfter(timeSlot.getStartTime()) && startTime.isBefore(timeSlot.getEndTime())) || (endTime.isAfter(timeSlot.getStartTime()) && endTime.isBefore(timeSlot.getEndTime())) || (startTime.isBefore(timeSlot.getStartTime()) && endTime.isAfter(timeSlot.getEndTime())))) {
                    throw new Exception("TimeSlot overlaps with existing TimeSlot for open hours!");
                }
            }
        } else { // creating staff hours
            for (TimeSlot timeSlot : getTimeSlotsByStaffAccountID(staffAccount.getAccountID())) {
                if (dayOfTheWeek == timeSlot.getDayOfTheWeek() && ((startTime.isAfter(timeSlot.getStartTime()) && startTime.isBefore(timeSlot.getEndTime())) || (endTime.isAfter(timeSlot.getStartTime()) && endTime.isBefore(timeSlot.getEndTime())) || (startTime.isBefore(timeSlot.getStartTime()) && endTime.isAfter(timeSlot.getEndTime())))) {
                    throw new Exception("TimeSlot overlaps with existing TimeSlot for this staff!");
                }
            }
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
     * @author Qin Xuan Xu
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
     * @author Qin Xuan Xu
     */
    @Transactional
    public List<TimeSlot> getAllTimeSlots() {
        return toList(timeSlotRepository.findAll());
    }

    /**
     * Get all time slots by staff account id
     * 
     * @param accountID - id of the staff account
     * @return list of all time slots with the associated staff id
     * @throws Exception if no time slot with this id exists
     * @author Qin Xuan Xu
     */
    @Transactional
    public List<TimeSlot> getTimeSlotsByStaffAccountID(long accountID) throws Exception {
        List<TimeSlot> list = timeSlotRepository.findTimeSlotByStaffAccountAccountID(accountID);
        if (list == null) {
            throw new Exception("No StaffAccount with this id exists!");
        }
        return list;
    }
    
    /**
     * Get all open hours
     * 
     * @return list of all time slots with null staff account
     * @author Qin Xuan Xu
     */
    @Transactional
    public List<TimeSlot> getAllOpenHours() {
        return timeSlotRepository.findTimeSlotByStaffAccount(null);
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
     * @author Qin Xuan Xu
     */
    @Transactional
    public TimeSlot updateTimeSlot(long timeSlotID, DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime) throws Exception {
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        if (timeSlot == null) {
            throw new Exception("No TimeSlot with this id exists!");
        }
        if (endTime.isBefore(startTime)) {
            throw new Exception("End time cannot be before start time!");
        }
        if (timeSlot.getStaffAccount() == null) { // creating open hours
            for (TimeSlot t : getAllOpenHours()) {
                if (timeSlot.getSystem().getParkingLotSoftwareSystemID() == t.getSystem().getParkingLotSoftwareSystemID() && dayOfTheWeek == t.getDayOfTheWeek() && timeSlotID != t.getTimeSlotID() && ((startTime.isAfter(t.getStartTime()) && startTime.isBefore(t.getEndTime())) || (endTime.isAfter(t.getStartTime()) && endTime.isBefore(t.getEndTime())) || (startTime.isBefore(t.getStartTime()) && endTime.isAfter(t.getEndTime())))) {
                    throw new Exception("TimeSlot overlaps with existing TimeSlot for open hours!");
                }
            }
        } else { // creating staff hours
            for (TimeSlot t : getTimeSlotsByStaffAccountID(timeSlot.getStaffAccount().getAccountID())) {
                if (dayOfTheWeek == t.getDayOfTheWeek() && timeSlotID != t.getTimeSlotID() && ((startTime.isAfter(t.getStartTime()) && startTime.isBefore(t.getEndTime())) || (endTime.isAfter(t.getStartTime()) && endTime.isBefore(t.getEndTime())) || (startTime.isBefore(t.getStartTime()) && endTime.isAfter(t.getEndTime())))) {
                    throw new Exception("TimeSlot overlaps with existing TimeSlot for this staff!");
                }
            }
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
     * @author Qin Xuan Xu
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

    /**
     * helper method to convert to list of objects
     * @param iterable
     * @param <T>
     * @return list of objects
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
