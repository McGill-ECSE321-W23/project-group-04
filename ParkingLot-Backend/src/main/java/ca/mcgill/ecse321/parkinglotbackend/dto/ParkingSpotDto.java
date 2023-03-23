package ca.mcgill.ecse321.parkinglotbackend.dto;

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

	public LocalDateTime getParkingSpotID() {
		return parkingSpotID;
	}

    public CarType getFloor() {
		return floor;
	}
    public void getNumber() {
        return number;
    }

    public void getSystem(){
        return parkingLotSoftwareSystem;
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

     public void setSystem(ParkingLotSoftwareSystem aNewParkingLotSoftwareSystem) {
        if (aNewParkingLotSoftwareSystem != null) {
            parkingLotSoftwareSystem = aNewParkingLotSoftwareSystem;
           
        }
    }


}

