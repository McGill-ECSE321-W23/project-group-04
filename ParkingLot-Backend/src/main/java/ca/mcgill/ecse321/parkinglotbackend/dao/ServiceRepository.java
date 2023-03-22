package ca.mcgill.ecse321.parkinglotbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Service;

public interface ServiceRepository extends CrudRepository<Service, Long>{
	Service findServiceByServiceID(Long serviceId);
}
