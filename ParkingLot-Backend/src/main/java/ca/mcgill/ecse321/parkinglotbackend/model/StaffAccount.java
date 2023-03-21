/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;

// line 32 "../../../../../ParkingLot.ump"
@Entity
public class StaffAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffAccount Attributes
  private float salary;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffAccount(long aAccountID, String aEmail, String aPassword, Person aPerson, float aSalary)
  {
    super(aAccountID, aEmail, aPassword, aPerson);
    salary = aSalary;
  }

  public StaffAccount() {
    super();
  }

  public StaffAccount(String aEmail, String aPassword, Person aPerson, float aSalary)
  {
    super(aEmail, aPassword, aPerson);
    salary = aSalary;
  }
  
  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSalary(float aSalary)
  {
    boolean wasSet = false;
    salary = aSalary;
    wasSet = true;
    return wasSet;
  }

  public float getSalary()
  {
    return salary;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "salary" + ":" + getSalary()+ "]";
  }
}