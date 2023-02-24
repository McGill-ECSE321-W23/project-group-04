/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;

import java.time.LocalDateTime;

// line 82 "../../../../../../ParkingLot.ump"
public class ServiceAppointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceAppointment Attributes
  private String serviceAppointmentID;
  private LocalDateTime startTime;

  //ServiceAppointment Associations
  private Service service;
  private Garage garage;
  private Car car;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceAppointment(String aServiceAppointmentID, LocalDateTime aStartTime, Service aService, Garage aGarage, Car aCar)
  {
    serviceAppointmentID = aServiceAppointmentID;
    startTime = aStartTime;
    if (!setService(aService))
    {
      throw new RuntimeException("Unable to create ServiceAppointment due to aService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setGarage(aGarage))
    {
      throw new RuntimeException("Unable to create ServiceAppointment due to aGarage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setCar(aCar))
    {
      throw new RuntimeException("Unable to create ServiceAppointment due to aCar. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setServiceAppointmentID(String aServiceAppointmentID)
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

  public String getServiceAppointmentID()
  {
    return serviceAppointmentID;
  }

  public LocalDateTime getStartTime()
  {
    return startTime;
  }
  /* Code from template association_GetOne */
  public Service getService()
  {
    return service;
  }
  /* Code from template association_GetOne */
  public Garage getGarage()
  {
    return garage;
  }
  /* Code from template association_GetOne */
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


  public String toString()
  {
    return super.toString() + "["+
            "serviceAppointmentID" + ":" + getServiceAppointmentID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "service = "+(getService()!=null?Integer.toHexString(System.identityHashCode(getService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null");
  }
}