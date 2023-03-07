/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.ManyToOne;

// line 103 "../../../../../../ParkingLot.ump"
@Entity
@Data
@NoArgsConstructor
public class TimeSlot
{
  //TimeSlot Attributes
  @Id
  @GeneratedValue
  private Long timeSlotID;
  private DayOfWeek dayOfTheWeek;
  private LocalTime startTime;
  private LocalTime endTime;
  
  //TimeSlot Associations
  @ManyToOne(optional=true)
  private ParkingLotSoftwareSystem parkingLotSoftwareSystem;
  @ManyToOne(optional=true)
  private StaffAccount staffAccount;

  public TimeSlot(LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.dayOfTheWeek = dayOfWeek;
  }

}