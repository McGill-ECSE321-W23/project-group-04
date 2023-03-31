package ca.mcgill.ecse321.parkinglotbackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.dto.CarDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.PersonDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.service.CarService;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;

@CrossOrigin(origins = "*")
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
    public List<CarDto> getAllCars() {
        return service.getAllCars().stream().map(c -> convertToDto(c)).collect(Collectors.toList());
    }

    /**
     * 
     * RESTful API for getting the car for a given license plate
     * 
     * @param licensePlate License plate of the car
     * @return Car that has the given license plate
     * @throws Exception No car for the given license plate exists
     * @author anniegouchee
     */
    @GetMapping(value = { "/get/{licensePlate}", "/get/{licensePlate}/" })
    public CarDto getCarByLicensePlate(@PathVariable("licensePlate") String licensePlate) throws Exception{
        Car c = service.getCarByLicensePlate(licensePlate);
        return convertToDto(c);
    }


    /**
     * RESTful API for getting the car for a given car ID
     * 
     * @param id ID of a car
     * @return Car with the given ID
     * @throws Exception No car found under a given ID
     * @author anniegouchee
     */
    @GetMapping(value = { "/get/{id}", "/get/{id}/" })
    public CarDto getCarByID(@PathVariable("id") Long id) throws Exception{
        Car c = service.getCarByID(id);
        return convertToDto(c);
    }

    /**
     * RESTful API for getting the cars under an owner with a given owner ID
     * 
     * @param id ID of the owner of the car
     * @return List of cars assciated to a person with the given person ID
     * @throws Exception No person exists with given person ID
     * @author anniegouchee
     */
    @GetMapping(value = { "/get/ByOwner/{id}", "/get/ByOwner/{id}/" })
    public List<CarDto> getCarsByOwner(@PathVariable("id")Long id) throws Exception{
        return service.findCarByOwnerID(id).stream().map(c -> convertToDto(c)).collect(Collectors.toList());
    }

    /**
     * RESTful API for registering a new car
     * 
     * @param licensePlate License plate of the car to be registered
     * @param make Make of the car to be registered
     * @param model Model of the car to be registered
     * @param person Owner of the car to be registered
     * @return Car that was registered successfully
     * @throws Exception Invalis inputs resulting in the car not being able to be registered successfully
     * @author anniegouchee
     */
    @PostMapping(value = {"/register", "/register/"})
    public CarDto registerCar(@PathVariable("licensePlate") String licensePlate, String make, String model, PersonDto person) throws Exception{

        //Gets the owener of the car
       Person p = personService.getPersonByID(person.getPersonID());

       //Registers car with given information
       Car c = service.registerCar(p, licensePlate, make, model);
       return convertToDto(c);

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
     * @throws Exception Invalid inputs resulting in the car not being able to be updates successfully
     * @author anniegouchee
     */
    @PutMapping(value = {"/update/{id}", "/update/{id}/"})
    public CarDto updateCar(@PathVariable("id") Long id, String licensePlate, String make, String model, PersonDto person) throws Exception{
        Car car = service.updateCar(id, licensePlate, make, model, convertToDomainObject(person));
        return convertToDto(car);
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
    public CarDto deleteCar(@PathVariable("id") Long id) throws Exception{
        Car car = service.deleteCar(id);
        return convertToDto(car);
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