package com.bbang.refactoring.member.controller;

import com.bbang.refactoring.member.dto.MemberJoinDTO;
import com.bbang.refactoring.member.model.Member;
import com.bbang.refactoring.member.repository.MemberRepository;
import com.bbang.refactoring.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceImpl memberService;
    private final MemberRepository memberRepository;
    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "/member/signup";
    }

    @PostMapping("/member/signup")
    public ResponseEntity<?> join(@ModelAttribute MemberJoinDTO memberJoinDTO) {
        String memberId = memberJoinDTO.getMemberId();
        String memberName = memberJoinDTO.getMemberName();
        String memberPw = memberJoinDTO.getMemberPw();
        String memberPwChk = memberJoinDTO.getMemberPwChk();
        if (memberPw.equals(memberPwChk)) {
            Member newMember = Member.builder()
                    .memberId(memberId)
                    .memberPw(memberPw)
                    .memberName(memberName)
                    .build();
            memberService.signup(newMember);
        }
        return ResponseEntity.ok().body("ok");
    }

}
