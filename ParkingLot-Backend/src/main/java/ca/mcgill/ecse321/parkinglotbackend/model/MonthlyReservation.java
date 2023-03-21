/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// line 51 "../../../../../ParkingLot.ump"
@Entity
public class MonthlyReservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MonthlyReservation Attributes
  private long monthlyReservationID;
  private LocalDate startDate;
  private LocalDate endDate;

  //MonthlyReservation Associations
  private Person person;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MonthlyReservation(long aMonthlyReservationID, LocalDate aStartDate, LocalDate aEndDate, Person aPerson)
  {
    monthlyReservationID = aMonthlyReservationID;
    startDate = aStartDate;
    endDate = aEndDate;
    if (!setPerson(aPerson))
    {
      throw new RuntimeException("Unable to create MonthlyReservation due to aPerson. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public MonthlyReservation() {}
  
  public MonthlyReservation(LocalDate aStartDate, LocalDate aEndDate, Person aPerson)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    if (!setPerson(aPerson))
    {
      throw new RuntimeException("Unable to create MonthlyReservation due to aPerson. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMonthlyReservationID(long aMonthlyReservationID)
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

  @Id
  @GeneratedValue
  public long getMonthlyReservationID()
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
  @ManyToOne
  public Person getPerson()
  {
    return person;
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
    person = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "monthlyReservationID" + ":" + getMonthlyReservationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null");
  }
}