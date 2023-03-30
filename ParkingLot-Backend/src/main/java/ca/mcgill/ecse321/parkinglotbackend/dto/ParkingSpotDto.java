/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.dto;

public class ParkingSpotDto
{
  //ParkingSpot Attributes
  private Long parkingSpotID;
  private Integer floor;
  private Integer number;
  private MonthlyReservationDto monthlyReservationDto;

  public ParkingSpotDto(long aParkingSpotID, int aFloor, int aNumber)
  {
    parkingSpotID = aParkingSpotID;
    floor = aFloor;
    number = aNumber;
  }

  public void setParkingSpotID(long aParkingSpotID)
  {
    parkingSpotID = aParkingSpotID;
  }

  public void setFloor(int aFloor)
  {
    floor = aFloor;
  }

  public void setNumber(int aNumber)
  {
    number = aNumber;
  }

  public Long getParkingSpotID()
  {
    return parkingSpotID;
  }

  public Integer getFloor()
  {
    return floor;
  }

  public Integer getNumber()
  {
    return number;
  }

  public MonthlyReservationDto getMonthlyReservationDto()
  {
    return monthlyReservationDto;
  }

  public boolean hasMonthlyReservation()
  {
    return monthlyReservationDto != null;
  }
  public void setMonthlyReservation(MonthlyReservationDto aNewMonthlyReservation)
  {
    monthlyReservationDto = aNewMonthlyReservation;
  }

  public static boolean isValid(ParkingSpotDto parkingSpotDto) {
    return parkingSpotDto != null &&
            parkingSpotDto.getParkingSpotID() != null &&
            parkingSpotDto.getFloor() != null &&
            parkingSpotDto.number != null &&
            MonthlyReservationDto.isValid(parkingSpotDto.getMonthlyReservationDto());
  }
}