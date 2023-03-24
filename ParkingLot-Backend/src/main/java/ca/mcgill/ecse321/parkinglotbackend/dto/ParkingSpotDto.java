package ca.mcgill.ecse321.parkinglotbackend.dto;

import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;

import java.time.LocalDateTime;


public class ParkingSpotDto {

    //ParkingSpot Attributes
  private long parkingSpotID;
  private int floor;
  private int number;

  //ParkingSpot Associations
  private MonthlyReservation monthlyReservation;


	public ParkingSpotDto() {
	}

	// @SuppressWarnings("unchecked")
	// public TicketDto(long aTicketID) {
		
	// }

	public ParkingSpotDto(long aParkingSpotID, int aFloor, int aNumber)  {
        parkingSpotID = aParkingSpotID;
        floor = aFloor;
        number = aNumber;
      }

    // getters

	public long getParkingSpotID() {
		return parkingSpotID;
	}

    public int getFloor() {

        return floor;
	}
    public int getNumber() {

        return number;
    }


    // setters

    public void setParkingSpotID(long aParkingSpotID) {

        parkingSpotID = aParkingSpotID;
	}

	public void setFloor(int aFloor) {

        floor = aFloor;
     }

     public void setNumber(int aNumber) {

        number = aNumber;
     }



}

