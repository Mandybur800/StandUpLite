package ua.com.conductor.service.dtomappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.conductor.model.StandUpSession;
import ua.com.conductor.model.dto.StandUpSessionRequestDto;
import ua.com.conductor.model.dto.StandUpSessionResponseDto;
import ua.com.conductor.service.EventService;
import ua.com.conductor.service.LocationService;

@Component
public class StandUpSessionMapper {
    private final EventService eventService;
    private final LocationService locationService;

    @Autowired
    public StandUpSessionMapper(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    public StandUpSession toEntity(StandUpSessionRequestDto standUpSessionRequestDto) {
        StandUpSession standUpSession = new StandUpSession();
        standUpSession.setMovie(eventService.get(standUpSessionRequestDto.getEventId()));
        LocalDateTime dateTime = LocalDateTime.parse(standUpSessionRequestDto.getShowTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        standUpSession.setShowTime(dateTime);
        standUpSession.setCinemaHall(locationService.get(standUpSessionRequestDto.getLocationId()));
        return standUpSession;
    }

    public StandUpSessionResponseDto toDto(StandUpSession standUpSession) {
        StandUpSessionResponseDto dto = new StandUpSessionResponseDto();
        dto.setEventId(standUpSession.getMovie().getId());
        dto.setTitle(standUpSession.getMovie().getTitle());
        dto.setSessionId(standUpSession.getId());
        dto.setLocationId(standUpSession.getCinemaHall().getId());
        dto.setShowTime(standUpSession.getShowTime().toString());
        return dto;
    }
}
