/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;

// line 17 "../../../../../ParkingLot.ump"
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private long accountID;
  private String email;
  private String password;

  //Account Associations
  private Person person;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(long aAccountID, String aEmail, String aPassword, Person aPerson)
  {
    accountID = aAccountID;
    email = aEmail;
    password = aPassword;
    if (!setPerson(aPerson))
    {
      throw new RuntimeException("Unable to create Account due to aPerson. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }
  
  public Account() {}
  
  public Account(String aEmail, String aPassword, Person aPerson)
  {
    email = aEmail;
    password = aPassword;
    if (!setPerson(aPerson))
    {
      throw new RuntimeException("Unable to create Account due to aPerson. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAccountID(long aAccountID)
  {
    boolean wasSet = false;
    accountID = aAccountID;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  @Id
  @GeneratedValue
  public long getAccountID()
  {
    return accountID;
  }

  /**
   * unique
   */
  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  public Person getPerson()
  {
    return person;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPerson(Person aNewPerson)
  {
    boolean wasSet = false;
    if (aNewPerson != null)
    {
      person = aNewPerson;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    person = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "accountID" + ":" + getAccountID()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null");
  }
}