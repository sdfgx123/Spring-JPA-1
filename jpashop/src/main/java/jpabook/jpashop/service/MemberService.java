package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// service 어노테이션 > 자동으로 스프링 빈의 대상이 됨
// transactional > 매서드들이 한 트랜잭션 안에 묶임 > readonly true : 성능 최적화 > 쓰기작업 발생하는 join 매서드에는 readonly true 옵션 제외
// requiredArgConstructor > 생성자에 주석 처리한 부분까지 커버해줌
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    // 생성자에서 repository 의존성 injection 해줌
    // 더 이상 바꿀 일 없으니 final로 고정
    private final MemberRepository memberRepository;

    // 스프링 최신버전에서는 생성자 하나만 있으면, autowired 어노테이션 없어도 알아서 의존성 injection 해줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증 비즈니스 로직
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는지 확인
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
