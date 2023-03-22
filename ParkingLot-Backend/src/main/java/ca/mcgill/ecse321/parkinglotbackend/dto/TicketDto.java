package ca.mcgill.ecse321.parkinglotbackend.dto;

import java.time.LocalDateTime;


public class TicketDto {

    private long ticketID;
    private LocalDateTime entryTime;
    private CarType carType;

     //Ticket Associations
     private ParkingLotSoftwareSystem parkingLotSoftwareSystem;

	public TicketDto() {
	}

	// @SuppressWarnings("unchecked")
	// public TicketDto(long aTicketID) {
		
	// }

	public TicketDto(long aTicketID, LocalDateTime aEntryTime, 
    CarType aCarType, ParkingLotSoftwareSystem aParkingLotSoftwareSystem) {
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
    public void getSystem() {
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

