package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.CarRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;
    @Autowired
    PersonRepository personRepository;

    @Transactional
    public Car registerCar(Person person, String licensePlate, String make, String model){

        String error = "";

        if (person == null){
            error += "Owner cannot be empty";
        }

        if (licensePlate == null || licensePlate.trim().length() == 0){
            error += "License plate cannot be empty";
        }

        if (make == null || make.trim().length() == 0){
            error += "Make cannot be empty";
        }
        
        if (model == null || model.trim().length() == 0){
            error += "Model cannot be empty";
        }

        error = error.trim();

        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setMake(make);
        car.setModel(model);
        car.setOwner(person);
        carRepository.save(car);
        return car;
    }

    @Transactional
    public Car getCarByID (long id) throws Exception{
        Car car = carRepository.findCarByCarID(id);
        if (car == null){
            throw new Exception("No car with this ID exists");
        }
        return car;
    }

    @Transactional
    public Car getCarByLicensePlate (String licensePlate) throws Exception{
        Car car = carRepository.findCarByLicensePlate(licensePlate);
        if (car == null){
            throw new Exception("No car with this license exists");
        }
        return car;
    }

    @Transactional
    public List<Car> findCarByOwnerID(Long id) throws Exception{
        if (!personRepository.existsById(id)){
            throw new Exception("No person with this id exists");
        }
        List<Car> carsOfPerson = carRepository.findCarByOwner_PersonID(id);
        return carsOfPerson;
    }


    @Transactional
    public List<Car> getAllCars() {
        return toList(carRepository.findAll());
    }


    @Transactional
    public Car deleteCar(Long id) throws Exception{
        Car car = carRepository.findCarByCarID(id);
        if (car == null){
            throw new Exception("No car with this id exists");
        }
        carRepository.delete(car);
        return car;
    }

    @Transactional
    public Car updateCar(Long carID, String licensePlate, String make, String model, Person person) throws Exception {
        
        String error = "";

        Car car = getCarByID(carID);

        if (car == null){
            error += "No car with this id exists";
        }
        
        if (licensePlate == null || licensePlate.trim().length() == 0) {
            error += ("License Plate cannot be empty");
        }

        if (make == null || make.trim().length() == 0) {
            error += ("Make cannot be empty");
        }

        if (model == null || model.trim().length() == 0) {
            error += ("Model cannot be empty");
        }

        if (person == null){
            error += "Owner cannot be null";
        }

        error = error.trim();

        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }

        car.setLicensePlate(licensePlate);
        car.setMake(make);
        car.setModel(model);
        car.setOwner(person);
        carRepository.save(car);

        return car;
    }

    /**
     * 
     * @param <T>
     * @param iterable
     * @return
     */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
    }


}