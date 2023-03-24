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

@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Transactional
    public TimeSlot createTimeSlot(DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime,
            ParkingLotSoftwareSystem parkingLotSoftwareSystem, StaffAccount staffAccount) {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDayOfTheWeek(dayOfTheWeek);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlot.setSystem(parkingLotSoftwareSystem);
        timeSlot.setStaffAccount(staffAccount);
        timeSlotRepository.save(timeSlot);
        return timeSlot;
    }

    @Transactional
    public TimeSlot getTimeSlot(long timeSlotID) {
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        return timeSlot;
    }

    @Transactional
    public List<TimeSlot> getAllTimeSlots() {
        return toList(timeSlotRepository.findAll());
    }

    @Transactional
    public TimeSlot updateTimeSlot(long timeSlotID, DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime) throws Exception {
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        if (timeSlot == null) {
            throw new Exception("No account with this id exists!");
        }
        timeSlot.setDayOfTheWeek(dayOfTheWeek);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlotRepository.save(timeSlot);
        return timeSlot;
    }

    @Transactional
    public TimeSlot deleteTimeSlot(long timeSlotID) throws Exception {
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotID(timeSlotID);
        if (timeSlot == null) {
            throw new Exception("No account with this id exists!");
        }
        timeSlotRepository.delete(timeSlot);
        return timeSlot;
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
