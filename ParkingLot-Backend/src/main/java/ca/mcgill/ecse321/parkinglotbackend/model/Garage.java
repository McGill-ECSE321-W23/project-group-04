/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

// line 76 "../../../../../ParkingLot.ump"
@Entity
@Data
@NoArgsConstructor
public class Garage
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Garage Attributes
  private Long garageID;
  private int garageNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Garage(int aGarageNumber)
  {
    garageNumber = aGarageNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

// public boolean setGarageID(String aGarageID)
//   {
//     boolean wasSet = false;
//     garageID = aGarageID;
//     wasSet = true;
//     return wasSet;
//   }

  public boolean setGarageNumber(int aGarageNumber)
  {
    boolean wasSet = false;
    garageNumber = aGarageNumber;
    wasSet = true;
    return wasSet;
  }

  @Id
  @GeneratedValue
  public Long getGarageID()
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