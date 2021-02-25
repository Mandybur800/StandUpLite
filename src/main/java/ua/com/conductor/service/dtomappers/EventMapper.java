package ua.com.conductor.service.dtomappers;

import org.springframework.stereotype.Component;
import ua.com.conductor.model.Event;
import ua.com.conductor.model.dto.EventRequestDto;
import ua.com.conductor.model.dto.EventResponseDto;

@Component
public class EventMapper {
    public Event toEntity(EventRequestDto eventRequestDto) {
        Event event = new Event();
        event.setDescription(eventRequestDto.getDescription());
        event.setTitle(eventRequestDto.getTitle());
        return event;
    }

    public EventResponseDto toDto(Event event) {
        EventResponseDto eventResponseDto = new EventResponseDto();
        eventResponseDto.setId(event.getId());
        eventResponseDto.setTitle(event.getTitle());
        eventResponseDto.setDescription(event.getDescription());
        return eventResponseDto;
    }
}
