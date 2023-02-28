/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 32 "../../../../../ParkingLot.ump"
public class StaffAccountToTimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffAccountToTimeSlot Associations
  private StaffAccount staffAccount;
  private TimeSlot timeSlot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffAccountToTimeSlot(StaffAccount aStaffAccount, TimeSlot aTimeSlot)
  {
    if (!setStaffAccount(aStaffAccount))
    {
      throw new RuntimeException("Unable to create StaffAccountToTimeSlot due to aStaffAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setTimeSlot(aTimeSlot))
    {
      throw new RuntimeException("Unable to create StaffAccountToTimeSlot due to aTimeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public StaffAccount getStaffAccount()
  {
    return staffAccount;
  }
  /* Code from template association_GetOne */
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setStaffAccount(StaffAccount aNewStaffAccount)
  {
    boolean wasSet = false;
    if (aNewStaffAccount != null)
    {
      staffAccount = aNewStaffAccount;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    staffAccount = null;
    timeSlot = null;
  }

}