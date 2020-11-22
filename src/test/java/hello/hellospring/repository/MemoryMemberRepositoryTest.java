package hello.hellospring.repository;

import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { // 테스트들 간의 의존 관계를 없애기 위해 데이터 모두 삭제
        repository.clearStore();
    }

    @Test
    public void save(){

        Member member = new Member();
        member.setName("strping");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional이 반환형이라 get으로 받아줘야 함(다른 방법이 더 좋음)
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member6 = new Member();
        member6.setName("spring2");
        repository.save(member6);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
