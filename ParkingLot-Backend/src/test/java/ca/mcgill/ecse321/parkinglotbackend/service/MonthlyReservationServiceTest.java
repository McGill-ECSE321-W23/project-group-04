package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.MonthlyReservationRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MonthlyReservationServiceTest {

    @InjectMocks
    MonthlyReservationService monthlyReservationService;

    @MockBean
    MonthlyReservationRepository monthlyReservationRepository;

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
}
