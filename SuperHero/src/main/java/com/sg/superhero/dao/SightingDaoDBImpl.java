package com.sg.superhero.dao;

import com.sg.superhero.entity.Hero;
import com.sg.superhero.entity.Orginization;
import com.sg.superhero.entity.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
@Repository
public class SightingDaoDBImpl implements  SightingDao{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingById(int id) {
        final String GET_SIGHTING = "SELECT * FROM sighting where id = ?";
        return jdbc.queryForObject(GET_SIGHTING,new SightingMapper(),id);
    }

    @Override
    public List<Sighting> getAllSighting() {
        final String GET_ALL_SIGHTINGS = "SELECT * FROM sighting";
        return jdbc.query(GET_ALL_SIGHTINGS,new SightingMapper());
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sighting(hero,location,date) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getHero(),
                sighting.getLocation(),
                sighting.getDate());


        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setId(newId);

        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sighting SET hero= ?, location = ?, date = ? WHERE id = ?";
        jdbc.update(UPDATE_SIGHTING,sighting.getHero(),sighting.getLocation(),sighting.getDate(),
               sighting.getId());
    }

    @Override
    public void deleteSightingById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE id = ? ";
        jdbc.update(DELETE_SIGHTING,id);


    }

    @Override
    public Sighting getSightingForHero(Hero hero) {
        final String SELECT_HERO_FOR_SIGHTING = "SELECT h.* FROM hero h "
                + "JOIN sighting s ON h.id = s.id ";
        return jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING,new SightingMapper());
    }

    public static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sightingObj = new Sighting();
            sightingObj.setId(rs.getInt("id"));
            sightingObj.setHero(rs.getInt("hero"));
            sightingObj.setLocation(rs.getInt("location"));
            sightingObj.setDate(rs.getTimestamp("date").toLocalDateTime());

            return sightingObj;
        }
}
}
