package ua.com.conductor.dao;

import java.util.List;
import ua.com.conductor.model.CinemaHall;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
