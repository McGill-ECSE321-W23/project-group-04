/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 63 "../../../../../ParkingLot.ump"
public class ParkingSpot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingSpot Attributes
  private String parkingSpotID;
  private int floor;
  private int number;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ParkingSpot(String aParkingSpotID, int aFloor, int aNumber)
  {
    parkingSpotID = aParkingSpotID;
    floor = aFloor;
    number = aNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setParkingSpotID(String aParkingSpotID)
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

  public String getParkingSpotID()
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "parkingSpotID" + ":" + getParkingSpotID()+ "," +
            "floor" + ":" + getFloor()+ "," +
            "number" + ":" + getNumber()+ "]";
  }
}