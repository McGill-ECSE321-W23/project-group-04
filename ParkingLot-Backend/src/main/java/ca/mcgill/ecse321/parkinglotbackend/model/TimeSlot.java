/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

// line 103 "../../../../../../ParkingLot.ump"
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private String timeSlotID;
  private DayOfWeek dayOfTheWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  //TimeSlot Associations
  private ParkingLotSoftwareSystem system;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(String aTimeSlotID, DayOfWeek aDayOfTheWeek, LocalTime aStartTime, LocalTime aEndTime, ParkingLotSoftwareSystem aSystem)
  {
    timeSlotID = aTimeSlotID;
    dayOfTheWeek = aDayOfTheWeek;
    startTime = aStartTime;
    endTime = aEndTime;
    if (!setSystem(aSystem))
    {
      throw new RuntimeException("Unable to create TimeSlot due to aSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTimeSlotID(String aTimeSlotID)
  {
    boolean wasSet = false;
    timeSlotID = aTimeSlotID;
    wasSet = true;
    return wasSet;
  }

  public boolean setDayOfTheWeek(DayOfWeek aDayOfTheWeek)
  {
    boolean wasSet = false;
    dayOfTheWeek = aDayOfTheWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(LocalTime aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(LocalTime aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public String getTimeSlotID()
  {
    return timeSlotID;
  }

  public DayOfWeek getDayOfTheWeek()
  {
    return dayOfTheWeek;
  }

  public LocalTime getStartTime()
  {
    return startTime;
  }

  public LocalTime getEndTime()
  {
    return endTime;
  }
  /* Code from template association_GetOne */
  public ParkingLotSoftwareSystem getSystem()
  {
    return system;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setSystem(ParkingLotSoftwareSystem aNewSystem)
  {
    boolean wasSet = false;
    if (aNewSystem != null)
    {
      system = aNewSystem;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    system = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "timeSlotID" + ":" + getTimeSlotID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "system = "+(getSystem()!=null?Integer.toHexString(System.identityHashCode(getSystem())):"null");
  }
}