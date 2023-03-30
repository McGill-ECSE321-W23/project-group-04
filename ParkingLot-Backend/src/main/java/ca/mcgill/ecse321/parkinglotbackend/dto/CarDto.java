package ca.mcgill.ecse321.parkinglotbackend.dto;


public class CarDto {
    
    private long carID;
    private String licensePlate;
    private String make;
    private String model;
    private PersonDto owner;

    public CarDto(){
    }

    /**
     * Constructor for Car DTO
     * 
     * @param carID
     * @param licensePlate
     * @param make
     * @param model
     * @param owner
     * @author anniegouchee
     */
    public CarDto(long carID, String licensePlate, String make, String model, PersonDto owner){
        this.carID = carID;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.owner = owner;
    }

    //Getters and setters for Car DTO
    
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

    public PersonDto getOwner() {
        return owner;
    }

    public void setOwner (PersonDto newOwner){
        this.owner = newOwner;
    }

}
