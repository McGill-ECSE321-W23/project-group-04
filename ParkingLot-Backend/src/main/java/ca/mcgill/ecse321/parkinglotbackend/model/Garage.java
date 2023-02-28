/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// line 76 "../../../../../ParkingLot.ump"
@Entity
public class Garage
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Garage Attributes
  private String garageID;
  private int garageNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Garage(String aGarageID, int aGarageNumber)
  {
    garageID = aGarageID;
    garageNumber = aGarageNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Garage() {
	// TODO Auto-generated constructor stub
	garageID = null;
	garageNumber = -1;
}

public boolean setGarageID(String aGarageID)
  {
    boolean wasSet = false;
    garageID = aGarageID;
    wasSet = true;
    return wasSet;
  }

  public boolean setGarageNumber(int aGarageNumber)
  {
    boolean wasSet = false;
    garageNumber = aGarageNumber;
    wasSet = true;
    return wasSet;
  }

  @Id
  public String getGarageID()
  {
    return garageID;
  }

  /**
   * unique and generated
   */
  public int getGarageNumber()
  {
    return garageNumber;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "garageID" + ":" + getGarageID()+ "," +
            "garageNumber" + ":" + getGarageNumber()+ "]";
  }
}