package ca.mcgill.ecse321.parkinglotbackend.dto;

import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
import java.time.LocalDateTime;


public class TicketDto {

    private long ticketID;
    private LocalDateTime entryTime;
    private Ticket.CarType carType;

     //Ticket Associations
     private ParkingLotSoftwareSystem parkingLotSoftwareSystem;

	public TicketDto() {
	}

	 @SuppressWarnings("unchecked")
	 public TicketDto(LocalDateTime aEntryTime) {
		this.setEntryTime(aEntryTime);
	 }

	public TicketDto(long aTicketID, LocalDateTime aEntryTime,
                     Ticket.CarType aCarType, ParkingLotSoftwareSystem aParkingLotSoftwareSystem) {
        this.parkingLotSoftwareSystem = aParkingLotSoftwareSystem;
        this.ticketID = aTicketID;
        this.entryTime = aEntryTime;
        this.carType = aCarType;
	}

    // getters

	public long getTicketID() {
		return ticketID;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

    public CarType getCarType() {
		return carType;
	}
    public ParkingLotSoftwareSystem getSystem() {
        return parkingLotSoftwareSystem;
    }

    // setters

    public void setTicketID(long aTicketID) {
       ticketID = aTicketID;
	}

	public void setEntryTime(LocalDateTime aEntryTime) {
        entryTime = aEntryTime;
     }

     public void setCarType(CarType aCarType) {
        carType = aCarType;
     }

     public void setSystem(ParkingLotSoftwareSystem aNewParkingLotSoftwareSystem) {
        if (aNewParkingLotSoftwareSystem != null) {
            parkingLotSoftwareSystem = aNewParkingLotSoftwareSystem;
           
        }
    }


}

