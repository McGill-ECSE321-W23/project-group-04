package ca.mcgill.ecse321.parkinglotbackend.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlotDto {
    //TimeSlot Attributes
    private long timeSlotID;
    private DayOfWeek dayOfTheWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    //TimeSlot Associations
    private long systemId;
    private long staffAccountId;

    public TimeSlotDto() {
    }

    public TimeSlotDto(long timeSlotID, DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime, long system, long staffAccountId) {
        this.timeSlotID = timeSlotID;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.systemId = system;
        this.staffAccountId = staffAccountId;
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

    public long getSystemId() {
        return systemId;
    }

    public long getStaffAccount() {
        return staffAccountId;
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

    public void setSystemId(long systemId) {
        this.systemId = systemId;
    }

    public void setStaffAccount(long staffAccountId) {
        this.staffAccountId = staffAccountId;
    }
    
}
