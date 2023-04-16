package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.CarDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.PersonDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.service.CarService;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars")
public class CarRestController {

    @Autowired
    private CarService service;
    @Autowired
    private PersonService personService;

    /**
     * RESTful API for getting all cars
     * 
     * @return All cars in the system
     * @author anniegouchee
     */
    @GetMapping(value = { "/getAll", "/getAll/" })
    public ResponseEntity<?> getAllCars() {
        return ResponseEntity.ok().body(service.getAllCars().stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
    }

    /**
     * 
     * RESTful API for getting the car for a given license plate
     * 
     * @param licensePlate License plate of the car
     * @return Car that has the given license plate
     * @author anniegouchee
     */
    @GetMapping(value = { "/getByLicensePlate/{licensePlate}", "/getByLicensePlate/{licensePlate}/" })
    public ResponseEntity<?> getCarByLicensePlate(@PathVariable("licensePlate") String licensePlate) {

        try {
            return ResponseEntity.ok().body(convertToDto(service.getCarByLicensePlate(licensePlate)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    /**
     * RESTful API for getting the car for a given car ID
     * 
     * @param id ID of a car
     * @return Car with the given ID
     * @author anniegouchee
     */
    @GetMapping(value = { "/get/{id}", "/get/{id}/" })
    public ResponseEntity<?> getCarByID(@PathVariable("id") Long id) {
        Car c;
        try {
            c = service.getCarByID(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(convertToDto(c));
    }

    /**
     * RESTful API for getting the cars under an owner with a given owner ID
     * 
     * @param id ID of the owner of the car
     * @return List of cars assciated to a person with the given person ID
     * @author anniegouchee
     */
    @GetMapping(value = { "/getByPersonID/{id}", "/getByPersonID/{id}/" })
    public ResponseEntity<?> getCarsByOwner(@PathVariable("id")Long id) {

        try {
            return ResponseEntity.ok().body(service.findCarByOwnerID(id).stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * RESTful API for registering a new car
     * 
     * @param licensePlate License plate of the car to be registered
     * @param make Make of the car to be registered
     * @param model Model of the car to be registered
     * @param person Owner of the car to be registered
     * @return Car that was registered successfully
     * @author anniegouchee
     */
    @PostMapping(value = {"/register", "/register/"})
    public ResponseEntity<?> registerCar(@PathVariable("licensePlate") String licensePlate, String make, String model, PersonDto person) {

        //Gets the owener of the car
        Person p;
        try {
            p = personService.getPersonByID(person.getPersonID());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        //Registers car with given information
        Car c = service.registerCar(p, licensePlate, make, model);
        return ResponseEntity.ok().body(convertToDto(c));

    }

    /**
     * RESTful API for updating the information fo a car
     * 
     * @param id ID of the car whose information needs to be updates
     * @param licensePlate License plate of the updated car 
     * @param make Make of the updated car
     * @param model Model of the updated car
     * @param person Owner of the updated car
     * @return Car with updated information
     * @author anniegouchee
     */
    @PutMapping(value = {"/update/{id}", "/update/{id}/"})
    public ResponseEntity<?> updateCar(@PathVariable("id") Long id, String licensePlate, String make, String model, PersonDto person) {
        Car car;
        try {
            car = service.updateCar(id, licensePlate, make, model, convertToDomainObject(person));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
        return ResponseEntity.ok().body(convertToDto(car));
    }

    /**
     * RESTful API for deleting a car
     * 
     * @param id ID of the car to be deleted
     * @return Car that was deleted
     * @throws Exception There is no car under the given ID
     * @author anniegouchee
     */
    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) throws Exception{
        Car car = service.deleteCar(id);
        return ResponseEntity.ok().body(convertToDto(car));
    }

    /**
     * Helper method that converts a Person class into a PersonDTO
     * @param p Person to be converted into TO
     * @return TO that encapsulates information of the Person
     * @author anniegouchee
     */
    private PersonDto convertToDto(Person p){
        if (p == null) {
            throw new IllegalArgumentException("There is no such Person!");
        }
        PersonDto personDto = new PersonDto();
        return personDto;
    }


    /**
     * Helper method that converts a Car class into a CarDTO
     * @param c Car to be converted into TO
     * @return TO that encapsulates information of the Car
     * @author anniegouchee
     */
    private CarDto convertToDto(Car c) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Car!");
        }
        CarDto carDto = new CarDto(c.getCarID(), c.getLicensePlate(), c.getMake(), c.getModel(), convertToDto(c.getOwner()));
        return carDto;
    }

    /**
     * Helper method that converts a PersonDTO into a Person class
     * 
     * @param pDto Person TO to be converted into Person class
     * @return Person that encapsulates information of the TO
     * @author anniegouchee
     */
    private Person convertToDomainObject(PersonDto pDto) {
        List<Person> allPersons = personService.getAllPersons();
        
        for (Person person : allPersons) {
            if (person.getPersonID() == pDto.getPersonID()) {
                return person;
            }
        }
        return null;
    }
        
}