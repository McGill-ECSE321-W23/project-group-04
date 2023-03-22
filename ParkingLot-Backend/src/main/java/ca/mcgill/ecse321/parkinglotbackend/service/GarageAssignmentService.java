//package ca.mcgill.ecse321.parkinglotbackend.service;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;
//import ca.mcgill.ecse321.parkinglotbackend.dao.OfferedServiceRepository;
//import ca.mcgill.ecse321.parkinglotbackend.dao.ServiceAppointmentRepository;
//import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
//import ca.mcgill.ecse321.parkinglotbackend.dao.StaffAccountRepository;
//import ca.mcgill.ecse321.parkinglotbackend.dao.ManagerAccountRepository;
//import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
//import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
//import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
//import ca.mcgill.ecse321.parkinglotbackend.model.Person;
//import ca.mcgill.ecse321.parkinglotbackend.model.Account;
//import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
//import ca.mcgill.ecse321.parkinglotbackend.model.ManagerAccount;
//
//@Service
//public class GarageAssignmentService {
//
//	@Autowired
//	GarageRepository garageRepository;
//	@Autowired
//	PersonRepository personRepository;
//	@Autowired
//	RegistrationRepository registrationRepository;
//
//	@Transactional
//	public Person createPerson(String name) {
//		Person person = new Person();
//		person.setName(name);
//		personRepository.save(person);
//		return person;
//	}
//
//	@Transactional
//	public Person getPerson(String name) {
//		Person person = personRepository.findPersonByName(name);
//		return person;
//	}
//
//	@Transactional
//	public List<Person> getAllPersons() {
//		return toList(personRepository.findAll());
//	}
//
//	@Transactional
//	public Event createEvent(String name, Date date, Time startTime, Time endTime) {
//		Event event = new Event();
//		event.setName(name);
//		event.setDate(date);
//		event.setStartTime(startTime);
//		event.setEndTime(endTime);
//		eventRepository.save(event);
//		return event;
//	}
//
//	@Transactional
//	public Event getEvent(String name) {
//		Event event = eventRepository.findEventByName(name);
//		return event;
//	}
//
//	@Transactional
//	public List<Event> getAllEvents() {
//		return toList(eventRepository.findAll());
//	}
//
//	@Transactional
//	public Registration register(Person person, Event event) {
//		Registration registration = new Registration();
//		registration.setId(person.getName().hashCode() * event.getName().hashCode());
//		registration.setPerson(person);
//		registration.setEvent(event);
//
//		registrationRepository.save(registration);
//
//		return registration;
//	}
//
//	@Transactional
//	public List<Registration> getAllRegistrations(){
//		return toList(registrationRepository.findAll());
//	}
//
//	@Transactional
//	public List<Event> getEventsAttendedByPerson(Person person) {
//		List<Event> eventsAttendedByPerson = new ArrayList<>();
//		for (Registration r : registrationRepository.findByPerson(person)) {
//			eventsAttendedByPerson.add(r.getEvent());
//		}
//		return eventsAttendedByPerson;
//	}
//
//	private <T> List<T> toList(Iterable<T> iterable){
//		List<T> resultList = new ArrayList<T>();
//		for (T t : iterable) {
//			resultList.add(t);
//		}
//		return resultList;
//	}
//
//}
//}
