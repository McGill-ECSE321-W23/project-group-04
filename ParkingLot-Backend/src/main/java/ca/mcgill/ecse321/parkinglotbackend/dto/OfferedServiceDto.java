package ca.mcgill.ecse321.parkinglotbackend.dto;

/**
 * This class implements the data transfer object class for OfferedService.
 *
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_exposing_service_functionality_via_a_restful_api
 */
public class OfferedServiceDto {
    private long serviceID;
    private String description;
    private float cost;
    private int duration;

    /**
     * This is the empty constructor for the offered service DTO
     *
     * @author Estefania Vazquez
     */
    public OfferedServiceDto() {
    }

    /**
     * This is the complete constructor for the offered service DTO
     *
     * @param serviceID - The unique ID of an offered service
     * @param description - A description of the service offered (like a name)
     * @param cost - The cost of the offered service
     * @param duration - The duration of the offered service
     *
     * @author Estefania Vazquez
     */
    public OfferedServiceDto(long serviceID, String description, float cost, int duration) {
        this.serviceID = serviceID;
        this.description = description;
        this.cost = cost;
        this.duration = duration;
    }

    /**
     * Getter for the offered service's ID
     *
     * @return - The unique ID of an offered service
     *
     * @author Estefania Vazquez
     */
    public long getOfferedServiceID() {
        return this.serviceID;
    }

    /**
     * Getter for the offered service's description
     *
     * @return - The description of an offered service
     *
     * @author Estefania Vazquez
     */
    public String getOfferedServiceDescription() {
        return this.description;
    }

    /**
     * Getter for the offered service's cost
     *
     * @return - The cost of an offered service
     *
     * @author Estefania Vazquez
     */
    public float getOfferedServiceCost() {
        return this.cost;
    }

    /**
     * Getter for the offered service's duration
     *
     * @return - The duration of an offered service
     *
     * @author Estefania Vazquez
     */
    public int getOfferedServiceDuration() {
        return this.duration;
    }
}
