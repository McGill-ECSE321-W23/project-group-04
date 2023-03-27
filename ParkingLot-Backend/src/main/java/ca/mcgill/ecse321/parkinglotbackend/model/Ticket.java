/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// line 94 "../../../../../ParkingLot.ump"
@Entity
public class Ticket
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum CarType { Regular, Large }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ticket Attributes
  private long ticketID;
  private LocalDateTime entryTime;
  private CarType carType;

  //Ticket Associations
  private ParkingLotSoftwareSystem parkingLotSoftwareSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  
  public Ticket(long aTicketID, LocalDateTime aEntryTime, CarType aCarType, ParkingLotSoftwareSystem aParkingLotSoftwareSystem)
  {
    ticketID = aTicketID;
    entryTime = aEntryTime;
    carType = aCarType;
    if (!setSystem(aParkingLotSoftwareSystem))
    {
      throw new RuntimeException("Unable to create Ticket due to aParkingLotSoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Ticket() {
  }
  
  public Ticket(LocalDateTime aEntryTime, CarType aCarType, ParkingLotSoftwareSystem aParkingLotSoftwareSystem)
  {
    entryTime = aEntryTime;
    carType = aCarType;
    if (!setSystem(aParkingLotSoftwareSystem))
    {
      throw new RuntimeException("Unable to create Ticket due to aParkingLotSoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

//------------------------
  // INTERFACE
  //------------------------

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

  @Id
  @GeneratedValue
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
  /* Code from template association_GetOne */

  @ManyToOne(optional=false)
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

  public void delete()
  {
    parkingLotSoftwareSystem = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "ticketID" + ":" + getTicketID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "entryTime" + "=" + (getEntryTime() != null ? !getEntryTime().equals(this)  ? getEntryTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "carType" + "=" + (getCarType() != null ? !getCarType().equals(this)  ? getCarType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parkingLotSoftwareSystem = "+(getSystem()!=null?Integer.toHexString(System.identityHashCode(getSystem())):"null");
  }
}