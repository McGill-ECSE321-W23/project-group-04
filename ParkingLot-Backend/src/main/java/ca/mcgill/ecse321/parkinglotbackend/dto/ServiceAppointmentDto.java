package ca.mcgill.ecse321.parkinglotbackend.dto;

import java.time.LocalDateTime;

public class ServiceAppointmentDto {

    public enum AppointmentStatusDto { Ready, InProgress, Completed }

    private long serviceAppointmentID;
    private LocalDateTime startTime;
    private AppointmentStatusDto appointmentStatus;

    private OfferedServiceDto service;
    private GarageDto garage;
    private CarDto car;

    public ServiceAppointmentDto(){
    }

    /**
     * Service DTO constructor
     * @param serviceAppointmentID 
     * @param startTime 
     * @param appointmentStatus
     * @param service
     * @param garage
     * @param car
     * @author anniegouchee
     */
    public ServiceAppointmentDto(long serviceAppointmentID, LocalDateTime startTime, AppointmentStatusDto appointmentStatus, 
        OfferedServiceDto service, GarageDto garage, CarDto car){
        this.serviceAppointmentID = serviceAppointmentID;
        this.startTime = startTime;
        this.appointmentStatus = appointmentStatus;
        this.service = service;
        this.garage = garage;
        this.car = car;
    }

    //Getters and setters for DTO
    
    public long getServiceAppointmentID() {
        return serviceAppointmentID;
    }

    public void setServiceAppointmentID (long newID) {
        this.serviceAppointmentID = newID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime (LocalDateTime newStartTime) {
        this.startTime = newStartTime;
    }


    public AppointmentStatusDto getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus (AppointmentStatusDto newAppointmentStatus) {
        this.appointmentStatus = newAppointmentStatus;
    }
    
    public OfferedServiceDto getService() {
        return service;
    }

    public void setService (OfferedServiceDto newService) {
        this.service = newService;
    }

    public GarageDto getGarage() {
        return garage;
    }

    public void setGarage (GarageDto newGarage) {
        this.garage = newGarage;
    }
    public CarDto getCar() {
        return car;
    }

    public void setCar (CarDto newCar) {
        this.car = newCar;
    }
}
