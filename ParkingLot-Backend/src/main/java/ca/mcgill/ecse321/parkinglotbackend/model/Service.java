/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// line 63 "../../../../../ParkingLot.ump"
@Entity
public class Service
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Service> servicesByServiceID = new HashMap<String, Service>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String ServiceID;
  private String description;
  private float cost;
  private int duration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aServiceID, String aDescription, float aCost, int aDuration)
  {
    description = aDescription;
    cost = aCost;
    duration = aDuration;
    if (!setServiceID(aServiceID))
    {
      throw new RuntimeException("Cannot create due to duplicate ServiceID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setServiceID(String aServiceID)
  {
    boolean wasSet = false;
    String anOldServiceID = getServiceID();
    if (anOldServiceID != null && anOldServiceID.equals(aServiceID)) {
      return true;
    }
    if (hasWithServiceID(aServiceID)) {
      return wasSet;
    }
    ServiceID = aServiceID;
    wasSet = true;
    if (anOldServiceID != null) {
      servicesByServiceID.remove(anOldServiceID);
    }
    servicesByServiceID.put(aServiceID, this);
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setCost(float aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  @Id
  public String getServiceID()
  {
    return ServiceID;
  }
  /* Code from template attribute_GetUnique */
  public static Service getWithServiceID(String aServiceID)
  {
    return servicesByServiceID.get(aServiceID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithServiceID(String aServiceID)
  {
    return getWithServiceID(aServiceID) != null;
  }

  public String getDescription()
  {
    return description;
  }

  public float getCost()
  {
    return cost;
  }

  /**
   * in minutes
   */
  public int getDuration()
  {
    return duration;
  }

  public void delete()
  {
    servicesByServiceID.remove(getServiceID());
  }


  public String toString()
  {
    return super.toString() + "["+
            "ServiceID" + ":" + getServiceID()+ "," +
            "description" + ":" + getDescription()+ "," +
            "cost" + ":" + getCost()+ "," +
            "duration" + ":" + getDuration()+ "]";
  }
}