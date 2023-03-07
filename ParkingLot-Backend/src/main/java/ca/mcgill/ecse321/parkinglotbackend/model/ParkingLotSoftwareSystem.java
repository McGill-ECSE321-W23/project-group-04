/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// line 3 "../../../../../../ParkingLot.ump"
@Entity
public class ParkingLotSoftwareSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingLotSoftwareSystem Attributes
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

  public ParkingLotSoftwareSystem(Long aParkingLotSoftwareSystemID, float aMonthlyFee, float aFeePer15m, int aMaxStay, int aNumberOfRegularParkingSpots, int aNumberOfLargeParkingSpots, int aNumberOfMonthlyFloors, int aNumberOfMonthlySpotsPerFloor, int aNumberOfGarages)
  {
    parkingLotSoftwareSystemID = aParkingLotSoftwareSystemID;
    monthlyFee = aMonthlyFee;
    feePer15m = aFeePer15m;
    maxStay = aMaxStay;
    numberOfRegularParkingSpots = aNumberOfRegularParkingSpots;
    numberOfLargeParkingSpots = aNumberOfLargeParkingSpots;
    numberOfMonthlyFloors = aNumberOfMonthlyFloors;
    numberOfMonthlySpotsPerFloor = aNumberOfMonthlySpotsPerFloor;
    numberOfGarages = aNumberOfGarages;
  }

  // FOR PERSISTENCE TESTING
  public ParkingLotSoftwareSystem()
  {
    parkingLotSoftwareSystemID = null;
    monthlyFee = -1;
    feePer15m = -1;
    maxStay = -1;
    numberOfRegularParkingSpots = -1;
    numberOfLargeParkingSpots = -1;
    numberOfMonthlyFloors = -1;
    numberOfMonthlySpotsPerFloor = -1;
    numberOfGarages = -1;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setParkingLotSoftwareSystemID(Long aParkingLotSoftwareSystemID)
  {
    boolean wasSet = false;
    parkingLotSoftwareSystemID = aParkingLotSoftwareSystemID;
    wasSet = true;
    return wasSet;
  }

  public boolean setMonthlyFee(float aMonthlyFee)
  {
    boolean wasSet = false;
    monthlyFee = aMonthlyFee;
    wasSet = true;
    return wasSet;
  }

  public boolean setFeePer15m(float aFeePer15m)
  {
    boolean wasSet = false;
    feePer15m = aFeePer15m;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxStay(int aMaxStay)
  {
    boolean wasSet = false;
    maxStay = aMaxStay;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfRegularParkingSpots(int aNumberOfRegularParkingSpots)
  {
    boolean wasSet = false;
    numberOfRegularParkingSpots = aNumberOfRegularParkingSpots;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfLargeParkingSpots(int aNumberOfLargeParkingSpots)
  {
    boolean wasSet = false;
    numberOfLargeParkingSpots = aNumberOfLargeParkingSpots;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfMonthlyFloors(int aNumberOfMonthlyFloors)
  {
    boolean wasSet = false;
    numberOfMonthlyFloors = aNumberOfMonthlyFloors;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfMonthlySpotsPerFloor(int aNumberOfMonthlySpotsPerFloor)
  {
    boolean wasSet = false;
    numberOfMonthlySpotsPerFloor = aNumberOfMonthlySpotsPerFloor;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfGarages(int aNumberOfGarages)
  {
    boolean wasSet = false;
    numberOfGarages = aNumberOfGarages;
    wasSet = true;
    return wasSet;
  }

  @Id
  public Long getParkingLotSoftwareSystemID()
  {
    return parkingLotSoftwareSystemID;
  }

  public float getMonthlyFee()
  {
    return monthlyFee;
  }

  public float getFeePer15m()
  {
    return feePer15m;
  }

  /**
   * in minutes
   */
  public int getMaxStay()
  {
    return maxStay;
  }

  public int getNumberOfRegularParkingSpots()
  {
    return numberOfRegularParkingSpots;
  }

  public int getNumberOfLargeParkingSpots()
  {
    return numberOfLargeParkingSpots;
  }

  public int getNumberOfMonthlyFloors()
  {
    return numberOfMonthlyFloors;
  }

  public int getNumberOfMonthlySpotsPerFloor()
  {
    return numberOfMonthlySpotsPerFloor;
  }

  public int getNumberOfGarages()
  {
    return numberOfGarages;
  }

  public void delete()
  {}


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