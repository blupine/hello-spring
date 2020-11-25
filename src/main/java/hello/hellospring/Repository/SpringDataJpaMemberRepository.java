package hello.hellospring.Repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring이 JpaRepository를 상속받는 아래 인터페이스의 구현체를 알아서 만든 다음에 bean에 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{ // Long은 Member의 key인 id필드의 타입

    @Override
    Optional<Member> findByName(String name);
}
