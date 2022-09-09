package com.sg.superhero.dao;

import com.sg.superhero.entity.Member;

import java.util.List;

public interface MemberDao {
    Member getMemberById(int id);

    List<Member> getAllMember();

    Member addMember(Member member);

    void updateMember(Member member);

    void deleteMemberById(int id);
}
