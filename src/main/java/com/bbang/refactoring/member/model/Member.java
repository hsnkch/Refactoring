package com.bbang.refactoring.member.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;
    @Column(unique = true)
    private String memberId;
    private String memberName;
    private String memberPw;
    @Builder    // 객체 생성
    public Member(String memberId, String memberName, String memberPw) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPw = memberPw;
    }

    // 수정 메서드
    public void update(String memberName, String memberPw) {
        this.memberName = memberName;
        this.memberPw = memberPw;
    }
}
