/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

  //ParkingSpot Associations
  @OneToOne(optional = true)
  private MonthlyReservation monthlyReservation;

  public ParkingSpot(int aFloor, int aNumber) {
    floor = aFloor;
    number = aNumber;
  }

}