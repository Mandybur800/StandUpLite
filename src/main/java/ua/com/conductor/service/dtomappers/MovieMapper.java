package ua.com.conductor.service.dtomappers;

import org.springframework.stereotype.Component;
import ua.com.conductor.model.Movie;
import ua.com.conductor.model.dto.MovieRequestDto;
import ua.com.conductor.model.dto.MovieResponseDto;

@Component
public class MovieMapper {
    public Movie toEntity(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setDescription(movieRequestDto.getDescription());
        movie.setTitle(movieRequestDto.getTitle());
        return movie;
    }

    public MovieResponseDto toDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }
}
