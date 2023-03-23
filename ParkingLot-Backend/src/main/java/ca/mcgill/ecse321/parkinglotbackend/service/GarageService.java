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
    public Garage createGarageService(int garageNumber) {
        // Create the garage by adding all the necessary components
        Garage garage = new Garage();
        garage.setGarageNumber(garageNumber);
        garageRepository.save(garage);
        return garage;
    }

    @Transactional
    public Garage deleteGarageService(long garageID) throws Exception {
        // Fetch the garage we want to delete through the id in the db
        Garage garageService = garageRepository.findGarageByGarageID(garageID);

        // If we could not find the garage to delete
        if (garageService == null) {
            throw new Exception("Garage not found.");
        }

        // If we found the garage to delete
        else {
            garageRepository.delete(garageService);
            return garageService;
        }
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

        // If the service was found
        else {
            // Edit the garage by adding all the necessary components
            garage.setGarageNumber(garageNumber);
            garageRepository.save(garage);
            return garage;
        }
    }

    @Transactional
    public Garage getGarageService(long garageID) {
        // Fetch the garage we want through the id in the db
        Garage garage = garageRepository.findGarageByGarageID(garageID);
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
