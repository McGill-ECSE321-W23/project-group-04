/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 23 "../../../../../ParkingLot.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String personID;
  private String phoneNumber;
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aPersonID, String aPhoneNumber, String aName)
  {
    personID = aPersonID;
    phoneNumber = aPhoneNumber;
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPersonID(String aPersonID)
  {
    boolean wasSet = false;
    personID = aPersonID;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getPersonID()
  {
    return personID;
  }

  /**
   * unique
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "personID" + ":" + getPersonID()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "name" + ":" + getName()+ "]";
  }
}