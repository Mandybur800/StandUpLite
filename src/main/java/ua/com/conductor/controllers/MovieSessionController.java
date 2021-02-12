package ua.com.conductor.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.conductor.model.MovieSession;
import ua.com.conductor.model.dto.MovieSessionRequestDto;
import ua.com.conductor.model.dto.MovieSessionResponseDto;
import ua.com.conductor.service.MovieSessionService;
import ua.com.conductor.service.dtomappers.MovieSessionMapper;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper mapper;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper mapper) {
        this.movieSessionService = movieSessionService;
        this.mapper = mapper;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getSessionsByDate(@RequestParam Long movieId,
                           @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createMovieSession(@RequestBody MovieSessionRequestDto requestDto) {
        MovieSession movieSession = mapper.toEntity(requestDto);
        movieSessionService.add(movieSession);
    }

    @PutMapping
    public void updateMovieSession(@RequestBody MovieSessionRequestDto dto) {
        movieSessionService.update(mapper.toEntity(dto));
    }

    @DeleteMapping
    public void deleteMovieSession(@RequestBody MovieSessionRequestDto dto) {
        movieSessionService.delete(mapper.toEntity(dto));
    }
}
