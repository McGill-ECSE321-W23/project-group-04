/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;

// line 40 "../../../../../ParkingLot.ump"
@Entity
public class ManagerAccount extends StaffAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ManagerAccount(long aAccountID, String aEmail, String aPassword, Person aPerson, float aSalary)
  {
    super(aAccountID, aEmail, aPassword, aPerson, aSalary);
  }

  public ManagerAccount() {
    super();
  }
  
  public ManagerAccount(String aEmail, String aPassword, Person aPerson, float aSalary)
  {
    super(aEmail, aPassword, aPerson, aSalary);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}