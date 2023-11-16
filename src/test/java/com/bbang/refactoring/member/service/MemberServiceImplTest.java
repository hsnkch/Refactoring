package com.bbang.refactoring.member.service;

import com.bbang.refactoring.member.dto.MemberJoinDTO;
import com.bbang.refactoring.member.dto.MemberUpdateDTO;
import com.bbang.refactoring.member.model.Member;
import com.bbang.refactoring.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MemberServiceImplTest {
    @Autowired
    MemberServiceImpl memberService;
    @Autowired
    MemberRepository memberRepository;
    private Member member;
    private String id = "id" + String.valueOf(Math.random());
    private String pw = "pw" + String.valueOf(Math.random());
    private String name = "name" + String.valueOf(Math.random());

    @BeforeEach
    void beforeEach() {
        member = Member.builder()
                .memberId(id)
                .memberPw(pw)
                .memberName(name)
                .build();
        memberService.signup(member);
    }

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    void signup() {
        // given
        MemberJoinDTO memberJoinDTO = new MemberJoinDTO();
        memberJoinDTO.setMemberId("testid");
        memberJoinDTO.setMemberName("testname");
        memberJoinDTO.setMemberPw("testpw");
        Member member = Member.builder()
                .memberId(memberJoinDTO.getMemberId())
                .memberPw(memberJoinDTO.getMemberPw())
                .memberName(memberJoinDTO.getMemberName())
                .build();

        // when
        memberService.signup(member);

        // then
        assertEquals(member.getMemberName(),"testname");

    }

    @Test
    @DisplayName("전체 조회")
    void getAllMembers() {
        //given

        // when
        List<Member> allMembers = memberService.getAllMembers();

        // then
        for (int i = 0; i < allMembers.size(); i++) {
            assertEquals(allMembers.get(i).getMemberId(), id);
        }
    }

    @Test
    @DisplayName("한명 조회")
    void getMemberByMemberId() {
        // given

        // when
        Member testId = memberService.getMemberByMemberId(id);

        // then
        assertEquals(testId.getMemberId(), id);

    }

    @Test
    @DisplayName("삭제")
    void deleteMemberByMemberId() {
        // given

        // when
        memberService.deleteMemberByMemberId(id);
        // then
        Member memberByMemberId = memberService.getMemberByMemberId(id);
        assertNull(memberByMemberId);
    }

    @Test
    @DisplayName("수정")
    void updateMember() {
        // given
        MemberUpdateDTO memberUpdateDTO = new MemberUpdateDTO();
        memberUpdateDTO.setMemberName("updateName");
        memberUpdateDTO.setMemberPw("updatePw");

        // when
        memberService.updateMember(id, memberUpdateDTO);

        // then
        assertEquals("updateName", memberService.getMemberByMemberId(id).getMemberName());

    }
}