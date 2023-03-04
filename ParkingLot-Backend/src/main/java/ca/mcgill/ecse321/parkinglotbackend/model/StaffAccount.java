/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

// line 37 "../../../../../../ParkingLot.ump"
@Entity
@NoArgsConstructor
public class StaffAccount extends Account
{
  //StaffAccount Attributes
  private float salary;

  public void setSalary(float aSalary)
  {
    salary = aSalary;
  }

  public float getSalary()
  {
    return salary;
  }


  public String toString()
  {
    return super.toString() + "["+
            "salary" + ":" + getSalary()+ "]";
  }
}