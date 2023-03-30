package ca.mcgill.ecse321.parkinglotbackend.service;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingLotSoftwareSystemRepository;
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

	/**
	 * @param entryTime time when ticket is created
	 * @param carType 	either Regular or Large
	 * @param parkingLotSoftwareSystem	the instance of the system
	 * @return	the ticket that was created
	 * @throws Exception	returns an error message if any of the params were not correct
	 * @author faizachowdhury
	 */
	@Transactional
	public Ticket createTicket(LocalDateTime entryTime, CarType carType,
							   ParkingLotSoftwareSystem parkingLotSoftwareSystem) throws Exception {
		if (parkingLotSoftwareSystem == null) {
			throw new Exception("Please provide a valid system");
		}
		if (carType == null) {
			throw new Exception("Please provide a valid Car Type");
		}
		if (entryTime == null) {
			throw new Exception("Please provide a valid entry time");
		}

		// create ticket
		Ticket ticket = new Ticket();
		ticket.setSystem(parkingLotSoftwareSystem);
		ticket.setCarType(carType);
		ticket.setEntryTime(entryTime);
		long ticketID = ticket.getTicketID();

		ticketRepository.save(ticket);
		return ticket;
	}

	/**
	 * Delete a ticket in the system
	 * @param ticketID	unique identifier of the ticket
	 * @return	Ticket object that was deleted
	 * @throws Exception error message if encountered
	 * @author faizachowdhury
	 */
	@Transactional
	public Ticket deleteTicket(long ticketID) throws Exception {
		Ticket ticket = ticketRepository.findTicketByTicketID(ticketID);
		if (ticket == null) {
			throw new Exception("There is no ticket in the system with this ID.");
		}
		ticketRepository.delete(ticket);
		return ticket;
	}

	/**
	 * Get a ticket by ID
	 * @param ticketID    unique identifier of the ticket
	 * @return	Ticket object that was found
	 * @throws Exception error message if encountered
	 */
	@Transactional
	public Ticket getTicketByID(long ticketID) throws Exception {
		Ticket ticket = ticketRepository.findTicketByTicketID(ticketID);
		if (ticket == null) {
			throw new Exception("There is no ticket in the system with this ID.");
		}
		return ticket;
	}

	/**
	 * get all the Tickets in the system currently
	 * @return	List of all Tickets
	 * @author faizachowdhury
	 */
	@Transactional
	public List<Ticket> getAllTickets(ParkingLotSoftwareSystem parkingLotSoftwareSystem) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (Ticket ticket: ticketRepository.findAll()) {
			if (ticket.getSystem() == parkingLotSoftwareSystem) {
				tickets.add(ticket);
			}
		}
		return tickets;
	}
	/**
	 * get all the Regular Tickets in the system currently
	 * @return	List of all Regular Tickets
	 * @author faizachowdhury
	 */
	@Transactional
	public List<Ticket> getAllTicketsRegular(ParkingLotSoftwareSystem parkingLotSoftwareSystem) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (Ticket ticket: ticketRepository.findAll()) {
			if (ticket.getSystem() == parkingLotSoftwareSystem && ticket.getCarType() == CarType.Regular) {
				tickets.add(ticket);
			}
		}
		return tickets;
	}
	/**
	 * get all the large Tickets in the system currently
	 * @return	List of all Large Tickets
	 * @author faizachowdhury
	 */
	@Transactional
	public List<Ticket> getAllTicketsLarge(ParkingLotSoftwareSystem parkingLotSoftwareSystem) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (Ticket ticket: ticketRepository.findAll()) {
			if (ticket.getSystem() == parkingLotSoftwareSystem && ticket.getCarType() == CarType.Large) {
				tickets.add(ticket);
			}
		}
		return tickets;
	}

	/**
	 * Find number of Tickets in the system
	 * @return 	the number of tickets that are in the system
	 * @author faizachowdhury
	 */
	@Transactional
	public int numberOfTickets(ParkingLotSoftwareSystem parkingLotSoftwareSystem) {
		List <Ticket> allTickets = getAllTickets(parkingLotSoftwareSystem);
		int number = allTickets.size();
		return number;
	}


}
    
