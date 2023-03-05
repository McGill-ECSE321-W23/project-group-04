/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// line 23 "../../../../../../ParkingLot.ump"
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person
{
  //Person Attributes
  @Id
  @GeneratedValue
  private Long personID;

  private String phoneNumber;

  private String name;
  public void delete()
  {}

  public Person(String nbr, String name) {
    phoneNumber = nbr;
    this.name = name;
  }

  public String toString()
  {
    return super.toString() + "["+
            "personID" + ":" + getPersonID()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "name" + ":" + getName()+ "]";
  }
}