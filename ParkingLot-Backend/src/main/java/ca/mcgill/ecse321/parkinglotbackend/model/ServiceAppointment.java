/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// line 82 "../../../../../../ParkingLot.ump"
@Entity
@NoArgsConstructor
public class ServiceAppointment
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum AppointmentStatus { Ready, InProgress, Completed }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceAppointment Attributes
  private Long serviceAppointmentID;
  private LocalDateTime startTime;
  private AppointmentStatus appointmentStatus;

  //ServiceAppointment Associations
  private Service service;
  private Garage garage;
  private Car car;

  // Constructor
  public ServiceAppointment(LocalDateTime aStartTime, AppointmentStatus aAppointmentStatus)
  {
    startTime = aStartTime;
    appointmentStatus = aAppointmentStatus;
  }


  //------------------------
  // INTERFACE
  //------------------------

  public boolean setServiceAppointmentID(Long aServiceAppointmentID)
  {
    boolean wasSet = false;
    serviceAppointmentID = aServiceAppointmentID;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(LocalDateTime aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setAppointmentStatus(AppointmentStatus aAppointmentStatus)
  {
    boolean wasSet = false;
    appointmentStatus = aAppointmentStatus;
    wasSet = true;
    return wasSet;
  }

  @Id
  @GeneratedValue
  public Long getServiceAppointmentID()
  {
    return serviceAppointmentID;
  }

  public LocalDateTime getStartTime()
  {
    return startTime;
  }

  public AppointmentStatus getAppointmentStatus()
  {
    return appointmentStatus;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public Service getService()
  {
    return service;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public Garage getGarage()
  {
    return garage;
  }
  /* Code from template association_GetOne */

  @ManyToOne(optional = false)
  public Car getCar()
  {
    return car;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setService(Service aNewService)
  {
    boolean wasSet = false;
    if (aNewService != null)
    {
      service = aNewService;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setGarage(Garage aNewGarage)
  {
    boolean wasSet = false;
    if (aNewGarage != null)
    {
      garage = aNewGarage;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setCar(Car aNewCar)
  {
    boolean wasSet = false;
    if (aNewCar != null)
    {
      car = aNewCar;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    service = null;
    garage = null;
    car = null;
  }

}