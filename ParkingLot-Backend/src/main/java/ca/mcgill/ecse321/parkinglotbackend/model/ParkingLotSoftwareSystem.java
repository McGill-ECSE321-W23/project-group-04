/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

// line 3 "../../../../../ParkingLot.ump"
public class ParkingLotSoftwareSystem
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextParkingLotSoftwareSystemID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingLotSoftwareSystem Attributes
  private float monthlyFee;
  private float feePer15m;
  private int maxStay;
  private int numberOfRegularParkingSpots;
  private int numberOfLargeParkingSpots;
  private int numberOfMonthlyFloors;
  private int numberOfMonthlySpotsPerFloor;
  private int numberOfGarages;
  private int maxCarsPerPerson;

  //Autounique Attributes
  private int ParkingLotSoftwareSystemID;

  //ParkingLotSoftwareSystem Associations
  private List<TimeSlot> openingHours;
  private List<TicketForRegularCar> ticketForRegularCars;
  private List<TicketForLargeCar> ticketForLargeCars;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ParkingLotSoftwareSystem(float aMonthlyFee, float aFeePer15m, int aMaxStay, int aNumberOfRegularParkingSpots, int aNumberOfLargeParkingSpots, int aNumberOfMonthlyFloors, int aNumberOfMonthlySpotsPerFloor, int aNumberOfGarages, int aMaxCarsPerPerson)
  {
    monthlyFee = aMonthlyFee;
    feePer15m = aFeePer15m;
    maxStay = aMaxStay;
    numberOfRegularParkingSpots = aNumberOfRegularParkingSpots;
    numberOfLargeParkingSpots = aNumberOfLargeParkingSpots;
    numberOfMonthlyFloors = aNumberOfMonthlyFloors;
    numberOfMonthlySpotsPerFloor = aNumberOfMonthlySpotsPerFloor;
    numberOfGarages = aNumberOfGarages;
    maxCarsPerPerson = aMaxCarsPerPerson;
    ParkingLotSoftwareSystemID = nextParkingLotSoftwareSystemID++;
    openingHours = new ArrayList<TimeSlot>();
    ticketForRegularCars = new ArrayList<TicketForRegularCar>();
    ticketForLargeCars = new ArrayList<TicketForLargeCar>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMonthlyFee(float aMonthlyFee)
  {
    boolean wasSet = false;
    monthlyFee = aMonthlyFee;
    wasSet = true;
    return wasSet;
  }

  public boolean setFeePer15m(float aFeePer15m)
  {
    boolean wasSet = false;
    feePer15m = aFeePer15m;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxStay(int aMaxStay)
  {
    boolean wasSet = false;
    maxStay = aMaxStay;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfRegularParkingSpots(int aNumberOfRegularParkingSpots)
  {
    boolean wasSet = false;
    numberOfRegularParkingSpots = aNumberOfRegularParkingSpots;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfLargeParkingSpots(int aNumberOfLargeParkingSpots)
  {
    boolean wasSet = false;
    numberOfLargeParkingSpots = aNumberOfLargeParkingSpots;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfMonthlyFloors(int aNumberOfMonthlyFloors)
  {
    boolean wasSet = false;
    numberOfMonthlyFloors = aNumberOfMonthlyFloors;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfMonthlySpotsPerFloor(int aNumberOfMonthlySpotsPerFloor)
  {
    boolean wasSet = false;
    numberOfMonthlySpotsPerFloor = aNumberOfMonthlySpotsPerFloor;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfGarages(int aNumberOfGarages)
  {
    boolean wasSet = false;
    numberOfGarages = aNumberOfGarages;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxCarsPerPerson(int aMaxCarsPerPerson)
  {
    boolean wasSet = false;
    maxCarsPerPerson = aMaxCarsPerPerson;
    wasSet = true;
    return wasSet;
  }

  public float getMonthlyFee()
  {
    return monthlyFee;
  }

  public float getFeePer15m()
  {
    return feePer15m;
  }

  /**
   * in minutes
   */
  public int getMaxStay()
  {
    return maxStay;
  }

  public int getNumberOfRegularParkingSpots()
  {
    return numberOfRegularParkingSpots;
  }

  public int getNumberOfLargeParkingSpots()
  {
    return numberOfLargeParkingSpots;
  }

  public int getNumberOfMonthlyFloors()
  {
    return numberOfMonthlyFloors;
  }

  public int getNumberOfMonthlySpotsPerFloor()
  {
    return numberOfMonthlySpotsPerFloor;
  }

  public int getNumberOfGarages()
  {
    return numberOfGarages;
  }

  public int getMaxCarsPerPerson()
  {
    return maxCarsPerPerson;
  }

  public int getParkingLotSoftwareSystemID()
  {
    return ParkingLotSoftwareSystemID;
  }
  /* Code from template association_GetMany */
  public TimeSlot getOpeningHour(int index)
  {
    TimeSlot aOpeningHour = openingHours.get(index);
    return aOpeningHour;
  }

  public List<TimeSlot> getOpeningHours()
  {
    List<TimeSlot> newOpeningHours = Collections.unmodifiableList(openingHours);
    return newOpeningHours;
  }

  public int numberOfOpeningHours()
  {
    int number = openingHours.size();
    return number;
  }

  public boolean hasOpeningHours()
  {
    boolean has = openingHours.size() > 0;
    return has;
  }

  public int indexOfOpeningHour(TimeSlot aOpeningHour)
  {
    int index = openingHours.indexOf(aOpeningHour);
    return index;
  }
  /* Code from template association_GetMany */
  public TicketForRegularCar getTicketForRegularCar(int index)
  {
    TicketForRegularCar aTicketForRegularCar = ticketForRegularCars.get(index);
    return aTicketForRegularCar;
  }

  public List<TicketForRegularCar> getTicketForRegularCars()
  {
    List<TicketForRegularCar> newTicketForRegularCars = Collections.unmodifiableList(ticketForRegularCars);
    return newTicketForRegularCars;
  }

  public int numberOfTicketForRegularCars()
  {
    int number = ticketForRegularCars.size();
    return number;
  }

  public boolean hasTicketForRegularCars()
  {
    boolean has = ticketForRegularCars.size() > 0;
    return has;
  }

  public int indexOfTicketForRegularCar(TicketForRegularCar aTicketForRegularCar)
  {
    int index = ticketForRegularCars.indexOf(aTicketForRegularCar);
    return index;
  }
  /* Code from template association_GetMany */
  public TicketForLargeCar getTicketForLargeCar(int index)
  {
    TicketForLargeCar aTicketForLargeCar = ticketForLargeCars.get(index);
    return aTicketForLargeCar;
  }

  public List<TicketForLargeCar> getTicketForLargeCars()
  {
    List<TicketForLargeCar> newTicketForLargeCars = Collections.unmodifiableList(ticketForLargeCars);
    return newTicketForLargeCars;
  }

  public int numberOfTicketForLargeCars()
  {
    int number = ticketForLargeCars.size();
    return number;
  }

  public boolean hasTicketForLargeCars()
  {
    boolean has = ticketForLargeCars.size() > 0;
    return has;
  }

  public int indexOfTicketForLargeCar(TicketForLargeCar aTicketForLargeCar)
  {
    int index = ticketForLargeCars.indexOf(aTicketForLargeCar);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOpeningHours()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addOpeningHour(TimeSlot aOpeningHour)
  {
    boolean wasAdded = false;
    if (openingHours.contains(aOpeningHour)) { return false; }
    openingHours.add(aOpeningHour);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOpeningHour(TimeSlot aOpeningHour)
  {
    boolean wasRemoved = false;
    if (openingHours.contains(aOpeningHour))
    {
      openingHours.remove(aOpeningHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOpeningHourAt(TimeSlot aOpeningHour, int index)
  {  
    boolean wasAdded = false;
    if(addOpeningHour(aOpeningHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOpeningHours()) { index = numberOfOpeningHours() - 1; }
      openingHours.remove(aOpeningHour);
      openingHours.add(index, aOpeningHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOpeningHourAt(TimeSlot aOpeningHour, int index)
  {
    boolean wasAdded = false;
    if(openingHours.contains(aOpeningHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOpeningHours()) { index = numberOfOpeningHours() - 1; }
      openingHours.remove(aOpeningHour);
      openingHours.add(index, aOpeningHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOpeningHourAt(aOpeningHour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTicketForRegularCars()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTicketForRegularCar(TicketForRegularCar aTicketForRegularCar)
  {
    boolean wasAdded = false;
    if (ticketForRegularCars.contains(aTicketForRegularCar)) { return false; }
    ticketForRegularCars.add(aTicketForRegularCar);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicketForRegularCar(TicketForRegularCar aTicketForRegularCar)
  {
    boolean wasRemoved = false;
    if (ticketForRegularCars.contains(aTicketForRegularCar))
    {
      ticketForRegularCars.remove(aTicketForRegularCar);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketForRegularCarAt(TicketForRegularCar aTicketForRegularCar, int index)
  {  
    boolean wasAdded = false;
    if(addTicketForRegularCar(aTicketForRegularCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicketForRegularCars()) { index = numberOfTicketForRegularCars() - 1; }
      ticketForRegularCars.remove(aTicketForRegularCar);
      ticketForRegularCars.add(index, aTicketForRegularCar);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketForRegularCarAt(TicketForRegularCar aTicketForRegularCar, int index)
  {
    boolean wasAdded = false;
    if(ticketForRegularCars.contains(aTicketForRegularCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicketForRegularCars()) { index = numberOfTicketForRegularCars() - 1; }
      ticketForRegularCars.remove(aTicketForRegularCar);
      ticketForRegularCars.add(index, aTicketForRegularCar);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTicketForRegularCarAt(aTicketForRegularCar, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTicketForLargeCars()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTicketForLargeCar(TicketForLargeCar aTicketForLargeCar)
  {
    boolean wasAdded = false;
    if (ticketForLargeCars.contains(aTicketForLargeCar)) { return false; }
    ticketForLargeCars.add(aTicketForLargeCar);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicketForLargeCar(TicketForLargeCar aTicketForLargeCar)
  {
    boolean wasRemoved = false;
    if (ticketForLargeCars.contains(aTicketForLargeCar))
    {
      ticketForLargeCars.remove(aTicketForLargeCar);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketForLargeCarAt(TicketForLargeCar aTicketForLargeCar, int index)
  {  
    boolean wasAdded = false;
    if(addTicketForLargeCar(aTicketForLargeCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicketForLargeCars()) { index = numberOfTicketForLargeCars() - 1; }
      ticketForLargeCars.remove(aTicketForLargeCar);
      ticketForLargeCars.add(index, aTicketForLargeCar);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketForLargeCarAt(TicketForLargeCar aTicketForLargeCar, int index)
  {
    boolean wasAdded = false;
    if(ticketForLargeCars.contains(aTicketForLargeCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicketForLargeCars()) { index = numberOfTicketForLargeCars() - 1; }
      ticketForLargeCars.remove(aTicketForLargeCar);
      ticketForLargeCars.add(index, aTicketForLargeCar);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTicketForLargeCarAt(aTicketForLargeCar, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    openingHours.clear();
    ticketForRegularCars.clear();
    ticketForLargeCars.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "ParkingLotSoftwareSystemID" + ":" + getParkingLotSoftwareSystemID()+ "," +
            "monthlyFee" + ":" + getMonthlyFee()+ "," +
            "feePer15m" + ":" + getFeePer15m()+ "," +
            "maxStay" + ":" + getMaxStay()+ "," +
            "numberOfRegularParkingSpots" + ":" + getNumberOfRegularParkingSpots()+ "," +
            "numberOfLargeParkingSpots" + ":" + getNumberOfLargeParkingSpots()+ "," +
            "numberOfMonthlyFloors" + ":" + getNumberOfMonthlyFloors()+ "," +
            "numberOfMonthlySpotsPerFloor" + ":" + getNumberOfMonthlySpotsPerFloor()+ "," +
            "numberOfGarages" + ":" + getNumberOfGarages()+ "," +
            "maxCarsPerPerson" + ":" + getMaxCarsPerPerson()+ "]";
  }
}