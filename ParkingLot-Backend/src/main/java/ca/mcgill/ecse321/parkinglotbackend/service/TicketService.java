package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.TicketRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TicketService {
	@Autowired
	TicketRepository ticketRepository;


	@Transactional
	public Ticket createTicket(LocalDateTime entryTime, CarType carType,
							   ParkingLotSoftwareSystem parkingLotSoftwareSystem) {
		// create ticket
		Ticket ticket = new Ticket();
		ticket.setSystem(parkingLotSoftwareSystem);
		ticket.setCarType(carType);

		// set entry time
		//LocalDateTime currentTime = LocalDateTime.now();
		ticket.setEntryTime(entryTime);

		long ticketID = ticket.getTicketID();

		ticketRepository.save(ticket);

		return ticket;
	}

	@Transactional
	public Ticket deleteTicket(long ticketID) throws Exception {
		Ticket ticket = ticketRepository.findTicketByTicketID(ticketID);
		if (ticket == null) {
			throw new Exception("There is no ticket in the system with this ID.");
		}
		ticketRepository.delete(ticket);
		return ticket;
	}
	@Transactional
	public Ticket getTicketByID(long ticketID) throws Exception {
		Ticket ticket = ticketRepository.findTicketByTicketID(ticketID);
		if (ticket == null) {
			throw new Exception("There is no ticket in the system with this ID.");
		}
		return ticket;
	}

	@Transactional
	public List<Ticket> getAllTickets() {
		return toList(ticketRepository.findAll());
	}

	@Transactional
	public int numberOfTickets() {
		List <Ticket> allTickets = getAllTickets();
		int number = allTickets.size();
		return number;
	}




	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
    

