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
import ua.com.conductor.model.StandUpSession;
import ua.com.conductor.model.dto.StandUpSessionRequestDto;
import ua.com.conductor.model.dto.StandUpSessionResponseDto;
import ua.com.conductor.service.StandUpSessionService;
import ua.com.conductor.service.dtomappers.StandUpSessionMapper;

@RestController
@RequestMapping("/sessions")
public class StandUpSessionController {
    private final StandUpSessionService standUpSessionService;
    private final StandUpSessionMapper mapper;

    @Autowired
    public StandUpSessionController(StandUpSessionService standUpSessionService,
                                    StandUpSessionMapper mapper) {
        this.standUpSessionService = standUpSessionService;
        this.mapper = mapper;
    }

    @GetMapping("/available")
    public List<StandUpSessionResponseDto> getSessionsByDate(@RequestParam Long eventId,
                                                             @RequestParam
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                              LocalDate date) {
        return standUpSessionService.findAvailableSessions(eventId, date)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createStandUpSession(@RequestBody @Valid StandUpSessionRequestDto requestDto) {
        StandUpSession standUpSession = mapper.toEntity(requestDto);
        standUpSessionService.add(standUpSession);
    }

    @PutMapping("/{id}")
    public void updateStandUpSession(@PathVariable Long id,
                                   @RequestBody @Valid StandUpSessionRequestDto dto) {
        StandUpSession standUpSession = mapper.toEntity(dto);
        standUpSession.setId(id);
        standUpSessionService.update(standUpSession);
    }

    @DeleteMapping("/{id}")
    public void deleteStandUpSession(@PathVariable Long id) {
        standUpSessionService.delete(id);
    }
}
