package ca.mcgill.ecse321.parkinglotbackend.dao;

import java.util.List;

import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import org.springframework.data.repository.CrudRepository;


public interface ServiceAppointmentRepository extends CrudRepository<ServiceAppointment, String>{

    ServiceAppointment findAppointmentByServiceAppointmentID(Long serviceAppointmentID);

    List<ServiceAppointment> findAppointmentByCar(Car car);
    List<ServiceAppointment> findAppointmentByService(OfferedService service);

    boolean existsByCarAndService(Car car, OfferedService service);
    List<ServiceAppointment> findAppointmentByCarAndService(Car car, OfferedService service);

}