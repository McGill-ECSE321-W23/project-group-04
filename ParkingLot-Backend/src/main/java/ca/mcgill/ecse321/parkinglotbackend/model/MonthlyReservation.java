/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// line 54 "../../../../../../ParkingLot.ump"
@Entity
@Data
@NoArgsConstructor
public class MonthlyReservation
{
  //MonthlyReservation Attributes
  @Id
  @GeneratedValue
  private Long monthlyReservationID;
  private LocalDate startDate;
  private LocalDate endDate;

  //MonthlyReservation Associations
  @ManyToOne
  private Person person;

  public MonthlyReservation(LocalDate startDate, LocalDate endDate, Person aPerson) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.person = aPerson;
  }

}