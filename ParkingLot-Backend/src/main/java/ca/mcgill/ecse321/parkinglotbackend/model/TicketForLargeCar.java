/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 84 "../../../../../ParkingLot.ump"
public class TicketForLargeCar
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextTicketForLargeCarID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TicketForLargeCar Attributes
  private datetime entryTime;

  //Autounique Attributes
  private int TicketForLargeCarID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TicketForLargeCar(datetime aEntryTime)
  {
    entryTime = aEntryTime;
    TicketForLargeCarID = nextTicketForLargeCarID++;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEntryTime(datetime aEntryTime)
  {
    boolean wasSet = false;
    entryTime = aEntryTime;
    wasSet = true;
    return wasSet;
  }

  public datetime getEntryTime()
  {
    return entryTime;
  }

  public int getTicketForLargeCarID()
  {
    return TicketForLargeCarID;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "TicketForLargeCarID" + ":" + getTicketForLargeCarID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "entryTime" + "=" + (getEntryTime() != null ? !getEntryTime().equals(this)  ? getEntryTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}