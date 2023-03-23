package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingSpotRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.TicketRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpotService {
    @Autowired
    ParkingSpotRepository parkingSpotRepository;



    @Transactional
    public ParkingSpot createParkingSpot(LocalDateTime entryTime) {
        Ticket ticket = new Ticket();

        long ticketID = ticket.getTicketID();

        // set entry time
        //LocalDateTime currentTime = LocalDateTime.now();
        ticket.setEntryTime(entryTime);

        ticketRepository.save(ticket);


        return ticket;
    }

    @Transactional
    public Ticket getTicket(long ticketID) {
        Ticket ticket = ticketRepository.findTicketByTicketID(ticketID);
        return ticket;
    }

    @Transactional
    public List<Ticket> getAllTickets() {
        return toList(ticketRepository.findAll());
    }

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

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}


