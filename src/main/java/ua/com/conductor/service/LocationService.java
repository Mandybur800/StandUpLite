package ua.com.conductor.service;

import java.util.List;
import ua.com.conductor.model.Location;

public interface LocationService {
    Location add(Location location);

    List<Location> getAll();

    Location get(Long id);
}
