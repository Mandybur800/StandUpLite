package ua.com.conductor.service.dtomappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.conductor.model.Session;
import ua.com.conductor.model.dto.SessionRequestDto;
import ua.com.conductor.model.dto.SessionResponseDto;
import ua.com.conductor.service.EventService;
import ua.com.conductor.service.LocationService;

@Component
public class SessionMapper {
    private final EventService eventService;
    private final LocationService locationService;

    @Autowired
    public SessionMapper(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    public Session toEntity(SessionRequestDto sessionRequestDto) {
        Session session = new Session();
        session.setMovie(eventService.get(sessionRequestDto.getEventId()));
        LocalDateTime dateTime = LocalDateTime.parse(sessionRequestDto.getShowTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        session.setShowTime(dateTime);
        session.setCinemaHall(locationService.get(sessionRequestDto.getLocationId()));
        return session;
    }

    public SessionResponseDto toDto(Session session) {
        SessionResponseDto dto = new SessionResponseDto();
        dto.setEventId(session.getMovie().getId());
        dto.setTitle(session.getMovie().getTitle());
        dto.setSessionId(session.getId());
        dto.setLocationId(session.getCinemaHall().getId());
        dto.setShowTime(session.getShowTime().toString());
        return dto;
    }
}
