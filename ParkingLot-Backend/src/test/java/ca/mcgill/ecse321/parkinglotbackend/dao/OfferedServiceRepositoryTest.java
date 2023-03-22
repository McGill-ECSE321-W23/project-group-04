package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OfferedServiceRepositoryTest {
	@Autowired
	private OfferedServiceRepository serviceRepository;

	// clear database after testing
	@AfterEach
	public void clearDatabase() {
		serviceRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadService() {
		// Create object
		OfferedService changeTire = new OfferedService();
		changeTire.setServiceID((long)1234);
		String description = "Change the 4 tires of you car";
		float cost = 50;
		int duration = 2;
		changeTire.setDescription(description);
		changeTire.setCost(cost);
		changeTire.setDuration(duration);
		
		// Save service object to database
		OfferedService changeTireDB = serviceRepository.save(changeTire);
		Long id = changeTireDB.getServiceID();
		
		// Read service object from database
		OfferedService changeTireTest = serviceRepository.findOfferedServiceByServiceID(id);
		
		// Assert that service object has the correct attributes
		assertNotNull(changeTireTest);
		assertEquals(id, changeTireTest.getServiceID());
		assertEquals(description, changeTireTest.getDescription());
		assertEquals(cost, changeTireTest.getCost());
		assertEquals(duration, changeTireTest.getDuration());
	}
}