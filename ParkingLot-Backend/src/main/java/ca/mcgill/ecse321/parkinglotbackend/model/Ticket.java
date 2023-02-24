/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import java.time.LocalDateTime;

// line 91 "../../../../../../ParkingLot.ump"
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
  private String ticketForRegularCarID;
  private LocalDateTime entryTime;
  private CarType carType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ticket(String aTicketForRegularCarID, LocalDateTime aEntryTime, CarType aCarType)
  {
    ticketForRegularCarID = aTicketForRegularCarID;
    entryTime = aEntryTime;
    carType = aCarType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTicketForRegularCarID(String aTicketForRegularCarID)
  {
    boolean wasSet = false;
    ticketForRegularCarID = aTicketForRegularCarID;
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

  public String getTicketForRegularCarID()
  {
    return ticketForRegularCarID;
  }

  public LocalDateTime getEntryTime()
  {
    return entryTime;
  }

  public CarType getCarType()
  {
    return carType;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "ticketForRegularCarID" + ":" + getTicketForRegularCarID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "entryTime" + "=" + (getEntryTime() != null ? !getEntryTime().equals(this)  ? getEntryTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "carType" + "=" + (getCarType() != null ? !getCarType().equals(this)  ? getCarType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}