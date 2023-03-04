/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

// line 43 "../../../../../../ParkingLot.ump"
public class ManagerAccount extends StaffAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ManagerAccount(String aAccountID, String aEmail, String aPassword, Person aPerson, float aSalary)
  {
    super(aAccountID, aEmail, aPassword, aPerson, aSalary);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}