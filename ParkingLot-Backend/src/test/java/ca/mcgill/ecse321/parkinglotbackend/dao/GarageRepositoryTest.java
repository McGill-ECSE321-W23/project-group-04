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
	
	@AfterEach 
	public void clearDatabase() {
		garageRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadGarage() {
		// Create garage object
		int garageNum = 1;	// Create the garage number
		Garage garage = new Garage();	// Create the test garage
		garage.setGarageNumber(garageNum);
		
		// Save garage object to database
		Garage savedGarage = garageRepository.save(garage);

		Long garageID = savedGarage.getGarageID();


		// Read garage object from database
		Garage retrievedGarage = garageRepository.findGarageByGarageID(garageID);
		
		// Assert that the garage object taken from the database has the correct attributes 
		assertNotNull(retrievedGarage);
		assertEquals(garageID, retrievedGarage.getGarageID());
		assertEquals(garageNum, retrievedGarage.getGarageNumber());

	}
	
}