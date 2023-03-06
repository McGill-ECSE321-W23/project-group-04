package ca.mcgill.ecse321.parkinglotbackend.data;

import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlotMockBuilder {
    private TimeSlot timeSlot;

    public static TimeSlotMockBuilder builder() {
        return new TimeSlotMockBuilder();
    }

    public TimeSlot build() {
        timeSlot = new TimeSlot(
                LocalTime.MIN,
                LocalTime.MAX,
                DayOfWeek.FRIDAY
        );
        return timeSlot;
    }
}
