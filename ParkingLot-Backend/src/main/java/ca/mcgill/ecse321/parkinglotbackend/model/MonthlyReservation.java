/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import org.joda.time.LocalDate;

// line 54 "../../../../../ParkingLot.ump"
public class MonthlyReservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MonthlyReservation Attributes
  private String monthlyReservationID;
  private LocalDate startDate;
  private LocalDate endDate;

  //MonthlyReservation Associations
  private ParkingSpot parkingSpot;
  private Person person;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MonthlyReservation(String aMonthlyReservationID, LocalDate aStartDate, LocalDate aEndDate, ParkingSpot aParkingSpot, Person aPerson)
  {
    monthlyReservationID = aMonthlyReservationID;
    startDate = aStartDate;
    endDate = aEndDate;
    if (!setParkingSpot(aParkingSpot))
    {
      throw new RuntimeException("Unable to create MonthlyReservation due to aParkingSpot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setPerson(aPerson))
    {
      throw new RuntimeException("Unable to create MonthlyReservation due to aPerson. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMonthlyReservationID(String aMonthlyReservationID)
  {
    boolean wasSet = false;
    monthlyReservationID = aMonthlyReservationID;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(LocalDate aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(LocalDate aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public String getMonthlyReservationID()
  {
    return monthlyReservationID;
  }

  public LocalDate getStartDate()
  {
    return startDate;
  }

  public LocalDate getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetOne */
  public ParkingSpot getParkingSpot()
  {
    return parkingSpot;
  }
  /* Code from template association_GetOne */
  public Person getPerson()
  {
    return person;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setParkingSpot(ParkingSpot aNewParkingSpot)
  {
    boolean wasSet = false;
    if (aNewParkingSpot != null)
    {
      parkingSpot = aNewParkingSpot;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPerson(Person aNewPerson)
  {
    boolean wasSet = false;
    if (aNewPerson != null)
    {
      person = aNewPerson;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    parkingSpot = null;
    person = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "monthlyReservationID" + ":" + getMonthlyReservationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parkingSpot = "+(getParkingSpot()!=null?Integer.toHexString(System.identityHashCode(getParkingSpot())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null");
  }
}