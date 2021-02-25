package ua.com.conductor.dao;

import java.util.List;
import java.util.Optional;
import ua.com.conductor.model.Location;

public interface LocationDao {
    Location add(Location location);

    List<Location> getAll();

    Optional<Location> get(Long id);
}
