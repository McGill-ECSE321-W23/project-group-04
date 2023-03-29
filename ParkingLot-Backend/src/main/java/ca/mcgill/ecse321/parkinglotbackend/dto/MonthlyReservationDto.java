package ca.mcgill.ecse321.parkinglotbackend.dto;

import java.time.LocalDate;

public class MonthlyReservationDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private Long personId;

    public MonthlyReservationDto(LocalDate startDate, LocalDate endDate, Long personId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.personId = personId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonDto(Long personDto) {
        this.personId = personDto;
    }
}