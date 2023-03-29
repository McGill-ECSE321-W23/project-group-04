package ca.mcgill.ecse321.parkinglotbackend.dto;

import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;

import java.time.LocalDateTime;

/**
 * Ticket Data Transfer Object
 * @author faizachowdhury
 */
public class TicketDto {
    
    // Attributes
    private long ticketID;
    private CarType carType;
    private LocalDateTime entryTime;
    private ParkingLotSoftwareSystem parkingLotSoftwareSystem;

    public TicketDto(long aTicketID, LocalDateTime aEntryTime, CarType aCarType, ParkingLotSoftwareSystem aParkingLotSoftwareSystem)
  {
    ticketID = aTicketID;
    entryTime = aEntryTime;
    carType = aCarType;
    if (!setSystem(aParkingLotSoftwareSystem))
    {
      throw new RuntimeException("Unable to create Ticket due to aParkingLotSoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public TicketDto() {
  }
  
  public TicketDto(LocalDateTime aEntryTime, CarType aCarType, ParkingLotSoftwareSystem aParkingLotSoftwareSystem)
  {
    entryTime = aEntryTime;
    carType = aCarType;
    if (!setSystem(aParkingLotSoftwareSystem))
    {
      throw new RuntimeException("Unable to create Ticket due to aParkingLotSoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public ParkingLotSoftwareSystem getParkingLotSoftwareSystem() {
    return parkingLotSoftwareSystem;
  }

  public void setParkingLotSoftwareSystem(ParkingLotSoftwareSystem parkingLotSoftwareSystem) {
    this.parkingLotSoftwareSystem = parkingLotSoftwareSystem;
  }


  public boolean setTicketID(long aTicketID)
  {
    boolean wasSet = false;
    ticketID = aTicketID;
    wasSet = true;
    return wasSet;
  }

  public boolean setEntryTime(LocalDateTime aEntryTime)
  {
    boolean wasSet = false;
    entryTime = aEntryTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setCarType(CarType aCarType)
  {
    boolean wasSet = false;
    carType = aCarType;
    wasSet = true;
    return wasSet;
  }

  public long getTicketID()
  {
    return ticketID;
  }

  public LocalDateTime getEntryTime()
  {
    return entryTime;
  }

  public CarType getCarType()
  {
    return carType;
  }
  
  public ParkingLotSoftwareSystem getSystem()
  {
    return parkingLotSoftwareSystem;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setSystem(ParkingLotSoftwareSystem aNewParkingLotSoftwareSystem)
  {
    boolean wasSet = false;
    if (aNewParkingLotSoftwareSystem != null)
    {
      parkingLotSoftwareSystem = aNewParkingLotSoftwareSystem;
      wasSet = true;
    }
    return wasSet;
  }


}