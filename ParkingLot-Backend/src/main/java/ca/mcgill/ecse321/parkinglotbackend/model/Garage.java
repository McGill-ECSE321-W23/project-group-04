/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 76 "../../../../../../ParkingLot.ump"
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