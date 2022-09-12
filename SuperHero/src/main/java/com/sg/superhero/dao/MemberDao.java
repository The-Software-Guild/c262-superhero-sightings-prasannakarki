package com.sg.superhero.dao;

import com.sg.superhero.entity.Member;

import java.util.List;

public interface MemberDao {

    List<Member> getAllMember();

    Member addMember(Member member);

    void deleteMemberById(Member member, int id);
}
