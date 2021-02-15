package ua.com.conductor.dao;

import java.util.List;
import java.util.Optional;
import ua.com.conductor.model.CinemaHall;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    Optional<CinemaHall> get(Long id);
}
