package hello.hellospring.service;

import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service // 해당 Annotation이 없으면 스프링이 이게 서비스인지 알지 못함. 그래서 MemberController에서 Autowired를 해도 서비스인지 모르기 때문에 에러가 남
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { // DI : Dependency Injection (외부에서 만들어서 전달해줌)
        // 이것도 마찬가지로 스프링에서 인스턴스로 가지고 있던 MemberRepositroy를 넣어줌 (의존성 주입)
        this.memberRepository = memberRepository;
    }

    /*
     *  회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // Optional<Member> 반환됨
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
