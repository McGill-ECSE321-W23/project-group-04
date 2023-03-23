package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.OfferedServiceDto;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.service.OfferedServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/account")
public class OfferedServiceRestController {
    @Autowired
    private OfferedServiceService offeredServiceService;

    // Get an offered service by id
    @GetMapping(value = { "/offeredServices/{offeredServiceID}", "/offeredServices/{offeredServiceID}/" })
    public OfferedServiceDto getOfferedServiceByID(@PathVariable("offeredServiceID") long offeredServiceID) {
        return convertToDto(offeredServiceService.getOfferedServiceService(offeredServiceID));
    }

    // Get all of the offered services
    @GetMapping(value = { "/offeredServices", "/offeredServices/" })
    public List<OfferedServiceDto> getAllOfferedServices() {
        return offeredServiceService.getAllOfferedServiceService().stream().map(os -> convertToDto(os)).collect(Collectors.toList());
    }

    // Deleting an offered service
    @PostMapping(value = { "/offeredServices/{offeredServiceID}", "/offeredServices/{offeredServiceID}/" })
    public OfferedServiceDto deleteOfferedService(@RequestParam long serviceID) throws Exception {
        OfferedService offeredService = offeredServiceService.deleteOfferedServiceService(serviceID);
        return convertToDto(offeredService);
    }

    // Modifying an offered service
    @PostMapping(value = { "/offeredServices/{offeredServiceID}", "/offeredServices/{offeredServiceID}/" })
    public OfferedServiceDto modifyOfferedService(@RequestParam long serviceID, @RequestParam String description,
                                                  @RequestParam float cost, @RequestParam int duration) throws Exception {
        OfferedService offeredService = offeredServiceService.modifyOfferedServiceService(serviceID, description, cost, duration);
        return convertToDto(offeredService);
    }

    // Creating an offered service
    @PostMapping(value = { "/offeredServices/{offeredServiceID}", "/offeredServices/{offeredServiceID}/" })
    public OfferedServiceDto createOfferedService(@RequestParam String description, @RequestParam float cost,
                                                  @RequestParam int duration) throws Exception {
        OfferedService offeredService = offeredServiceService.createOfferedServiceService(description, cost, duration);
        return convertToDto(offeredService);
    }

    private OfferedServiceDto convertToDto(OfferedService os) {
        if (os == null) {
            throw new IllegalArgumentException("Service does not exist.");
        }
        OfferedServiceDto offeredServiceDto = new OfferedServiceDto(os.getServiceID());
        return offeredServiceDto;
    }
}
