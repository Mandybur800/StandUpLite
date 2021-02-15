package ua.com.conductor.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.conductor.model.Movie;
import ua.com.conductor.model.dto.MovieRequestDto;
import ua.com.conductor.model.dto.MovieResponseDto;
import ua.com.conductor.service.MovieService;
import ua.com.conductor.service.dtomappers.MovieMapper;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper mapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper mapper) {
        this.movieService = movieService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createMovie(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = mapper.toEntity(movieRequestDto);
        movieService.add(movie);
    }
}
