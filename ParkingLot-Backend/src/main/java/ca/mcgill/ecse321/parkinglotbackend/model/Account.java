/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// line 17 "../../../../../../ParkingLot.ump"
@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account
{
  //Account Attributes
  @Id
  @GeneratedValue
  private Long accountID;
  private String email;
  private String password;

  //Account Associations
  @OneToOne(optional = false, cascade = CascadeType.PERSIST)
  private Person person;

  public Account(String email, String password, Person person) {
    this.email = email;
    this.password = password;
    this.person = person;
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