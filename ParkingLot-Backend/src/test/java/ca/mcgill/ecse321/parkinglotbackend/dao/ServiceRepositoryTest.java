package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ServiceRepositoryTest {
	@Autowired
	private ServiceRepository serviceRepository;

	// clear database after testing
	@AfterEach
	public void clearDatabase() {
		serviceRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadService() {
		// Create object
		Service changeTire = new Service();
		changeTire.setServiceID((long)1234);
		String description = "Change the 4 tires of you car";
		float cost = 50;
		int duration = 2;
		changeTire.setDescription(description);
		changeTire.setCost(cost);
		changeTire.setDuration(duration);
		
		// Save service object to database
		Service changeTireDB = serviceRepository.save(changeTire);
		Long id = changeTireDB.getServiceID();
		
		// Read service object from database
<<<<<<< Updated upstream:ParkingLot-Backend/src/test/java/ca/mcgill/ecse321/parkinglotbackend/dao/ServiceRepositoryTest.java
		Service changeTireTest = serviceRepository.findServiceByServiceID(id);
=======
		OfferedService changeTireTest = serviceRepository.findOfferedServiceByServiceID(id);
>>>>>>> Stashed changes:ParkingLot-Backend/src/test/java/ca/mcgill/ecse321/parkinglotbackend/dao/OfferedServiceRepositoryTest.java
		
		// Assert that service object has the correct attributes
		assertNotNull(changeTireTest);
		assertEquals(id, changeTireTest.getServiceID());
		assertEquals(description, changeTireTest.getDescription());
		assertEquals(cost, changeTireTest.getCost());
		assertEquals(duration, changeTireTest.getDuration());
	}
}