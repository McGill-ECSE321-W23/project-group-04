//package ca.mcgill.ecse321.parkinglotbackend.dao;
//
//import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
//import ca.mcgill.ecse321.parkinglotbackend.model.Person;
//import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Set;
//
//import static java.time.DayOfWeek.FRIDAY;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class ManagerAccountRepositoryTest {
//    @Autowired ManagerAccountRepository managerAccountRepository;
//
//    @BeforeEach
//    void setup() {
//        managerAccountRepository.deleteAll();
//    }
//
//    @Test
//    void testPersistAndLoadManagerAccount() {
//        String accountId = "123";
//        Person person = new Person();
//        person.setName("Jon");
//        person.setPhoneNumber("5554443333");
//        Set<TimeSlot> schedule = Set.of(TimeSlot.builder().build());
//        ManagerAccount account = new ManagerAccount("garage@mail.mcgill.ca", "123", person, 999, schedule);
//
//        managerAccountRepository.save(account);
//        ManagerAccount retrievedAccount = managerAccountRepository.getManagerAccountByAccountIDIsNotNull();
//
//        assertNotNull(retrievedAccount);
//    }
//}
