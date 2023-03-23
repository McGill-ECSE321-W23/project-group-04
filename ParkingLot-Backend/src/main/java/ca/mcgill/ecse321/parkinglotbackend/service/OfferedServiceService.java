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
        if (description ==  null || description.isEmpty()) {
            throw new Exception("Description cannot be empty.");
        }

        // Create the service by adding all the necessary components, one by one
        else {
            OfferedService offeredService = new OfferedService();
            offeredService.setDescription(description);
            offeredService.setCost(cost);
            offeredService.setDuration(duration);
            offeredServiceRepository.save(offeredService);
            return offeredService;
        }
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
        else {
            offeredServiceRepository.delete(offeredService);
            return offeredService;
        }
    }

    @Transactional
    public OfferedService getOfferedServiceService(long serviceID) {
        // Fetch the service we want through the id in the db
        OfferedService offeredService = offeredServiceRepository.findOfferedServiceByServiceID(serviceID);
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

        // If the description is empty
        if (description == null || description.isEmpty()) {
            throw new Exception("Description cannot be empty.");
        }

        // If the service was found
        else {
            // Create the service by adding all the necessary components, one by one
            offeredService.setDescription(description);
            offeredService.setCost(cost);
            offeredService.setDuration(duration);
            offeredServiceRepository.save(offeredService);
            return offeredService;
        }
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
