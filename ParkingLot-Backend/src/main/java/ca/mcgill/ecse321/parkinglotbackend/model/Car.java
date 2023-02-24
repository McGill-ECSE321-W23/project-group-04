/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 47 "../../../../../../ParkingLot.ump"
public class Car
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes
  private String carID;
  private String licensePlate;
  private String make;
  private String model;

  //Car Associations
  private Person owner;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Car(String aCarID, String aLicensePlate, String aMake, String aModel, Person aOwner)
  {
    carID = aCarID;
    licensePlate = aLicensePlate;
    make = aMake;
    model = aModel;
    if (!setOwner(aOwner))
    {
      throw new RuntimeException("Unable to create Car due to aOwner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCarID(String aCarID)
  {
    boolean wasSet = false;
    carID = aCarID;
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

  public String getCarID()
  {
    return carID;
  }

  /**
   * unique
   */
  public String getLicensePlate()
  {
    return licensePlate;
  }

  public String getMake()
  {
    return make;
  }

  public String getModel()
  {
    return model;
  }
  /* Code from template association_GetOne */
  public Person getOwner()
  {
    return owner;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setOwner(Person aNewOwner)
  {
    boolean wasSet = false;
    if (aNewOwner != null)
    {
      owner = aNewOwner;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    owner = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "carID" + ":" + getCarID()+ "," +
            "licensePlate" + ":" + getLicensePlate()+ "," +
            "make" + ":" + getMake()+ "," +
            "model" + ":" + getModel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null");
  }
}