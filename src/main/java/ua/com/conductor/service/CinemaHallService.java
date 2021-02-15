package ua.com.conductor.service;

import java.util.List;
import ua.com.conductor.model.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall get(Long id);
}
