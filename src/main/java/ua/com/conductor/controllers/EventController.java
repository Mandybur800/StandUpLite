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
import ua.com.conductor.model.Event;
import ua.com.conductor.model.dto.EventRequestDto;
import ua.com.conductor.model.dto.EventResponseDto;
import ua.com.conductor.service.EventService;
import ua.com.conductor.service.dtomappers.EventMapper;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final EventMapper mapper;

    @Autowired
    public EventController(EventService eventService, EventMapper mapper) {
        this.eventService = eventService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<EventResponseDto> getAllEvents() {
        return eventService.getAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createEvent(@RequestBody @Valid EventRequestDto eventRequestDto) {
        Event event = mapper.toEntity(eventRequestDto);
        eventService.add(event);
    }
}
