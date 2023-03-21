/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// line 23 "../../../../../ParkingLot.ump"
@Entity
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private long personID;
  private String phoneNumber;
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(long aPersonID, String aPhoneNumber, String aName)
  {
    personID = aPersonID;
    phoneNumber = aPhoneNumber;
    name = aName;
  }

  public Person() {}

  //------------------------
  // INTERFACE
  //------------------------

  public Person(String aPhoneNumber, String aName) {
	    phoneNumber = aPhoneNumber;
	    name = aName;
  }

public boolean setPersonID(long aPersonID)
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

  @Id
  @GeneratedValue
  public long getPersonID()
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