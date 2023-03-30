/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

// line 60 "../../../../../ParkingLot.ump"
@Entity
public class ParkingSpot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingSpot Attributes
  private long parkingSpotID;
  private int floor;
  private int number;

  //ParkingSpot Associations
  private MonthlyReservation monthlyReservation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ParkingSpot(long aParkingSpotID, int aFloor, int aNumber)
  {
    parkingSpotID = aParkingSpotID;
    floor = aFloor;
    number = aNumber;
  }
  public ParkingSpot( int aFloor, int aNumber)
  {
    floor = aFloor;
    number = aNumber;
  }

  public ParkingSpot() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setParkingSpotID(long aParkingSpotID)
  {
    boolean wasSet = false;
    parkingSpotID = aParkingSpotID;
    wasSet = true;
    return wasSet;
  }

  public boolean setFloor(int aFloor)
  {
    boolean wasSet = false;
    floor = aFloor;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  @Id
  @GeneratedValue
  public long getParkingSpotID()
  {
    return parkingSpotID;
  }

  public int getFloor()
  {
    return floor;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template association_GetOne */
  @OneToOne(optional = true)
  public MonthlyReservation getMonthlyReservation()
  {
    return monthlyReservation;
  }

  public boolean hasMonthlyReservation()
  {
    boolean has = monthlyReservation != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setMonthlyReservation(MonthlyReservation aNewMonthlyReservation)
  {
    boolean wasSet = false;
    monthlyReservation = aNewMonthlyReservation;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    monthlyReservation = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "parkingSpotID" + ":" + getParkingSpotID()+ "," +
            "floor" + ":" + getFloor()+ "," +
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "monthlyReservation = "+(getMonthlyReservation()!=null?Integer.toHexString(System.identityHashCode(getMonthlyReservation())):"null");
  }
}