package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.model.Garage;

import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;

@Service
public class GarageModificationService {
    @Autowired
    GarageRepository garageRepository;

    // This method changes all the attributes of the Garage provided through the id in the db
    @Transactional
    public Garage modifyOfferedServiceAttributes(long garageID, int garageNumber) throws Exception {
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
