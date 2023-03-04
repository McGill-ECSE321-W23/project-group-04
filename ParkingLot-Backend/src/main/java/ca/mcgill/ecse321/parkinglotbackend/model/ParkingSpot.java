/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

// line 63 "../../../../../../ParkingLot.ump"
@Entity
@Data
@NoArgsConstructor
public class ParkingSpot
{
  //ParkingSpot Attributes
  @Id
  @GeneratedValue
  private Long parkingSpotID;
  private int floor;
  private int number;

  public String toString()
  {
    return super.toString() + "["+
            "parkingSpotID" + ":" + getParkingSpotID()+ "," +
            "floor" + ":" + getFloor()+ "," +
            "number" + ":" + getNumber()+ "]";
  }
}