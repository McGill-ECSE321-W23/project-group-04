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

    @GetMapping(value = { "/getAll", "/getAll/" })
    public List<CarDto> getAllCars() {
        return service.getAllCars().stream().map(c -> convertToDto(c)).collect(Collectors.toList());
    }

    @GetMapping(value = { "/get/{licensePlate}", "/get/{licensePlate}/" })
    public CarDto getCarByLicensePlate(@PathVariable("licensePlate") String licensePlate) throws Exception{
        Car c = service.getCarByLicensePlate(licensePlate);
        return convertToDto(c);
    }


    @GetMapping(value = { "/get/{id}", "/get/{id}/" })
    public CarDto getCarByID(@PathVariable("id") Long id) throws Exception{
        Car c = service.getCarByID(id);
        return convertToDto(c);
    }


    @GetMapping(value = { "/get/ByOwner/{id}", "/get/ByOwner/{id}/" })
    public List<CarDto> getCarsByOwner(@PathVariable("id")Long id) throws Exception{
        return service.findCarByOwnerID(id).stream().map(c -> convertToDto(c)).collect(Collectors.toList());
    }

    @PostMapping(value = {"/register/{licensePlate}", "/register/{licensePlate}/"})
    public CarDto registerCar(@PathVariable("licensePlate") String licensePlate, String make, String model, PersonDto person) throws Exception{
       Person p = personService.getPersonByID(person.getPersonID());
       Car c = service.registerCar(p, licensePlate, make, model);
       return convertToDto(c);

    }

    @PutMapping(value = {"/update/{id}", "/update/{id}/"})
    public CarDto updateCar(@PathVariable("id") Long id, String licensePlate, String make, String model, PersonDto person) throws Exception{
        Car car = service.updateCar(id, licensePlate, make, model, convertToDomainObject(person));
        return convertToDto(car);
    }


    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public CarDto deleteCar(@PathVariable("id") Long id) throws Exception{
        Car car = service.deleteCar(id);
        return convertToDto(car);
    }

    private PersonDto convertToDto(Person p){
        if (p == null) {
            throw new IllegalArgumentException("There is no such Person!");
        }
        PersonDto personDto = new PersonDto();
        return personDto;
    }


    private CarDto convertToDto(Car c) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Car!");
        }
        CarDto carDto = new CarDto(c.getCarID(), c.getLicensePlate(), c.getMake(), c.getModel(), convertToDto(c.getOwner()));
        return carDto;
    }

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