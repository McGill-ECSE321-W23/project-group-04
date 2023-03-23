package ca.mcgill.ecse321.parkinglotbackend.dto;

public class CarDto {
    
    private long carID;
    private String licensePlate;
    private String make;
    private String model;

    private PersonDto owner;

    public CarDto(){
    }

    public CarDto(long carID, String licensePlate, String make, String model, PersonDto owner){
        this.carID = carID;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.owner = owner;
    }

    public long getCarID() {
        return carID;
    }

    public void setCarID (long newID) {
        this.carID = newID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate (String newLicensePlate) {
        this.licensePlate = newLicensePlate;
    }


    public String getMake() {
        return make;
    }

    public void setMake (String newMake) {
        this.make = newMake;
    }

    
    public String getModel() {
        return model;
    }

    public void setModel (String newModel) {
        this.model = newModel;
    }

    public void setOwner (PersonDto newOwner){
        this.owner = newOwner;
    }

}
