package com.sg.superhero.dao;

import com.sg.superhero.entity.Hero;
import com.sg.superhero.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class LocationDaoDBImpl implements LocationDao{
    @Autowired
    JdbcTemplate jdbc;
    @Override
    public Location getLocationById(int id) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE id = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocation() {
        final String GET_ALL_LOCATIONS= "SELECT * FROM location";
        return jdbc.query(GET_ALL_LOCATIONS,new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location(name,address,description,latitude,longitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getAddress(),
                location.getDescription(),
                location.getLatitude(),
                location.getLongitute());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);

        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET name = ?, address = ?, description = ? latitude = ?" +
                " longitude = ?";
        jdbc.update(UPDATE_LOCATION,location.getName(),location.getAddress(),location.getDescription(),
                location.getLatitude(),location.getLongitute(),location.getId());

    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {

       final String DELETE_SIGHTING_LOCATION = "DELETE FROM sighting WHERE location = ?";
       jdbc.update(DELETE_SIGHTING_LOCATION,id);

       final String DELETE_LOCATION = "DELETE FROM location WHERE id = ?";
       jdbc.update(DELETE_LOCATION,id);
    }

    public static final class  LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location locationObj = new Location();
            locationObj.setId(rs.getInt("id"));
            locationObj.setName(rs.getString("name"));
            locationObj.setDescription(rs.getString("description"));
            locationObj.setAddress(rs.getString("address"));
            locationObj.setLatitude(rs.getString("latitude"));
            locationObj.setLongitute(rs.getString("longitude"));
            return locationObj;
        }

    }
}
