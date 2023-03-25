package ca.mcgill.ecse321.parkinglotbackend.dto;

import java.time.LocalDateTime;

import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;


public class ServiceAppointmentDto {

    private enum AppointmentStatus { Ready, InProgress, Completed }

    private long serviceAppointmentID;
    private LocalDateTime startTime;
    private AppointmentStatus appointmentStatus;

    private OfferedServiceDto service;
    private GarageDto garage;
    private CarDto car;

    public ServiceAppointmentDto(){
    }

    public ServiceAppointmentDto(long serviceAppointmentID, LocalDateTime startTime, AppointmentStatus appointmentStatus, 
        OfferedServiceDto service, GarageDto garage, CarDto car){
        this.serviceAppointmentID = serviceAppointmentID;
        this.startTime = startTime;
        this.appointmentStatus = appointmentStatus;
        this.service = service;
        this.garage = garage;
        this.car = car;
    }

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


    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus (AppointmentStatus newAppointmentStatus) {
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

    public void setService (CarDto newCar) {
        this.car = newCar;
    }



}
