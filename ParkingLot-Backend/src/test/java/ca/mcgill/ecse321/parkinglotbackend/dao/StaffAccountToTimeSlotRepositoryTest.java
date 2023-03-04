package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccountToTimeSlot;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static java.time.DayOfWeek.FRIDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StaffAccountToTimeSlotRepositoryTest {
    @Autowired StaffAccountToTimeSlotRepository staffAccountToTimeSlotRepository;

    @BeforeEach
    void setup() {
        staffAccountToTimeSlotRepository.deleteAll();
    }

    @Test
    void testPersistAndLoadStaffAccountToTimeSlot() {
        String accountId = "123";
        float salary = 1;
        Person person = new Person("11", "5554443333", "Jon");
        TimeSlot timeSlot = new TimeSlot("11", FRIDAY, LocalTime.MIN, LocalTime.MAX);
        StaffAccount account = new StaffAccount(accountId, "garage@mail.mcgill.ca", "123", person, salary);
        StaffAccountToTimeSlot staffAccountToTimeSlot = new StaffAccountToTimeSlot(account, timeSlot);

        staffAccountToTimeSlotRepository.save(staffAccountToTimeSlot);
        StaffAccountToTimeSlot retrievedAccount = staffAccountToTimeSlotRepository.getStaffAccountToTimeSlotByStaffAccount_AccountID(accountId);

        assertNotNull(retrievedAccount);
        assertEquals(accountId, retrievedAccount.getStaffAccount().getAccountID());
    }
}
