/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// line 70 "../../../../../ParkingLot.ump"
@Entity
public class Garage
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Garage> garagesByGarageID = new HashMap<Integer, Garage>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Garage Attributes
  private int GarageID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Garage(int aGarageID)
  {
    if (!setGarageID(aGarageID))
    {
      throw new RuntimeException("Cannot create due to duplicate GarageID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGarageID(int aGarageID)
  {
    boolean wasSet = false;
    Integer anOldGarageID = getGarageID();
    if (anOldGarageID != null && anOldGarageID.equals(aGarageID)) {
      return true;
    }
    if (hasWithGarageID(aGarageID)) {
      return wasSet;
    }
    GarageID = aGarageID;
    wasSet = true;
    if (anOldGarageID != null) {
      garagesByGarageID.remove(anOldGarageID);
    }
    garagesByGarageID.put(aGarageID, this);
    return wasSet;
  }

  @Id
  public int getGarageID()
  {
    return GarageID;
  }
  /* Code from template attribute_GetUnique */
  public static Garage getWithGarageID(int aGarageID)
  {
    return garagesByGarageID.get(aGarageID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithGarageID(int aGarageID)
  {
    return getWithGarageID(aGarageID) != null;
  }

  public void delete()
  {
    garagesByGarageID.remove(getGarageID());
  }


  public String toString()
  {
    return super.toString() + "["+
            "GarageID" + ":" + getGarageID()+ "]";
  }
}