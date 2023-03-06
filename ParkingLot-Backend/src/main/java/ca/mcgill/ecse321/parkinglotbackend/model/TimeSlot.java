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

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
  @ManyToOne
  private ParkingLotSoftwareSystem parkingLotSoftwareSystem;

  public TimeSlot(LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.dayOfTheWeek = dayOfWeek;
  }

  public String toString()
  {
    return super.toString() + "["+
            "timeSlotID" + ":" + getTimeSlotID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator");
  }
}