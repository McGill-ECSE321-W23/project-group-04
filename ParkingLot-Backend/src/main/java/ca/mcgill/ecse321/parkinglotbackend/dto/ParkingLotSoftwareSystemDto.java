package ca.mcgill.ecse321.parkinglotbackend.dto;

public class ParkingLotSoftwareSystemDto {
    //ParkingLotSoftwareSystem Attributes
    private long parkingLotSoftwareSystemID;
    private float monthlyFee;
    private float feePer15m;
    private int maxStay;
    private int numberOfRegularParkingSpots;
    private int numberOfLargeParkingSpots;
    private int numberOfMonthlyFloors;
    private int numberOfMonthlySpotsPerFloor;
    private int numberOfGarages;

    public ParkingLotSoftwareSystemDto() {
    }

    public ParkingLotSoftwareSystemDto(long parkingLotSoftwareSystemID, float monthlyFee, float feePer15m, int maxStay,
                                       int numberOfRegularParkingSpots, int numberOfLargeParkingSpots, int numberOfMonthlyFloors,
                                       int numberOfMonthlySpotsPerFloor, int numberOfGarages) {
        this.parkingLotSoftwareSystemID = parkingLotSoftwareSystemID;
        this.monthlyFee = monthlyFee;
        this.feePer15m = feePer15m;
        this.maxStay = maxStay;
        this.numberOfRegularParkingSpots = numberOfRegularParkingSpots;
        this.numberOfLargeParkingSpots = numberOfLargeParkingSpots;
        this.numberOfMonthlyFloors = numberOfMonthlyFloors;
        this.numberOfMonthlySpotsPerFloor = numberOfMonthlySpotsPerFloor;
        this.numberOfGarages = numberOfGarages;
    }

    public long getParkingLotSoftwareSystemID() {
        return parkingLotSoftwareSystemID;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public float getFeePer15m() {
        return feePer15m;
    }

    public int getMaxStay() {
        return maxStay;
    }

    public int getNumberOfRegularParkingSpots() {
        return numberOfRegularParkingSpots;
    }

    public int getNumberOfLargeParkingSpots() {
        return numberOfLargeParkingSpots;
    }

    public int getNumberOfMonthlyFloors() {
        return numberOfMonthlyFloors;
    }

    public int getNumberOfMonthlySpotsPerFloor() {
        return numberOfMonthlySpotsPerFloor;
    }

    public int getNumberOfGarages() {
        return numberOfGarages;
    }

    public void setParkingLotSoftwareSystemID(long parkingLotSoftwareSystemID) {
        this.parkingLotSoftwareSystemID = parkingLotSoftwareSystemID;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void setFeePer15m(float feePer15m) {
        this.feePer15m = feePer15m;
    }

    public void setMaxStay(int maxStay) {
        this.maxStay = maxStay;
    }

    public void setNumberOfRegularParkingSpots(int numberOfRegularParkingSpots) {
        this.numberOfRegularParkingSpots = numberOfRegularParkingSpots;
    }

    public void setNumberOfLargeParkingSpots(int numberOfLargeParkingSpots) {
        this.numberOfLargeParkingSpots = numberOfLargeParkingSpots;
    }

    public void setNumberOfMonthlyFloors(int numberOfMonthlyFloors) {
        this.numberOfMonthlyFloors = numberOfMonthlyFloors;
    }

    public void setNumberOfMonthlySpotsPerFloor(int numberOfMonthlySpotsPerFloor) {
        this.numberOfMonthlySpotsPerFloor = numberOfMonthlySpotsPerFloor;
    }

    public void setNumberOfGarages(int numberOfGarages) {
        this.numberOfGarages = numberOfGarages;
    }

}