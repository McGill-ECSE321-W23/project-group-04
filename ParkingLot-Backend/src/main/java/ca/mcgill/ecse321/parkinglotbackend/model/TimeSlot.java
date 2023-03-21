/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// line 106 "../../../../../ParkingLot.ump"
@Entity
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private long timeSlotID;
  private DayOfWeek dayOfTheWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  //TimeSlot Associations
  private ParkingLotSoftwareSystem system;
  private StaffAccount staffAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(long aTimeSlotID, DayOfWeek aDayOfTheWeek, LocalTime aStartTime, LocalTime aEndTime)
  {
    timeSlotID = aTimeSlotID;
    dayOfTheWeek = aDayOfTheWeek;
    startTime = aStartTime;
    endTime = aEndTime;
  }

  public TimeSlot() {}
  
  public TimeSlot(DayOfWeek aDayOfTheWeek, LocalTime aStartTime, LocalTime aEndTime)
  {
    dayOfTheWeek = aDayOfTheWeek;
    startTime = aStartTime;
    endTime = aEndTime;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTimeSlotID(long aTimeSlotID)
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

  @Id
  @GeneratedValue
  public long getTimeSlotID()
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
  @ManyToOne(optional=true)
  public ParkingLotSoftwareSystem getSystem()
  {
    return system;
  }

  public boolean hasSystem()
  {
    boolean has = system != null;
    return has;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional=true)
  public StaffAccount getStaffAccount()
  {
    return staffAccount;
  }

  public boolean hasStaffAccount()
  {
    boolean has = staffAccount != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setSystem(ParkingLotSoftwareSystem aNewSystem)
  {
    boolean wasSet = false;
    system = aNewSystem;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setStaffAccount(StaffAccount aNewStaffAccount)
  {
    boolean wasSet = false;
    staffAccount = aNewStaffAccount;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    system = null;
    staffAccount = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "timeSlotID" + ":" + getTimeSlotID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "system = "+(getSystem()!=null?Integer.toHexString(System.identityHashCode(getSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "staffAccount = "+(getStaffAccount()!=null?Integer.toHexString(System.identityHashCode(getStaffAccount())):"null");
  }
}