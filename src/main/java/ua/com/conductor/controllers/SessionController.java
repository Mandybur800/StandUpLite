package ua.com.conductor.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.conductor.model.Session;
import ua.com.conductor.model.dto.SessionRequestDto;
import ua.com.conductor.model.dto.SessionResponseDto;
import ua.com.conductor.service.SessionService;
import ua.com.conductor.service.dtomappers.SessionMapper;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;
    private final SessionMapper mapper;

    @Autowired
    public SessionController(SessionService sessionService,
                             SessionMapper mapper) {
        this.sessionService = sessionService;
        this.mapper = mapper;
    }

    @GetMapping("/available")
    public List<SessionResponseDto> getSessionsByDate(@RequestParam Long eventId,
                                                      @RequestParam
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                              LocalDate date) {
        return sessionService.findAvailableSessions(eventId, date)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createMovieSession(@RequestBody @Valid SessionRequestDto requestDto) {
        Session session = mapper.toEntity(requestDto);
        sessionService.add(session);
    }

    @PutMapping("/{id}")
    public void updateMovieSession(@PathVariable Long id,
                                   @RequestBody @Valid SessionRequestDto dto) {
        Session session = mapper.toEntity(dto);
        session.setId(id);
        sessionService.update(session);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieSession(@PathVariable Long id) {
        sessionService.delete(id);
    }
}
