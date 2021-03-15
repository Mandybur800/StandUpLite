package ua.com.conductor.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.conductor.model.Location;
import ua.com.conductor.model.dto.LocationRequestDto;
import ua.com.conductor.model.dto.LocationResponseDto;
import ua.com.conductor.service.LocationService;
import ua.com.conductor.service.dtomappers.LocationMapper;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @Autowired
    public LocationController(LocationService locationService,
                              LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @GetMapping
    public List<LocationResponseDto> getAllLocations() {
        return locationService.getAll()
                .stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createLocation(@RequestBody @Valid LocationRequestDto locationRequestDto) {
        Location location = locationMapper.toEntity(locationRequestDto);
        locationService.add(location);
    }
}
