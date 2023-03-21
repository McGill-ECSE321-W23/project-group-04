/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// line 66 "../../../../../ParkingLot.ump"
@Entity
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private long serviceID;
  private String description;
  private float cost;
  private int duration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(long aServiceID, String aDescription, float aCost, int aDuration)
  {
    serviceID = aServiceID;
    description = aDescription;
    cost = aCost;
    duration = aDuration;
  }

  public Service() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setServiceID(long aServiceID)
  {
    boolean wasSet = false;
    serviceID = aServiceID;
    wasSet = true;
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
  @GeneratedValue
  public long getServiceID()
  {
    return serviceID;
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
  {}


  public String toString()
  {
    return super.toString() + "["+
            "serviceID" + ":" + getServiceID()+ "," +
            "description" + ":" + getDescription()+ "," +
            "cost" + ":" + getCost()+ "," +
            "duration" + ":" + getDuration()+ "]";
  }
}