package ca.mcgill.ecse321.parkinglotbackend.dto;

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

    private ParkingLotSoftwareSystemDto plsDto;

    // constructors

    public TicketDto(long aTicketID, LocalDateTime aEntryTime, CarType aCarType, ParkingLotSoftwareSystemDto aPlsDto)
  {
    ticketID = aTicketID;
    entryTime = aEntryTime;
    carType = aCarType;
    if (!setSystem(aPlsDto))
    {
      throw new RuntimeException("Unable to create Ticket due to aParkingLotSoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }
  public TicketDto() {
  }
  
  public TicketDto(LocalDateTime aEntryTime, CarType aCarType, ParkingLotSoftwareSystemDto aPlsDto)
  {
    entryTime = aEntryTime;
    carType = aCarType;
    if (!setSystem(aPlsDto))
    {
      throw new RuntimeException("Unable to create Ticket due to aParkingLotSoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public ParkingLotSoftwareSystemDto getParkingLotSoftwareSystem() {
    return plsDto;
  }

  public void setParkingLotSoftwareSystem(ParkingLotSoftwareSystemDto plsDto) {
    this.plsDto = plsDto;
  }

// setters
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
// getters

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

  public ParkingLotSoftwareSystemDto getSystem()
  {
    return plsDto;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setSystem(ParkingLotSoftwareSystemDto aNewPlsDto)
  {
    boolean wasSet = false;
    if (aNewPlsDto != null)
    {
      plsDto = aNewPlsDto;
      wasSet = true;
    }
    return wasSet;
  }


}