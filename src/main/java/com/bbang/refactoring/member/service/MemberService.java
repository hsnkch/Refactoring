package com.bbang.refactoring.member.service;

import com.bbang.refactoring.member.dto.MemberUpdateDTO;
import com.bbang.refactoring.member.model.Member;

import java.util.List;

public interface MemberService {
    void signup(Member member);

    List<Member> getAllMembers();

    Member getMemberByMemberId(String memberId);

    void deleteMemberByMemberId(String memberId);

    void updateMember(String memberId, MemberUpdateDTO memberUpdateDTO);
}
