package com.sg.superhero.dao;



import com.sg.superhero.entity.Location;

import java.util.List;

public interface LocationDao {
    Location getLocationById(int id);

    List<Location> getAllLocation();

    Location addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocationById(int id);
}
