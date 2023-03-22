package ca.mcgill.ecse321.parkinglotbackend.dto;

import java.util.Collections;
import java.util.List;

public class OfferedServiceDto {
    private long serviceID;
    private List<OfferedServiceDto> offeredServices;

    public OfferedServiceDto() {
    }

    @SuppressWarnings("unchecked")
    public OfferedServiceDto(long serviceID) {
        this(serviceID, Collections.EMPTY_LIST);
    }

    public OfferedServiceDto(long serviceID, List<OfferedServiceDto> offeredServices) {
        this.serviceID = serviceID;
        this.offeredServices = offeredServices;
    }

    public long getOfferedServiceID() {
        return this.serviceID;
    }

    public List<OfferedServiceDto> getOfferedServices() {
        return this.offeredServices;
    }

    public void setOfferedServices(List<OfferedServiceDto> offeredServices) {
        this.offeredServices = offeredServices;
    }

}
