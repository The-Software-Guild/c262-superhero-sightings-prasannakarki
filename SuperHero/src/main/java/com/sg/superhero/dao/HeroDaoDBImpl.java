package com.sg.superhero.dao;

import com.sg.superhero.entity.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class HeroDaoDBImpl implements HeroDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero getHeroById(int id) {
        final String GET_HERO_BY_ID = "SELECT * FROM hero WHERE id = ? ";
        return jdbc.queryForObject(GET_HERO_BY_ID,new HeroMapper(),id);

    }

    @Override
    public List<Hero> getAllHeros() {
        final String GET_ALL_HEROS = "SELECT * FROM hero";
        return jdbc.query(GET_ALL_HEROS,new HeroMapper());
    }

    @Override
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO hero(heroName,heroDescription,super_power) VALUES(?,?,?)";

        jdbc.update(INSERT_HERO,
                hero.getName(),hero.getDescription(),hero.getPower());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setId(newId);
        return hero;

    }

    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE hero SET heroName = ?, heroDescription = ? ,super_power = ? " +
        "WHERE id = ?";
        jdbc.update(UPDATE_HERO,hero.getName(),hero.getDescription(),hero.getPower(),hero.getId());

    }

    @Override
    public void deleteHeroById(int id) {
        final String DELETE_HERO_FROM_SIGHTING = "DELETE FROM sighting WHERE hero = ? ";
        jdbc.update(DELETE_HERO_FROM_SIGHTING,id);
        final String DELETE_HERO_FROM_MEMBER = "DELETE FROM member WHERE hero = ? ";
        jdbc.update(DELETE_HERO_FROM_MEMBER,id);
        final String DELETE_HERO = "DELETE FROM hero WHERE id = ?";
        jdbc.update(DELETE_HERO,id);

    }

    public static final class  HeroMapper implements RowMapper<Hero>{
        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException{
            Hero heroObj = new Hero();
            heroObj.setId(rs.getInt("id"));
            heroObj.setName(rs.getString("heroName"));
            heroObj.setDescription(rs.getString("heroDescription"));
            heroObj.setPower(rs.getString("super_power"));
            return heroObj;
        }

    }
}
