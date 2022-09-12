package com.sg.superhero.dao;

import com.sg.superhero.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
public class MemberDBImpl implements MemberDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Member> getAllMember() {
        final String GET_MEMBER_BY_ID = "SELECT * FROM member";
        return jdbc.query(GET_MEMBER_BY_ID,new MemberMapper());
    }

    @Override
    public Member addMember(Member member) {
       final String ADD_MEMBER = "INSERT INTO member(hero,orginization) VALUES(?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(ADD_MEMBER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, member.getHeroId());
            statement.setInt(2, member.getOrginizationId());

            return statement;
        }, keyHolder);

        return member;

    }



    @Override
    public void deleteMemberById(Member member,int id) {
        final String DELETE_MEMBER = "DELETE FROM member WHERE hero = ? , AND orginization = ? ";
        jdbc.update(DELETE_MEMBER,member.getHeroId(),member.getOrginizationId());
    }
    private static final class MemberMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int index) throws SQLException {
            Member member = new Member();
            member.setHeroId(rs.getInt("hero"));
            member.setOrginizationId(rs.getInt("orginization"));

            return member;
        }
    }
}
