package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.OfferedServiceDto;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.service.OfferedServiceDeletionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class OfferedServiceDeletionRestController {
    @Autowired
    private OfferedServiceDeletionService offeredServiceDeletionService;

    // Get an offered service by id
    @GetMapping(value = { "/offeredService/{offeredServiceID}", "/offeredService/{offeredServiceID}/" })
    public OfferedServiceDto getOfferedServiceByID(@PathVariable("offeredServiceID") long offeredServiceID) {
        return convertToDto(offeredServiceDeletionService.getOfferedServiceService(offeredServiceID));
    }

    // Get all of the offered services
    @GetMapping(value = { "/offeredServices", "/offeredServices/" })
    public List<OfferedServiceDto> getAllOfferedServices() {
        return offeredServiceDeletionService.getAllOfferedServiceService().stream().map(os -> convertToDto(os)).collect(Collectors.toList());
    }

    // Deleting an offered service
    @PostMapping(value = { "/offeredServices/{offeredServiceID}", "/offeredServices/{offeredServiceID}/" })
    public OfferedServiceDto deleteOfferedService(@RequestParam long serviceID) throws Exception {
        OfferedService offeredService = offeredServiceDeletionService.deleteOfferedServiceService(serviceID);
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
