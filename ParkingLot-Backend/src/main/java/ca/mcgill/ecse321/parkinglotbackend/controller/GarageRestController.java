package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.GarageDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;

import ca.mcgill.ecse321.parkinglotbackend.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/account")
public class GarageRestController {
    @Autowired
    private GarageService garageService;

    // Get a garage by id
    @GetMapping(value = { "/garages/{garageID}", "/garages/{garageID}/" })
    public GarageDto getGarageByID(@PathVariable("garageID") long garageID) {
        return convertToDto(garageService.getGarageService(garageID));
    }

    // Get all of the garages
    @GetMapping(value = { "/garages", "/garages/" })
    public List<GarageDto> getAllGarages() {
        return garageService.getAllGarageService().stream().map(g -> convertToDto(g)).collect(Collectors.toList());
    }

    // Creating a garage
    @PostMapping(value = { "/garages/{garageID}", "/garages/{garageID}/" })
    public GarageDto createGarage(@RequestParam int garageNumber) throws Exception {
        Garage garage = garageService.createGarageService(garageNumber);
        return convertToDto(garage);
    }

    // Deleting a garage
    @PostMapping(value = { "/garages/{garageID}", "/garages/{garageID}/" })
    public GarageDto deleteGarage(@RequestParam long garageID) throws Exception {
        Garage garage = garageService.deleteGarageService(garageID);
        return convertToDto(garage);
    }

    // Modifying a garage
    @PostMapping(value = { "/garages/{garageID}", "/garages/{garageID}/" })
    public GarageDto modifyGarage(@RequestParam long garageID, @RequestParam int garageNumber) throws Exception {
        Garage garage = garageService.modifyGarage(garageID, garageNumber);
        return convertToDto(garage);
    }

    private GarageDto convertToDto(Garage g) {
        if (g == null) {
            throw new IllegalArgumentException("Garage does not exist.");
        }
        GarageDto garageDto = new GarageDto(g.getGarageID());
        return garageDto;
    }
}
