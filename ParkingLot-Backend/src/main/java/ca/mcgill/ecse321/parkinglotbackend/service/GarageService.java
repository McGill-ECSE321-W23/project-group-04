package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.model.Garage;

import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;

/**
 * This class implements the Service class for Garage.
 *
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_creating_restful_web_services_in_spring
 */
@Service
public class GarageService {
    @Autowired
    GarageRepository garageRepository;

    /**
     * This method allows to create a new garage and save it to the database.
     *
     * @param garageNumber - The number (like a name) assigned to the garage
     * @return - The garage created
     * @throws Exception - The message indicating why a garage could not be created, conforming to our design decisions
     * @author Estefania Vazquez
     */
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

    /**
     * This method allows to delete the garage with the provided ID and save it to the database.
     *
     * @param garageID - The unique ID of a garage
     * @return - The garage deleted
     * @throws Exception - The message indicating why a garage could not be deleted
     */
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

    /**
     * This method allows to modify the garage provided by the ID and save the changes to the database.
     *
     * @param garageID - The unique ID of a garage
     * @param garageNumber - The number (like a name) assigned to the garage
     * @return - The garage modified
     * @throws Exception - The message indicating why a garage could not be modified, conforming to our design decisions
     * @author Estefania Vazquez
     */
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

    /**
     * This method allows to get a garage with the provided ID from the database.
     *
     * @param garageID - The unique ID of a garage
     * @return - The garage with the unique ID provided
     * @throws Exception - The message indicating that the garage was not found
     * @author Estefania Vazquez
     */
    @Transactional
    public Garage getGarageService(long garageID) throws Exception {
        // Fetch the garage we want to delete through the id in the db
        Garage garage = garageRepository.findGarageByGarageID(garageID);

        // If we could not find the garage to delete
        if (garage == null) {
            throw new Exception("Garage not found.");
        }

        // If the garage was found
        return garage;
    }

    /**
     * This method allows to get all the garages in the database.
     *
     * @return - A list of all the garages in the database
     * @author Estefania Vazquez
     */
    @Transactional
    public List<Garage> getAllGarageService() {
        return toList(garageRepository.findAll());
    }

    /**
     * This method allows to convert an Iterable to a List.
     *
     * @param iterable - The Iterable element
     * @return - A list containing all the Iterable elements
     * @param <T> - For any element
     * @author Taken directly from the tutorial notes provided in the link
     */
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
