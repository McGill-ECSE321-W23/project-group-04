package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MonthlyReservationRepository extends CrudRepository<MonthlyReservation, Long> {
    MonthlyReservation getMonthlyReservationByMonthlyReservationID(Long reservationId);

    Person findPersonByMonthlyReservationID(Long reservationId);
    List<MonthlyReservation> findMonthlyReservationByPerson_PersonID(Long personId);
}
