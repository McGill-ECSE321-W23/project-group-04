/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

// line 23 "../../../../../../ParkingLot.ump"
@Entity
@NoArgsConstructor
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  @Id
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