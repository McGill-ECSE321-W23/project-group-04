package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.DtoUtility;
import ca.mcgill.ecse321.parkinglotbackend.dao.MonthlyReservationRepository;
import ca.mcgill.ecse321.parkinglotbackend.dto.MonthlyReservationDto;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonthlyReservationService {

    @Autowired
    MonthlyReservationRepository monthlyReservationRepository;

    @Autowired
    ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;

    /**
     * method to add a reservation
     * @param startDate - start of reservation
     * @param endDate -end of reservation
     * @param person - person obj
     * @return - created reservation
     * @author Edwin You Zhou
     */
    public MonthlyReservation addReservation(LocalDate startDate, LocalDate endDate, Person person) {
        MonthlyReservation monthlyReservation = new MonthlyReservation(startDate, endDate, person);
        return monthlyReservationRepository.save(monthlyReservation);
    }

    /**
     * delete a reservation
     * @param reservationId - unique ID of the reservation
     * @author Edwin You Zhou
     */
    public void deleteReservation(Long reservationId) {
        MonthlyReservation reservation = monthlyReservationRepository.getMonthlyReservationByMonthlyReservationID(reservationId);
        monthlyReservationRepository.delete(reservation);
    }

    /**
     * get a reservation by ID
     * @param reservationId - unique ID of the reservation
     * @return found Reservation
     * @author Edwin You Zhou
     */
    public Optional<MonthlyReservation> getReservationById(Long reservationId) {
        return monthlyReservationRepository.findById(reservationId);
    }

    /**
     * get all the resrvations of a customer
     * @param personId - unique id of the customer
     * @return - list of reservations
     * @author Edwin You Zhou
     */
    public List<MonthlyReservation> getCustomerReservations(Long personId) {
        return monthlyReservationRepository.findMonthlyReservationByPerson_PersonID(personId);
    }

    /**
     * get a list of all reservations
     * @return list of all reservations
     * @author Edwin You Zhou
     */
    public List<MonthlyReservation> getAllReservations() {
        return toList(monthlyReservationRepository.findAll());
    }

    /**
     * increments the expiry date by 1 month if payment amount is enough
      * @param reservationId - unique id of a reservation
     * @param paidAmount - amount paid
     * @return Reservation TO
     * @throws Exception
     * @author Edwin You Zhou
     */
    public MonthlyReservationDto renewPayment(long reservationId, float paidAmount) throws Exception {
        MonthlyReservation monthlyReservation = monthlyReservationRepository.getMonthlyReservationByMonthlyReservationID(reservationId);
        if (isEnough(paidAmount)) {
            monthlyReservation.setEndDate(monthlyReservation.getEndDate().plusMonths(1));
        }
        return DtoUtility.convertToDto(monthlyReservationRepository.save(monthlyReservation));
    }

    /**
     * check if payment amount is larger than monthly fee
     * @param amount - amount paid
     * @return if payment is success
     * @throws Exception
     * @author Edwin You Zhou
     */
    private boolean isEnough(float amount) throws Exception {
        float monthlyPrice = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem().getMonthlyFee();
        return amount >= monthlyPrice;
    }

    // Convert Iterable to List

    /**
     * Helper method to make a list of objects
     * @param iterable
     * @param <T>
     * @return List object
     * @author Edwin You Zhou
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}