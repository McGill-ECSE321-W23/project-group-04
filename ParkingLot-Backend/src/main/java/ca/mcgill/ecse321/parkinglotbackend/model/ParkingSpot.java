/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

// line 57 "../../../../../ParkingLot.ump"
public class ParkingSpot
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, ParkingSpot> parkingspotsByParkingSpotID = new HashMap<String, ParkingSpot>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingSpot Attributes
  private String ParkingSpotID;
  private int floor;
  private int number;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ParkingSpot(String aParkingSpotID, int aFloor, int aNumber)
  {
    floor = aFloor;
    number = aNumber;
    if (!setParkingSpotID(aParkingSpotID))
    {
      throw new RuntimeException("Cannot create due to duplicate ParkingSpotID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setParkingSpotID(String aParkingSpotID)
  {
    boolean wasSet = false;
    String anOldParkingSpotID = getParkingSpotID();
    if (anOldParkingSpotID != null && anOldParkingSpotID.equals(aParkingSpotID)) {
      return true;
    }
    if (hasWithParkingSpotID(aParkingSpotID)) {
      return wasSet;
    }
    ParkingSpotID = aParkingSpotID;
    wasSet = true;
    if (anOldParkingSpotID != null) {
      parkingspotsByParkingSpotID.remove(anOldParkingSpotID);
    }
    parkingspotsByParkingSpotID.put(aParkingSpotID, this);
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
    return ParkingSpotID;
  }
  /* Code from template attribute_GetUnique */
  public static ParkingSpot getWithParkingSpotID(String aParkingSpotID)
  {
    return parkingspotsByParkingSpotID.get(aParkingSpotID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithParkingSpotID(String aParkingSpotID)
  {
    return getWithParkingSpotID(aParkingSpotID) != null;
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
  {
    parkingspotsByParkingSpotID.remove(getParkingSpotID());
  }


  public String toString()
  {
    return super.toString() + "["+
            "ParkingSpotID" + ":" + getParkingSpotID()+ "," +
            "floor" + ":" + getFloor()+ "," +
            "number" + ":" + getNumber()+ "]";
  }
}