package ua.com.conductor.service.dtomappers;

import org.springframework.stereotype.Component;
import ua.com.conductor.model.CinemaHall;
import ua.com.conductor.model.dto.CinemaHallRequestDto;
import ua.com.conductor.model.dto.CinemaHallResponseDto;

@Component
public class CinemaHallMapper {
    public CinemaHall toEntity(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHall;
    }

    public CinemaHallResponseDto toDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}
