package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

   private final MemberRepository memberRepository;


    //회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId(); // 값이 항상 있다.
    }


    // 회원 전체 조회
    @Transactional(readOnly = true) //읽기 전용 같은 ?? JPA처럼 최적화 해줌
    public List<Member> memberAll() {
        return memberRepository.findAll();
    }

    //하나만 조회
    @Transactional(readOnly = true)
    public Member findOnd(Long memberId) {
        return memberRepository.find(memberId);
    }

    // 겹치는 이름이 있는지 확인하는 로직
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        //Exception  예외를 터트릴 것
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
