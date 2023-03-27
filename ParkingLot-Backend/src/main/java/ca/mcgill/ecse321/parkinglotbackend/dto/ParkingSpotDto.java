package ca.mcgill.ecse321.parkinglotbackend.dto;

import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;

public class ParkingSpotDto {

private long parkingSpotID;
private int floor;
private int number;


private MonthlyReservation monthlyReservation;

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

public MonthlyReservation getMonthlyReservation()
{
  return monthlyReservation;
}

public boolean hasMonthlyReservation()
{
  boolean has = monthlyReservation != null;
  return has;
}

public boolean setMonthlyReservation(MonthlyReservation aNewMonthlyReservation)
{
  boolean wasSet = false;
  monthlyReservation = aNewMonthlyReservation;
  wasSet = true;
  return wasSet;
}


}