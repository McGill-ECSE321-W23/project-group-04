package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.CarRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    /**
     * Create/ register a car under a person
     * @param person Owner of the car
     * @param licensePlate Car license plate number
     * @param make Car make
     * @param model Car model
     * @return The car being registered
     * @author anniegouchee
     */
    @Transactional
    public Car registerCar(Person person, String licensePlate, String make, String model){

        String error = "";

        //Checks if parameters are correct
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

    /**
     * Gets the car given the car ID
     * @param id ID of the car
     * @return Car of given ID
     * @throws Exception No car exists with this ID
     * @author anniegouchee
     */
    @Transactional
    public Car getCarByID (long id) throws Exception{
        Car car = carRepository.findCarByCarID(id);
        if (car == null){
            throw new Exception("No car with this ID exists");
        }
        return car;
    }

    /**
     * Gets the car given the license plate
     * @param licensePlate License plate of the car
     * @return Car of given license plate
     * @throws Exception No car exists with this license plate
     * @author anniegouchee
     */
    @Transactional
    public Car getCarByLicensePlate (String licensePlate) throws Exception{
        Car car = carRepository.findCarByLicensePlate(licensePlate);
        if (car == null){
            throw new Exception("No car with this license exists");
        }
        return car;
    }

    /**
     * Gets a list of cars belonging to a person
     * @param id Id of the car owner
     * @return List of cars under the given owner
     * @throws Exception No person exists under given id 
     * @author anniegouchee
     */
    @Transactional
    public List<Car> findCarByOwnerID(Long id) throws Exception{
        List<Car> carsOfPerson = carRepository.findCarByOwner_PersonID(id);
        if (carsOfPerson == null || carsOfPerson.isEmpty()){
            throw new Exception("No person with this ID exists");
        }
        return carsOfPerson;
    }


    /**
     * Gets list of all cars
     * @return List of all cars
     * @auhor anniegouchee
     */
    @Transactional
    public List<Car> getAllCars() {
        return toList(carRepository.findAll());
    }


    /**
     * Deletes a car given id
     * @param id ID of the car
     * @return Car to be deleted
     * @throws Exception No car exists with given ID
     * @author anniegouchee
     */
    @Transactional
    public Car deleteCar(Long id) throws Exception{
        Car car = carRepository.findCarByCarID(id);
        if (car == null){
            throw new Exception("No car with this ID exists");
        }
        carRepository.delete(car);
        return car;
    }

    /**
     * Updates car given information
     * @param carID ID of car to be updated
     * @param licensePlate License plate of car to be updated
     * @param make Make of car to be updates
     * @param model Model of car to be updated
     * @param person Owner of car to be updated
     * @return Updated car
     * @throws Exception No car exists under the given ID
     */
    @Transactional
    public Car updateCar(Long carID, String licensePlate, String make, String model, Person person) throws Exception {
        
        String error = "";

        Car car = null;

        if (carID == null) {
            error += "No car with this ID exists";
            throw new IllegalArgumentException(error);
        } else {

            car = getCarByID(carID);

            // Checks if the parameters for update are valid
            if (car == null){
                error += "No car with this ID exists";
            }
            
            if (licensePlate == null || licensePlate.trim().length() == 0) {
                error += ("License plate cannot be empty");
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
        }

        return car;
    }

    /**
     * Helper method 
     * @param <T>
     * @param iterable
     * @return List
     */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
    }


}
