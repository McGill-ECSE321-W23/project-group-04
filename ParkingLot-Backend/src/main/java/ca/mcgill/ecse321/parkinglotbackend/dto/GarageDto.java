package ca.mcgill.ecse321.parkinglotbackend.dto;

import java.util.Collections;
import java.util.List;

public class GarageDto {
    private long garageID;
    private List<GarageDto> garages;

    public GarageDto() {
    }

    @SuppressWarnings("unchecked")
    public GarageDto(long garageID) {
        this(garageID, Collections.EMPTY_LIST);
    }

    public GarageDto(long garageID, List<GarageDto> garages) {
        this.garageID = garageID;
        this.garages = garages;
    }

    public long getGarageID() {
        return this.garageID;
    }

    public List<GarageDto> getGarages() {
        return this.garages;
    }

    public void setGarages(List<GarageDto> garages) {
        this.garages = garages;
    }

}
