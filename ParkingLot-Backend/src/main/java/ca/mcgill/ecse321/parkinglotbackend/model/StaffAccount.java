/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 37 "../../../../../../ParkingLot.ump"
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

  public StaffAccount(String aAccountID, String aEmail, String aPassword, Person aPerson, float aSalary)
  {
    super(aAccountID, aEmail, aPassword, aPerson);
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