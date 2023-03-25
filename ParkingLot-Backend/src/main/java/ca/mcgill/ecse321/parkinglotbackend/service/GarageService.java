package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.model.Garage;

import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;

@Service
public class GarageService {
    @Autowired
    GarageRepository garageRepository;

    @Transactional
    public Garage createGarageService(int garageNumber) throws Exception {
        // Check that this garage number is not negative or equal to 0
        if (garageNumber <= 0) {
            throw new Exception("Garage number must be positive.");
        }

        // Check that this garage number has not been assigned yet
        for (Garage garage : getAllGarageService()) {
            // If this garage number already exists
            if (garage.getGarageNumber() == garageNumber) {
                throw new Exception("This garage number already exists.");
            }
        }

        // If this garage number doesn't yet exist and is valid
        // Create the garage by adding all the necessary components
        Garage garage = new Garage();
        garage.setGarageNumber(garageNumber);
        garageRepository.save(garage);
        return garage;
    }

    @Transactional
    public Garage deleteGarageService(long garageID) throws Exception {
        // Fetch the garage we want to delete through the id in the db
        Garage garage = garageRepository.findGarageByGarageID(garageID);

        // If we could not find the garage to delete
        if (garage == null) {
            throw new Exception("Garage not found.");
        }

        // If we found the garage to delete
        garageRepository.delete(garage);
        return garage;
    }

    // This method changes all the attributes of the Garage provided through the id in the db
    @Transactional
    public Garage modifyGarage(long garageID, int garageNumber) throws Exception {
        // Fetch the garage we want to edit through the id in the db
        Garage garage = garageRepository.findGarageByGarageID(garageID);

        // If the garage was not found
        if (garage == null) {
            throw new Exception("Garage not found.");
        }

        // Check that this garage number is not negative
        if (garageNumber <= 0) {
            throw new Exception("Garage number must be positive.");
        }

        // Check that this garage number has not been assigned yet
        for (Garage g : getAllGarageService()) {
            // If this garage number already exists
            if (g.getGarageNumber() == garageNumber) {
                throw new Exception("This garage number already exists.");
            }
        }

        // If the service was found
        // Edit the garage by adding all the necessary components
        garage.setGarageNumber(garageNumber);
        garageRepository.save(garage);
        return garage;
    }

    @Transactional
    public Garage getGarageService(long garageID) throws Exception {
        // Fetch the garage we want to delete through the id in the db
        Garage garage = garageRepository.findGarageByGarageID(garageID);

        // If we could not find the garage to delete
        if (garage == null) {
            throw new Exception("Garage not found.");
        }
        return garage;
    }

    @Transactional
    public List<Garage> getAllGarageService() {
        return toList(garageRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
