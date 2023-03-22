package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;

@Service
public class OfferedServiceCreationService {

    @Autowired
    OfferedServiceRepository offeredServiceRepository;

    @Transactional
    public OfferedService createOfferedService(String description, float cost, int duration) throws Exception {
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
    public OfferedService getOfferedService(long serviceID) {
        // Fetch the service we want through the id in the db
        OfferedService offeredService = offeredServiceRepository.findOfferedServiceByServiceID(serviceID);
        return offeredService;
    }

    @Transactional
    public List<OfferedService> getAllOfferedService() {
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