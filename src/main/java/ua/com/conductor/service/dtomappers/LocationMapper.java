package ua.com.conductor.service.dtomappers;

import org.springframework.stereotype.Component;
import ua.com.conductor.model.Location;
import ua.com.conductor.model.dto.LocationRequestDto;
import ua.com.conductor.model.dto.LocationResponseDto;

@Component
public class LocationMapper {
    public Location toEntity(LocationRequestDto locationRequestDto) {
        Location location = new Location();
        location.setCapacity(locationRequestDto.getCapacity());
        location.setDescription(locationRequestDto.getDescription());
        location.setAddress(locationRequestDto.getAddress());
        return location;
    }

    public LocationResponseDto toDto(Location location) {
        LocationResponseDto locationResponseDto = new LocationResponseDto();
        locationResponseDto.setId(location.getId());
        locationResponseDto.setCapacity(location.getCapacity());
        locationResponseDto.setDescription(location.getDescription());
        locationResponseDto.setAddress(location.getAddress());
        return locationResponseDto;
    }
}
