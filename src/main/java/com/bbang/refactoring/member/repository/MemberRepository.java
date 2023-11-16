package com.bbang.refactoring.member.repository;

import com.bbang.refactoring.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    Member findMemberByMemberId(String memberId);
}
