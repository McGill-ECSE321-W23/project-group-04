//package ca.mcgill.ecse321.parkinglotbackend.dao;
//
//import ca.mcgill.ecse321.parkinglotbackend.model.Person;
//import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
//import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.Time;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static java.time.DayOfWeek.FRIDAY;
//
//@SpringBootTest
//public class StaffAccountRepositoryTest {
//    @Autowired StaffAccountRepository staffAccountRepository;
//
//    @BeforeEach
//    void setup() {
//        staffAccountRepository.deleteAll();
//    }
//
//    @Test
//    void testPersistAndLoadStaffAccount() {
//        String accountId = "123";
//        float salary = 999;
//        Person person = new Person();
//        person.setName("Jon");
//        person.setPhoneNumber("5554443333");
//        Set<TimeSlot> schedule = Set.of(new TimeSlot("id", FRIDAY, LocalTime.MIN, LocalTime.MAX));
//        StaffAccount account = new StaffAccount("garage@mail.mcgill.ca", "123", person, salary, schedule);
//
//        staffAccountRepository.save(account);
//        StaffAccount retrievedAccount = staffAccountRepository.getStaffAccountBySalary(salary);
//
//        assertNotNull(retrievedAccount);
//        assertEquals(salary, retrievedAccount.getSalary());
//    }
//}
