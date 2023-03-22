package ca.mcgill.ecse321.parkinglotbackend.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;

public interface OfferedServiceRepository extends CrudRepository<OfferedService, Long>{
	OfferedService findServiceByServiceID(Long serviceId);
}

