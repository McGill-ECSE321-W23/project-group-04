package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.MonthlyReservationRepository;
import ca.mcgill.ecse321.parkinglotbackend.dto.MonthlyReservationDto;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TestMonthlyReservationService {

    @InjectMocks
    MonthlyReservationService monthlyReservationService;

    @MockBean
    MonthlyReservationRepository monthlyReservationRepository;

    @MockBean
    ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;

    MonthlyReservation monthlyReservation;

    @BeforeEach
    void setup() {
        Person person = new Person("514", "oker");
        monthlyReservation = new MonthlyReservation(
                LocalDate.of(2023, 3, 23),
                LocalDate.of(2023, 4, 23),
                person
        );
    }

    @Test
    void AddReservation_Should_AddInDatabase() {
        when(monthlyReservationRepository.save(any())).thenReturn(monthlyReservation);

        monthlyReservationService.addReservation(
                monthlyReservation.getStartDate(),
                monthlyReservation.getEndDate(),
                monthlyReservation.getPerson()
        );
    }

    @Test
    void DeleteReservation_Should_DeleteInDatabase() {
        doNothing().when(monthlyReservationRepository).delete(any());

        monthlyReservationService.addReservation(
                monthlyReservation.getStartDate(),
                monthlyReservation.getEndDate(),
                monthlyReservation.getPerson()
        );
    }

    @Test
    void RenewPayment_Should_UpdateDate_When_PaymentAmountIsEnough() throws Exception {
        LocalDate date = monthlyReservation.getEndDate();
        when(parkingLotSoftwareSystemService.getParkingLotSoftwareSystem()).thenReturn(new ParkingLotSoftwareSystem());
        when(monthlyReservationRepository.getMonthlyReservationByMonthlyReservationID(any())).thenReturn(monthlyReservation);
        when(monthlyReservationRepository.save(any())).thenReturn(monthlyReservation);

        MonthlyReservationDto monthlyReservationDto = monthlyReservationService.renewPayment(1L, 500);

        assertEquals("date is not updated", date.plusMonths(1), monthlyReservationDto.getEndDate());
    }


}
