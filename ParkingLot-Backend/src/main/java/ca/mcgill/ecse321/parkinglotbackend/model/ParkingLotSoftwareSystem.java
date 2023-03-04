/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 3 "../../../../../../ParkingLot.ump"
public class ParkingLotSoftwareSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingLotSoftwareSystem Attributes
  private String parkingLotSoftwareSystemID;
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

  public ParkingLotSoftwareSystem(String aParkingLotSoftwareSystemID, float aMonthlyFee, float aFeePer15m, int aMaxStay, int aNumberOfRegularParkingSpots, int aNumberOfLargeParkingSpots, int aNumberOfMonthlyFloors, int aNumberOfMonthlySpotsPerFloor, int aNumberOfGarages)
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

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setParkingLotSoftwareSystemID(String aParkingLotSoftwareSystemID)
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

  public String getParkingLotSoftwareSystemID()
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