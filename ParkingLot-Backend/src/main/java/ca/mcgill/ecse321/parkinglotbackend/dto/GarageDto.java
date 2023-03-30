package ca.mcgill.ecse321.parkinglotbackend.dto;

/**
 * This class implements the data transfer object class for Garage.
 *
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_exposing_service_functionality_via_a_restful_api
 */
public class GarageDto {
    private long garageID;
    private int garageNumber;

    /**
     * This is the empty constructor for the garage DTO
     *
     * @author Estefania Vazquez
     */
    public GarageDto() {
    }

    /**
     * This is the full constructor for the garage DTO
     *
     * @param garageID - The unique ID of a garage
     * @param garageNumber - The number (like a name) assigned to the garage
     *
     * @author Estefania Vazquez
     */
    public GarageDto(long garageID, int garageNumber) {
        this.garageID = garageID;
        this.garageNumber = garageNumber;
    }

    /**
     * Getter for the garage's ID
     *
     * @return - The unique ID of a garage
     *
     * @author Estefania Vazquez
     */
    public long getGarageID() {
        return this.garageID;
    }

    /**
     * Getter for the garage's number
     *
     * @return - The number (like a name) assigned to the garage
     *
     * @author Estefania Vazquez
     */
    public int getGarageNumber() {
        return this.garageNumber;
    }
}
