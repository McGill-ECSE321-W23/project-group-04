package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.GarageDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import ca.mcgill.ecse321.parkinglotbackend.service.GarageDeletionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class GarageDeletionRestController {
    @Autowired
    private GarageDeletionService garageDeletionService;

    // Get a garage by id
    @GetMapping(value = { "/garage/{garageID}", "/garage/{garageID}/" })
    public GarageDto getGarageByID(@PathVariable("garageID") long garageID) {
        return convertToDto(garageDeletionService.getGarageService(garageID));
    }

    // Get all of the garages
    @GetMapping(value = { "/garages", "/garages/" })
    public List<GarageDto> getAllGarages() {
        return garageDeletionService.getAllGarageService().stream().map(g -> convertToDto(g)).collect(Collectors.toList());
    }

    // Deleting an offered service
    @PostMapping(value = { "/garages/{garageID}", "/garages/{garageID}/" })
    public GarageDto deleteGarage(@RequestParam long garageID) throws Exception {
        Garage garage = garageDeletionService.deleteGarageService(garageID);
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
