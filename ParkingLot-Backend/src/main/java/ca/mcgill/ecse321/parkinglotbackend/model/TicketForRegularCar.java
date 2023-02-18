/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import org.joda.time.DateTime;

// line 79 "../../../../../ParkingLot.ump"
public class TicketForRegularCar
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextTicketForRegularCarID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TicketForRegularCar Attributes
  private DateTime entryTime;

  //Autounique Attributes
  private int TicketForRegularCarID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TicketForRegularCar(DateTime aEntryTime)
  {
    entryTime = aEntryTime;
    TicketForRegularCarID = nextTicketForRegularCarID++;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEntryTime(DateTime aEntryTime)
  {
    boolean wasSet = false;
    entryTime = aEntryTime;
    wasSet = true;
    return wasSet;
  }

  public DateTime getEntryTime()
  {
    return entryTime;
  }

  public int getTicketForRegularCarID()
  {
    return TicketForRegularCarID;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "TicketForRegularCarID" + ":" + getTicketForRegularCarID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "entryTime" + "=" + (getEntryTime() != null ? !getEntryTime().equals(this)  ? getEntryTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}