package com.bbang.refactoring.member.service;

import com.bbang.refactoring.member.dto.MemberUpdateDTO;
import com.bbang.refactoring.member.model.Member;
import com.bbang.refactoring.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public void register(Member member) {
        memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> allMembers = memberRepository.findAll();
        return allMembers;
    }

    @Override
    public Member getMemberByMemberId(String memberId) {
        return memberRepository.findMemberByMemberId(memberId);
    }

    @Override
    public void removeMember(String memberId) {
        memberRepository.delete(getMemberByMemberId(memberId));
    }

    @Override
    public void modifyMember(String memberId, MemberUpdateDTO memberUpdateDTO) {
        Member memberByMemberId = memberRepository.findMemberByMemberId(memberId);

        if (memberByMemberId != null) {
            memberByMemberId.update(memberUpdateDTO.getMemberName(), memberUpdateDTO.getMemberPw());
            memberRepository.save(memberByMemberId);
        }
    }

}
