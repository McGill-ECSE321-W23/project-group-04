/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// line 91 "../../../../../../ParkingLot.ump"
@Entity
@Data
@NoArgsConstructor
public class Ticket
{
  //------------------------
  // ENUMERATIONS
  //------------------------
  public enum CarType { Regular, Large }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ticket Attributes
  @Id
  @GeneratedValue
  private Long ticketID;
  private LocalDateTime entryTime;
  private CarType carType;

  //Ticket Associations
  @ManyToOne(optional=true)
  private ParkingLotSoftwareSystem parkingLotSoftwareSystem;

  // Constructor
  public Ticket(LocalDateTime aEntryTime, CarType aCarType)
  {
    entryTime = aEntryTime;
    carType = aCarType;
  }
  
  public String toString()
  {
    return super.toString() + "["+
            "ticketForRegularCarID" + ":" + getTicketID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "entryTime" + "=" + (getEntryTime() != null ? !getEntryTime().equals(this)  ? getEntryTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "carType" + "=" + (getCarType() != null ? !getCarType().equals(this)  ? getCarType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}