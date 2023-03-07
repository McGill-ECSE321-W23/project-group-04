/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// line 69 "../../../../../ParkingLot.ump"
@Entity
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String serviceID;
  private String description;
  private float cost;
  private int duration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aServiceID, String aDescription, float aCost, int aDuration)
  {
    serviceID = aServiceID;
    description = aDescription;
    cost = aCost;
    duration = aDuration;
   
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Service() {
	// TODO Auto-generated constructor stub
	serviceID = null;
	description = null;
	cost = -1;
	duration = -1;
  }

public boolean setServiceID(String aServiceID)
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
  public String getServiceID()
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


}