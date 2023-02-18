/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

// line 30 "../../../../../ParkingLot.ump"
public class CustomerRole extends Role
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CustomerRole Associations
  private List<ParkingSpot> reservations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CustomerRole()
  {
    super();
    reservations = new ArrayList<ParkingSpot>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public ParkingSpot getReservation(int index)
  {
    ParkingSpot aReservation = reservations.get(index);
    return aReservation;
  }

  public List<ParkingSpot> getReservations()
  {
    List<ParkingSpot> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(ParkingSpot aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addReservation(ParkingSpot aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    reservations.add(aReservation);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservation(ParkingSpot aReservation)
  {
    boolean wasRemoved = false;
    if (reservations.contains(aReservation))
    {
      reservations.remove(aReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReservationAt(ParkingSpot aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(ParkingSpot aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    reservations.clear();
    super.delete();
  }

}