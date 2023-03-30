package ca.mcgill.ecse321.parkinglotbackend.dto;
import java.time.LocalDateTime;


/**
 * Ticket Data Transfer Object
 * @author faizachowdhury
 */
public class TicketDto {

  public enum CarTypeDto{
    Regular, Large
  }
    
    // Attributes
    private long ticketID;
    private CarTypeDto carTypeDto;
    private LocalDateTime entryTime;

    private ParkingLotSoftwareSystemDto plsDto;

    // constructors

    public TicketDto(long aTicketID, LocalDateTime aEntryTime, CarTypeDto aCarTypeDto, ParkingLotSoftwareSystemDto aPlsDto)
  {
    ticketID = aTicketID;
    entryTime = aEntryTime;
    carTypeDto = aCarTypeDto;
    if (!setSystem(aPlsDto))
    {
      throw new RuntimeException("Unable to create Ticket due to aParkingLotSoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }
  public TicketDto() {
  }
  
  public TicketDto(LocalDateTime aEntryTime, CarTypeDto aCarTypeDto, ParkingLotSoftwareSystemDto aPlsDto)
  {
    entryTime = aEntryTime;
    carTypeDto = aCarTypeDto;
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

  public boolean setCarType(CarTypeDto aCarTypeDto)
  {
    boolean wasSet = false;
    carTypeDto = aCarTypeDto;
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

  public CarTypeDto getCarTypeDto()
  {

    return carTypeDto;
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