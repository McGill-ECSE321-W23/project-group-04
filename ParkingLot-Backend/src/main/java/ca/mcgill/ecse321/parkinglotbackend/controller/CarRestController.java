package ca.mcgill.ecse321.parkinglotbackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.boot.model.relational.Sequence.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import ca.mcgill.ecse321.parkinglotbackend.dto.CarDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.PersonDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.service.CarService;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class CarRestController {

    @Autowired
    private CarService service;
    @Autowired
    private PersonService personService;

    @GetMapping(value = { "/cars", "/cars/" })
    public List<CarDto> getAllCars() {
        return service.getAllCars().stream().map(c -> convertToDto(c, c.getOwner())).collect(Collectors.toList());
    }

    @GetMapping(value = { "/cars/{licensePlate}", "/cars/{licensePlate}/" })
    public CarDto getCarByLicensePlate(@PathVariable("licensePlate") String licensePlate) throws Exception{
        Car c = service.getCarByLicensePlate(licensePlate);
        return convertToDto(c, c.getOwner());
    }

    @GetMapping(value = { "/cars/{licensePlate}", "/cars/{licensePlate}/" })
    public List<CarDto> getCarsByOwner(Long id){
        return service.findCarByOwnerID(id).stream().map(c -> convertToDto(c, c.getOwner())).collect(Collectors.toList());
    }


    @PostMapping(value = "/register/cars/{licensePLate}, /register/cars/{licensePlate}/")
    public CarDto registerCarUnderAccount(@PathVariable("licensePlate") String licensePlate, String make, String model, PersonDto person){
       Person p = service.findPersonByID(person.getID());
       Car c = service.registerCar(p, licensePlate, make, model);
        return convertToDto(c, p);

    }

    @PostMapping(value = "/register/cars/{name}, /register/cars/{name}/")
    public CarDto createCar(@PathVariable("name") String name, String phoneNumber, String licensePlate, String make, String model){
        Car car = service.createCar(name, phoneNumber, licensePlate, make, model);
        return convertToDto(car, car.getOwner());
    }

    @PutMapping("/update/{id}, /update/{id}/")
    public CarDto updateCar(@PathVariable("id") Long id, String licensePlate, String make, String model, PersonDto person) throws Exception{
        Car car = service.updateCar(id, licensePlate, make, model, convertToDomainObject(person));
        return convertToDto(car, car.getOwner()ss)
    }


    @DeleteMapping("/delete/{licensePlate}, /delete/{licensePlate}/")
    public CarDto deleteCar(@PathVariable("licensePlate") String licensePlate){
        Car car = service.deleteCar(licensePlate);
        return convertToDto(car, car.getOwner());
    }

    private PersonDto convertToDto(Person p){
        if (p == null) {
            throw new IllegalArgumentException("There is no such Person!");
        }
        PersonDto personDto = new PersonDto();
        return personDto;
    }


    private CarDto convertToDto(Car c, Person p) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Car!");
        }
        PersonDto person = convertToDto(p);
        CarDto carDto = new CarDto(c.getCarID(), c.getLicensePlate(), c.getMake(), c.getModel(), person);
        return carDto;
    }

    private Person convertToDomainObject(PersonDto pDto) {
        List<Person> allPersons = personService.listAllPersons();
        
        for (Person person : allPersons) {
            if (person.getPersonID().equals(pDto.getPersonID())) {
                return person;
            }
        }
        return null;
    }
        
}