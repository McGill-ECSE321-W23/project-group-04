package ca.mcgill.ecse321.parkinglotbackend.data;

import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlotMockBuilder {
    static TimeSlot build() {
        return TimeSlot.builder()
                .startTime(LocalTime.MIN)
                .endTime(LocalTime.MAX)
                .dayOfTheWeek(DayOfWeek.FRIDAY)
                .build();
    }
}
