/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

import org.joda.time.DateTime;

// line 89 "../../../../../ParkingLot.ump"
public class TimeSlot
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, TimeSlot> timeslotsByTimeSlotID = new HashMap<String, TimeSlot>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private String TimeSlotID;
  private DayOfTheWeek dayOfTheWeek;
  private DateTime startTime;
  private DateTime endTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(String aTimeSlotID, DayOfTheWeek aDayOfTheWeek, DateTime aStartTime, DateTime aEndTime)
  {
    dayOfTheWeek = aDayOfTheWeek;
    startTime = aStartTime;
    endTime = aEndTime;
    if (!setTimeSlotID(aTimeSlotID))
    {
      throw new RuntimeException("Cannot create due to duplicate TimeSlotID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTimeSlotID(String aTimeSlotID)
  {
    boolean wasSet = false;
    String anOldTimeSlotID = getTimeSlotID();
    if (anOldTimeSlotID != null && anOldTimeSlotID.equals(aTimeSlotID)) {
      return true;
    }
    if (hasWithTimeSlotID(aTimeSlotID)) {
      return wasSet;
    }
    TimeSlotID = aTimeSlotID;
    wasSet = true;
    if (anOldTimeSlotID != null) {
      timeslotsByTimeSlotID.remove(anOldTimeSlotID);
    }
    timeslotsByTimeSlotID.put(aTimeSlotID, this);
    return wasSet;
  }

  public boolean setDayOfTheWeek(DayOfTheWeek aDayOfTheWeek)
  {
    boolean wasSet = false;
    dayOfTheWeek = aDayOfTheWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(DateTime aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(DateTime aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public String getTimeSlotID()
  {
    return TimeSlotID;
  }
  /* Code from template attribute_GetUnique */
  public static TimeSlot getWithTimeSlotID(String aTimeSlotID)
  {
    return timeslotsByTimeSlotID.get(aTimeSlotID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithTimeSlotID(String aTimeSlotID)
  {
    return getWithTimeSlotID(aTimeSlotID) != null;
  }

  public DayOfTheWeek getDayOfTheWeek()
  {
    return dayOfTheWeek;
  }

  public DateTime getStartTime()
  {
    return startTime;
  }

  public DateTime getEndTime()
  {
    return endTime;
  }

  public void delete()
  {
    timeslotsByTimeSlotID.remove(getTimeSlotID());
  }


  public String toString()
  {
    return super.toString() + "["+
            "TimeSlotID" + ":" + getTimeSlotID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}