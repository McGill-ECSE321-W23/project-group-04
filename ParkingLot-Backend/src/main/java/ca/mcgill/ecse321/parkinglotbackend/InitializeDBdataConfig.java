package ca.mcgill.ecse321.parkinglotbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.ManagerAccountRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingLotSoftwareSystemRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingSpotRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import jakarta.annotation.PostConstruct;

@Configuration
public class InitializeDBdataConfig {

    // ParkingLotSoftwareSystem
    private final float MONTHLY_FEE = 100;
    private final float FEE_PER_15M = 5;
    private final int MAX_STAY = 12*60;
    private final int NUMBER_OF_REGULAR_PARKING_SPOTS = 250;
    private final int NUMBER_OF_LARGE_PARKING_SPOTS = 20;
    private final int NUMBER_OF_MONTHLY_FLOORS = 2;
    private final int NUMBER_OF_MONTHLY_SPOTS_PER_FLOOR = 100;
    private final int NUMBER_OF_GARAGES = 5;

    // Manager
    private final String MANAGER_NAME = "manager";
    private final String MANAGER_PHONE = "514-555-6666";
    private final String MANAGER_EMAIL = "manager@pls.ca";
    private final String MANAGER_PASSWORD = "manager";
    private final float MANAGER_SALARY = 100.0f;
    
    @Autowired
    private ParkingLotSoftwareSystemRepository parkingLotSoftwareSystemRepository;

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ManagerAccountRepository managerAccountRepository;

    @PostConstruct
    @Transactional
    public void init() {
        
        // Check if there is any data in the database
        if (parkingLotSoftwareSystemRepository.count() != 0) {
            // If there is data, do not initialize
            return;
        }

        // Create a new ParkingLotSoftwareSystem
        ParkingLotSoftwareSystem parkingLotSoftwareSystem = new ParkingLotSoftwareSystem();
        parkingLotSoftwareSystem.setParkingLotSoftwareSystemID(1);
        parkingLotSoftwareSystem.setMonthlyFee(MONTHLY_FEE);
        parkingLotSoftwareSystem.setFeePer15m(FEE_PER_15M);
        parkingLotSoftwareSystem.setMaxStay(MAX_STAY);
        parkingLotSoftwareSystem.setNumberOfRegularParkingSpots(NUMBER_OF_REGULAR_PARKING_SPOTS);
        parkingLotSoftwareSystem.setNumberOfLargeParkingSpots(NUMBER_OF_LARGE_PARKING_SPOTS);
        parkingLotSoftwareSystem.setNumberOfMonthlyFloors(NUMBER_OF_MONTHLY_FLOORS);
        parkingLotSoftwareSystem.setNumberOfMonthlySpotsPerFloor(NUMBER_OF_MONTHLY_SPOTS_PER_FLOOR);
        parkingLotSoftwareSystem.setNumberOfGarages(NUMBER_OF_GARAGES);

        // Save the ParkingLotSoftwareSystem to the database
        ParkingLotSoftwareSystem savedParkingLotSoftwareSystem = parkingLotSoftwareSystemRepository.save(parkingLotSoftwareSystem);

        // Check if the ParkingLotSoftwareSystem was saved successfully
        if (savedParkingLotSoftwareSystem == null) {
            throw new RuntimeException("Could not initialize data");
        }

        // Create garages
        for(int i = 1; i <= NUMBER_OF_GARAGES; i++) {
            garageRepository.save(new Garage(i, i));
        }

        // Check if the garages were saved successfully
        if (garageRepository.count() != NUMBER_OF_GARAGES) {
            throw new RuntimeException("Could not initialize data");
        }

        // Create parking spots
        for(int floor = 1; floor <= NUMBER_OF_MONTHLY_FLOORS; floor++) {
            for(int spot = 1; spot <= NUMBER_OF_MONTHLY_SPOTS_PER_FLOOR; spot++) {
                parkingSpotRepository.save(new ParkingSpot(floor, spot));
            }
        }

        // Check if the parking spots were saved successfully
        if (parkingSpotRepository.count() != NUMBER_OF_MONTHLY_FLOORS * NUMBER_OF_MONTHLY_SPOTS_PER_FLOOR) {
            throw new RuntimeException("Could not initialize data");
        }

        // Create manager
        Person managerPerson = new Person(MANAGER_NAME, MANAGER_PHONE);
        ManagerAccount managerAccount = new ManagerAccount(MANAGER_EMAIL, MANAGER_PASSWORD, managerPerson, MANAGER_SALARY);
        ManagerAccount savedManagerAccount = managerAccountRepository.save(managerAccount);

        // Check if the manager account was saved successfully
        if (savedManagerAccount == null) {
            throw new RuntimeException("Could not initialize data");
        }

    }

}
