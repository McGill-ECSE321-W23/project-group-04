package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;

@Service
public class OfferedServiceService {
    @Autowired
    OfferedServiceRepository offeredServiceRepository;

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

    @Transactional
    public OfferedService deleteOfferedServiceService(long serviceID) throws Exception {
        // Fetch the service we want to delete through the id in the db
        OfferedService offeredService = offeredServiceRepository.findOfferedServiceByServiceID(serviceID);

        // If we could not find the service to delete
        if (offeredService == null) {
            throw new Exception("Service not found.");
        }

        // If we found the service to delete
        offeredServiceRepository.delete(offeredService);
        return offeredService;
    }

    // This method changes all the attributes of the OfferedService provided through the id in the db
    @Transactional
    public OfferedService modifyOfferedServiceService(long serviceID, String description, float cost, int duration) throws Exception {
        // Fetch the service we want to edit through the id in the db
        OfferedService offeredService = offeredServiceRepository.findOfferedServiceByServiceID(serviceID);

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

    @Transactional
    public OfferedService getOfferedServiceService(long serviceID) throws Exception {
        // Fetch the service we want through the id in the db
        OfferedService offeredService = offeredServiceRepository.findOfferedServiceByServiceID(serviceID);

        // If we could not find the offered service to delete
        if (offeredService == null) {
            throw new Exception("Service not found.");
        }
        return offeredService;
    }

    @Transactional
    public List<OfferedService> getAllOfferedServiceService() {
        return toList(offeredServiceRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
