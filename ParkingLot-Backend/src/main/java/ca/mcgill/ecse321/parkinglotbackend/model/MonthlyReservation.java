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
  @OneToOne(cascade = CascadeType.ALL)
  private ParkingSpot parkingSpot;

  @ManyToOne
  private Person person;

  public MonthlyReservation(LocalDate startDate, LocalDate endDate, ParkingSpot parkingSpot) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.parkingSpot = parkingSpot;
  }

  public String toString()
  {
    return super.toString() + "["+
            "monthlyReservationID" + ":" + getMonthlyReservationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parkingSpot = "+(getParkingSpot()!=null?Integer.toHexString(System.identityHashCode(getParkingSpot())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null");
  }
}