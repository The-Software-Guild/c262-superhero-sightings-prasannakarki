package com.sg.superhero.dao;

import com.sg.superhero.entity.Location;
import com.sg.superhero.entity.Orginization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class OrginizationDaoDBImpl implements  OrginizationDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Orginization getOrginizationById(int id) {
        final String GET_ORGINZATION = "SELECT * FROM orginization WHERE id= ?";
        return jdbc.queryForObject(GET_ORGINZATION, new OrginizationMapper(),id);
    }

    @Override
    public List<Orginization> getAllOrginization() {
        final String GET_ALL_ORGANIZATION= "SELECT * FROM orginization";
        return jdbc.query(GET_ALL_ORGANIZATION,new OrginizationMapper());
    }

    @Override
    @Transactional
    public Orginization addOrginization(Orginization orginization) {
        final String INSERT_ORGINIZATION = "INSERT INTO orginization(name,address,description,contactInfo,allignment) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_ORGINIZATION,
                orginization.getName(),
                orginization.getAddress(),
                orginization.getDescription(),
                orginization.getContactInfo(),
                orginization.getAllignment());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        orginization.setId(newId);

        return orginization;
    }

    @Override
    public void updateOrginization(Orginization orginization) {
        final String UPDATE_LOCATION = "UPDATE orginization SET name= ?, address = ?, description = ?, contactInfo = ?," +
                " allignment = ? WHERE id = ?";
        jdbc.update(UPDATE_LOCATION,orginization.getName(),orginization.getAddress(),orginization.getDescription(),
                orginization.getContactInfo(),orginization.getAllignment(),orginization.getId());

    }

    @Override
    @Transactional
    public void deleteOrginizationById(int id) {
        final String DELETE_ORGINIZATION_FROM_MEMBER = "DELETE FROM member WHERE orginization = ? ";
        jdbc.update(DELETE_ORGINIZATION_FROM_MEMBER,id);
        final String DELETE_ORGINIZATION = "DELETE FROM orginization WHERE id = ?";
        jdbc.update(DELETE_ORGINIZATION,id);

    }

    public static final class OrginizationMapper implements RowMapper<Orginization> {
        @Override
        public Orginization mapRow(ResultSet rs, int index) throws SQLException {
            Orginization orginizationObj = new Orginization();
            orginizationObj.setId(rs.getInt("id"));
            orginizationObj.setName(rs.getString("name"));
            orginizationObj.setDescription(rs.getString("description"));
            orginizationObj.setAddress(rs.getString("address"));
            orginizationObj.setContactInfo(rs.getString("contactInfo"));
            orginizationObj.setAllignment(rs.getString("allignment"));
            return orginizationObj;
        }
    }
}