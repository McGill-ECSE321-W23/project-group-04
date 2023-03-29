package ca.mcgill.ecse321.parkinglotbackend.dto;


/**
 * Parking Spot Data Transfer Object
 * @author faizachowdhury
 */
public class ParkingSpotDto {

private long parkingSpotID;
private int floor;
private int number;

// parking spot association
private  MonthlyReservationDto monthlyReservationDto;


// constructors
public ParkingSpotDto(long aParkingSpotID, int aFloor, int aNumber)
{
  parkingSpotID = aParkingSpotID;
  floor = aFloor;
  number = aNumber;
}
public ParkingSpotDto(int aFloor, int aNumber)
{
  floor = aFloor;
  number = aNumber;
}
public ParkingSpotDto() {}

  // setters
public boolean setParkingSpotID(long aParkingSpotID)
{
  boolean wasSet = false;
  parkingSpotID = aParkingSpotID;
  wasSet = true;
  return wasSet;
}

public boolean setFloor(int aFloor)
{
  boolean wasSet = false;
  floor = aFloor;
  wasSet = true;
  return wasSet;
}

public boolean setNumber(int aNumber)
{
  boolean wasSet = false;
  number = aNumber;
  wasSet = true;
  return wasSet;
}

// getters
public long getParkingSpotID()
{
  return parkingSpotID;
}

public int getFloor()
{
  return floor;
}

public int getNumber()
{
  return number;
}


public MonthlyReservationDto getMonthlyReservation()
{
  return monthlyReservationDto;
}

public boolean hasMonthlyReservation()
{
  boolean has = monthlyReservationDto != null;
  return has;
}

public boolean setMonthlyReservation(MonthlyReservationDto aNewMonthlyReservationDto)
{
  boolean wasSet = false;
  monthlyReservationDto = aNewMonthlyReservationDto;
  wasSet = true;
  return wasSet;
}


}