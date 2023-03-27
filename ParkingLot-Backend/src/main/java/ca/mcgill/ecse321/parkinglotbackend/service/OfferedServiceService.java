package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;

/**
 * This class implements the Service class for OfferedService.
 *
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_creating_restful_web_services_in_spring
 */
@Service
public class OfferedServiceService {
    @Autowired
    OfferedServiceRepository offeredServiceRepository;

    /**
     * This method allows to create a new offered service and save it to the database.
     *
     * @param description - A description of the service offered (like a name)
     * @param cost - The cost of the offered service
     * @param duration - The duration of the offered service
     * @return - The offered service created
     * @throws Exception - The message indicating why an offered service could not be created, conforming to our design decisions
     * @author Estefania Vazquez
     */
    @Transactional
    public OfferedService createOfferedServiceService(String description, float cost, int duration) throws Exception {
        // If the description was empty
        if (description ==  null || description.trim().isEmpty()) {
            throw new Exception("Description cannot be empty.");
        }

        // If description already exists
        for (OfferedService offeredService : getAllOfferedServiceService()) {
            if (offeredService.getDescription().equals(description)) {
                throw new Exception("Description already exists.");
            }
        }

        // If cost was negative
        if (cost < 0) {
            throw new Exception("Cost cannot be negative.");
        }

        // If duration was negative or 0
        if (duration <= 0) {
            throw new Exception("Duration must be positive.");
        }

        // Create the service by adding all the necessary components, one by one
        OfferedService offeredService = new OfferedService();
        offeredService.setDescription(description);
        offeredService.setCost(cost);
        offeredService.setDuration(duration);
        offeredServiceRepository.save(offeredService);
        return offeredService;
    }

    /**
     * This method allows to delete the offered service with the provided ID and save it to the database.
     *
     * @param offeredServiceID - The unique ID of an offered service
     * @return - The offered service deleted
     * @throws Exception - The message indicating why an offered service could not be deleted
     * @author Estefania Vazquez
     */
    @Transactional
    public OfferedService deleteOfferedServiceService(long offeredServiceID) throws Exception {
        // Fetch the service we want to delete through the id in the db
        OfferedService offeredService = offeredServiceRepository.findOfferedServiceByServiceID(offeredServiceID);

        // If we could not find the service to delete
        if (offeredService == null) {
            throw new Exception("Service not found.");
        }

        // If we found the service to delete
        offeredServiceRepository.delete(offeredService);
        return offeredService;
    }

    /**
     * This method allows to modify the offered service provided by the ID and save the changes to the database.
     *
     * @param offeredServiceID - The unique ID of an offered service
     * @param description - A description of the service offered (like a name)
     * @param cost - The cost of the offered service
     * @param duration - The duration of the offered service
     * @return - The offered service modified
     * @throws Exception - The message indicating why an offered service could not be modified, conforming to our design decisions
     * @author Estefania Vazquez
     */
    @Transactional
    public OfferedService modifyOfferedServiceService(long offeredServiceID, String description, float cost, int duration) throws Exception {
        // Fetch the service we want to edit through the id in the db
        OfferedService offeredService = offeredServiceRepository.findOfferedServiceByServiceID(offeredServiceID);

        // If the service was not found
        if (offeredService == null) {
            throw new Exception("Service not found.");
        }

        // If the description was empty
        if (description ==  null || description.trim().isEmpty()) {
            throw new Exception("Description cannot be empty.");
        }

        // If description already exists
        for (OfferedService os : getAllOfferedServiceService()) {
            if (os.getDescription().equals(description)) {
                throw new Exception("Description already exists.");
            }
        }

        // If cost was negative
        if (cost < 0) {
            throw new Exception("Cost cannot be negative.");
        }

        // If duration was negative or 0
        if (duration <= 0) {
            throw new Exception("Duration must be positive.");
        }

        // If the service was found
        // Edit the service by adding all the necessary components, one by one
        offeredService.setDescription(description);
        offeredService.setCost(cost);
        offeredService.setDuration(duration);
        offeredServiceRepository.save(offeredService);
        return offeredService;
    }

    /**
     * This method allows to get an offered service with the provided ID from the database.
     *
     * @param offeredServiceID - The unique ID of an offered service
     * @return - The offered service with the unique ID provided
     * @throws Exception - The message indicating that the offered service was not found
     * @author Estefania Vazquez
     */
    @Transactional
    public OfferedService getOfferedServiceService(long offeredServiceID) throws Exception {
        // Fetch the service we want through the id in the db
        OfferedService offeredService = offeredServiceRepository.findOfferedServiceByServiceID(offeredServiceID);

        // If we could not find the offered service to delete
        if (offeredService == null) {
            throw new Exception("Service not found.");
        }
        return offeredService;
    }

    /**
     * This method allows to get all the offered services in the database.
     *
     * @return - A list of all the offered services in the database
     * @author Estefania Vazquez
     */
    @Transactional
    public List<OfferedService> getAllOfferedServiceService() {
        return toList(offeredServiceRepository.findAll());
    }

    /**
     * This method allows to convert an Iterable to a List.
     *
     * @param iterable - The Iterable element
     * @return - A list containing all the Iterable elements
     * @param <T> - For any element
     * @author Taken directly from the tutorial notes provided in the link but imported by Estefania Vazquez
     */
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
