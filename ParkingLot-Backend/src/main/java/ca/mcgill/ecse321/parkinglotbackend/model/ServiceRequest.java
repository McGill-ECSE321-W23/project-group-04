/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

// line 74 "../../../../../ParkingLot.ump"
public class ServiceRequest
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextServiceRequestID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceRequest Attributes
  private datetime startTime;

  //Autounique Attributes
  private int ServiceRequestID;

  //ServiceRequest Associations
  private Car car;
  private Service service;
  private Garage garage;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceRequest(datetime aStartTime, Car aCar, Service aService, Garage aGarage)
  {
    startTime = aStartTime;
    ServiceRequestID = nextServiceRequestID++;
    if (!setCar(aCar))
    {
      throw new RuntimeException("Unable to create ServiceRequest due to aCar. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setService(aService))
    {
      throw new RuntimeException("Unable to create ServiceRequest due to aService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setGarage(aGarage))
    {
      throw new RuntimeException("Unable to create ServiceRequest due to aGarage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(datetime aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public datetime getStartTime()
  {
    return startTime;
  }

  public int getServiceRequestID()
  {
    return ServiceRequestID;
  }
  /* Code from template association_GetOne */
  public Car getCar()
  {
    return car;
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

  public void delete()
  {
    car = null;
    service = null;
    garage = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "ServiceRequestID" + ":" + getServiceRequestID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "service = "+(getService()!=null?Integer.toHexString(System.identityHashCode(getService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null");
  }
}