package ua.com.conductor.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.conductor.dao.LocationDao;
import ua.com.conductor.model.Location;
import ua.com.conductor.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationDao locationDao;

    @Autowired
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Location add(Location location) {
        return locationDao.add(location);
    }

    @Override
    public List<Location> getAll() {
        return locationDao.getAll();
    }

    @Override
    public Location get(Long id) {
        return locationDao.get(id).get();
    }
}
