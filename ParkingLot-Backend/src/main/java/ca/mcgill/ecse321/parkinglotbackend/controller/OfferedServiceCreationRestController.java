package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.OfferedServiceDto;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.service.OfferedServiceCreationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class OfferedServiceCreationRestController {
    @Autowired
    private OfferedServiceCreationService offeredServiceCreationService;

    // Get an offered service by id
    @GetMapping(value = { "/offeredService/{offeredServiceID}", "/offeredService/{offeredServiceID}/" })
    public OfferedServiceDto getOfferedServiceByID(@PathVariable("offeredServiceID") long offeredServiceID) {
        return convertToDto(offeredServiceCreationService.getOfferedServiceService(offeredServiceID));
    }

    // Get all of the offered services
    @GetMapping(value = { "/offeredServices", "/offeredServices/" })
    public List<OfferedServiceDto> getAllOfferedServices() {
        return offeredServiceCreationService.getAllOfferedServiceService().stream().map(os -> convertToDto(os)).collect(Collectors.toList());
    }

    // Creating an offered service
    @PostMapping(value = { "/offeredServices/{offeredServiceID}", "/offeredServices/{offeredServiceID}/" })
    public OfferedServiceDto createOfferedService(@RequestParam String description, @RequestParam float cost,
                                                  @RequestParam int duration) throws Exception {
        OfferedService offeredService = offeredServiceCreationService.createOfferedServiceService(description, cost, duration);
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
