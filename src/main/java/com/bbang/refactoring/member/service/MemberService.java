package com.bbang.refactoring.member.service;

import com.bbang.refactoring.member.dto.MemberUpdateDTO;
import com.bbang.refactoring.member.model.Member;

import java.util.List;

public interface MemberService {
    void register(Member member);

    List<Member> getAllMembers();

    Member getMemberByMemberId(String memberId);

    void removeMember(String memberId);

    void modifyMember(String memberId, MemberUpdateDTO memberUpdateDTO);
}
