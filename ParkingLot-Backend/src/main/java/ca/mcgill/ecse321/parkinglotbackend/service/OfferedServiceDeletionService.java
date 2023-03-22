package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;

@Service
public class OfferedServiceDeletionService {
    @Autowired
    OfferedServiceRepository offeredServiceRepository;

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