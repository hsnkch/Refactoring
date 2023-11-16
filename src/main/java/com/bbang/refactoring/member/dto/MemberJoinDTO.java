package com.bbang.refactoring.member.dto;

import lombok.Data;

@Data
public class MemberJoinDTO {
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberPwChk;
}
