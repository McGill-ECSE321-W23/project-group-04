/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 51 "../../../../../ParkingLot.ump"
public class Car
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes
  private String make;
  private String model;
  private String licensePlate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Car(String aMake, String aModel, String aLicensePlate)
  {
    make = aMake;
    model = aModel;
    licensePlate = aLicensePlate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMake(String aMake)
  {
    boolean wasSet = false;
    make = aMake;
    wasSet = true;
    return wasSet;
  }

  public boolean setModel(String aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setLicensePlate(String aLicensePlate)
  {
    boolean wasSet = false;
    licensePlate = aLicensePlate;
    wasSet = true;
    return wasSet;
  }

  public String getMake()
  {
    return make;
  }

  public String getModel()
  {
    return model;
  }

  public String getLicensePlate()
  {
    return licensePlate;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "make" + ":" + getMake()+ "," +
            "model" + ":" + getModel()+ "," +
            "licensePlate" + ":" + getLicensePlate()+ "]";
  }
}