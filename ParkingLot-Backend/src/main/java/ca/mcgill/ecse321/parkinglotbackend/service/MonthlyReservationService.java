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

@Service
@Transactional
public class MonthlyReservationService {

    @Autowired
    MonthlyReservationRepository monthlyReservationRepository;

    @Autowired
    ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;

    public MonthlyReservation addReservation(LocalDate startDate, LocalDate endDate, Person person) {
        MonthlyReservation monthlyReservation = new MonthlyReservation(startDate, endDate, person);
        return monthlyReservationRepository.save(monthlyReservation);
    }

    public MonthlyReservation editReservation(Long reservationId, MonthlyReservation monthlyReservation) {
        monthlyReservation.setMonthlyReservationID(reservationId);
        return monthlyReservationRepository.save(monthlyReservation);
    }

    public void deleteReservation(Long reservationId) {
        MonthlyReservation reservation = monthlyReservationRepository.getMonthlyReservationByMonthlyReservationID(reservationId);
        monthlyReservationRepository.delete(reservation);
    }

    public List<MonthlyReservation> getCustomerReservations(Long personId) {
        return monthlyReservationRepository.findMonthlyReservationByPerson_PersonID(personId);
    }

    public List<MonthlyReservation> getAllReservations() {
        return toList(monthlyReservationRepository.findAll());
    }

    public MonthlyReservationDto renewPayment(long reservationId, float paidAmount) throws Exception {
        MonthlyReservation monthlyReservation = monthlyReservationRepository.getMonthlyReservationByMonthlyReservationID(reservationId);
        if (isEnough(paidAmount)) {
            monthlyReservation.setEndDate(monthlyReservation.getEndDate().plusMonths(1));
        }
        return DtoUtility.convertToDto(monthlyReservationRepository.save(monthlyReservation));
    }

    private boolean isEnough(float amount) throws Exception {
        float monthlyPrice = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(1).getMonthlyFee();
        return amount >= monthlyPrice;
    }

    private boolean isTaken() {
        return true;
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