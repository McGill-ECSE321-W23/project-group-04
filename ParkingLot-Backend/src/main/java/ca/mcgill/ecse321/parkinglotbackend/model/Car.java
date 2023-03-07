/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

// line 47 "../../../../../ParkingLot.ump"
@Entity
@NoArgsConstructor
public class Car
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes

  private Long carID;
  private String licensePlate;
  private String make;
  private String model;

  //Car Associations
  private Person owner;

  // Constructor
  public Car(String aLicensePlate, String aMake, String aModel, Person aOwner)
  {
    licensePlate = aLicensePlate;
    make = aMake;
    model = aModel;
  }


  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCarID(Long aCarID)
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

  @Id
  @GeneratedValue
  public Long getCarID()
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
  @ManyToOne(optional = false)
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

}