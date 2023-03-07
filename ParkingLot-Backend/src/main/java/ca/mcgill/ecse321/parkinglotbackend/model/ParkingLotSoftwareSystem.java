/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

// line 3 "../../../../../../ParkingLot.ump"
@Entity
@Data
@NoArgsConstructor
public class ParkingLotSoftwareSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingLotSoftwareSystem Attributes
  @Id
  @GeneratedValue
  private Long parkingLotSoftwareSystemID;
  private float monthlyFee;
  private float feePer15m;
  private int maxStay;
  private int numberOfRegularParkingSpots;
  private int numberOfLargeParkingSpots;
  private int numberOfMonthlyFloors;
  private int numberOfMonthlySpotsPerFloor;
  private int numberOfGarages;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ParkingLotSoftwareSystem(float aMonthlyFee, float aFeePer15m, int aMaxStay, int aNumberOfRegularParkingSpots, int aNumberOfLargeParkingSpots, int aNumberOfMonthlyFloors, int aNumberOfMonthlySpotsPerFloor, int aNumberOfGarages)
  {
    monthlyFee = aMonthlyFee;
    feePer15m = aFeePer15m;
    maxStay = aMaxStay;
    numberOfRegularParkingSpots = aNumberOfRegularParkingSpots;
    numberOfLargeParkingSpots = aNumberOfLargeParkingSpots;
    numberOfMonthlyFloors = aNumberOfMonthlyFloors;
    numberOfMonthlySpotsPerFloor = aNumberOfMonthlySpotsPerFloor;
    numberOfGarages = aNumberOfGarages;
  }

  public String toString()
  {
    return super.toString() + "["+
            "parkingLotSoftwareSystemID" + ":" + getParkingLotSoftwareSystemID()+ "," +
            "monthlyFee" + ":" + getMonthlyFee()+ "," +
            "feePer15m" + ":" + getFeePer15m()+ "," +
            "maxStay" + ":" + getMaxStay()+ "," +
            "numberOfRegularParkingSpots" + ":" + getNumberOfRegularParkingSpots()+ "," +
            "numberOfLargeParkingSpots" + ":" + getNumberOfLargeParkingSpots()+ "," +
            "numberOfMonthlyFloors" + ":" + getNumberOfMonthlyFloors()+ "," +
            "numberOfMonthlySpotsPerFloor" + ":" + getNumberOfMonthlySpotsPerFloor()+ "," +
            "numberOfGarages" + ":" + getNumberOfGarages()+ "]";
  }
}