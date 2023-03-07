package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GarageRepositoryTest {
	@Autowired
	private GarageRepository garageRepository;

	// clear database after testing
	@AfterEach 
	public void clearDatabase() {
		garageRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadGarage() {
		// Create garage object
		String garageId = "A";	// Create the unique id of the garage
		int garageNum = 1;	// Create the garage number
		Garage garageA = new Garage();	// Create the test garage
		garageA.setGarageID(garageId);
		garageA.setGarageNumber(garageNum);
		
		// Save garage object to database
		Garage garageADB = garageRepository.save(garageA);
		String garageADBId = garageADB.getGarageID();
		
		// Read garage object from database
		Garage garageATest = garageRepository.findGarageByGarageID(garageADBId);
		
		// Assert that the garage object taken from the database has the correct attributes 
		assertNotNull(garageATest);
		assertEquals(garageId, garageATest.getGarageID());
		assertEquals(garageNum, garageATest.getGarageNumber());

	}
	
}
