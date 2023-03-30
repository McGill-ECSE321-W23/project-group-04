package ca.mcgill.ecse321.parkinglotbackend.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;

public class TimeSlotDto {
    //TimeSlot Attributes
    private long timeSlotID;
    private DayOfWeek dayOfTheWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    //TimeSlot Associations
    private ParkingLotSoftwareSystem system;
    private StaffAccount staffAccount;

    public TimeSlotDto() {
    }

    public TimeSlotDto(long timeSlotID, DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime, ParkingLotSoftwareSystem system, StaffAccount staffAccount) {
        this.timeSlotID = timeSlotID;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.system = system;
        this.staffAccount = staffAccount;
    }

    public long getTimeSlotID() {
        return timeSlotID;
    }

    public DayOfWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public ParkingLotSoftwareSystem getSystem() {
        return system;
    }

    public StaffAccount getStaffAccount() {
        return staffAccount;
    }

    public void setTimeSlotID(long timeSlotID) {
        this.timeSlotID = timeSlotID;
    }

    public void setDayOfTheWeek(DayOfWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setSystem(ParkingLotSoftwareSystem system) {
        this.system = system;
    }

    public void setStaffAccount(StaffAccount staffAccount) {
        this.staffAccount = staffAccount;
    }
    
}
