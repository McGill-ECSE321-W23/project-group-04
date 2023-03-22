package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.GarageDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import ca.mcgill.ecse321.parkinglotbackend.service.GarageModificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class GarageModificationRestController {
    @Autowired
    private GarageModificationService garageModificationService;

    // Get a garage by id
    @GetMapping(value = { "/garage/{garageID}", "/garage/{garageID}/" })
    public GarageDto getGarageByID(@PathVariable("garageID") long garageID) {
        return convertToDto(garageModificationService.getGarageService(garageID));
    }

    // Get all of the garages
    @GetMapping(value = { "/garages", "/garages/" })
    public List<GarageDto> getAllGarages() {
        return garageModificationService.getAllGarageService().stream().map(g -> convertToDto(g)).collect(Collectors.toList());
    }

    // Modifying a garage
    @PostMapping(value = { "/garage/{garageID}", "/garage/{garageID}/" })
    public GarageDto modifyGarage(@RequestParam long garageID, @RequestParam int garageNumber) throws Exception {
        Garage garage = garageModificationService.modifyGarage(garageID, garageNumber);
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
