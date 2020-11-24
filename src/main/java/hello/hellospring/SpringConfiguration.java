package hello.hellospring;

import hello.hellospring.Repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfiguration {
    //@Autowired private DataSource dataSource;
//    private DataSource dataSource;
//    @Autowired
//    public SpringConfiguration(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
    EntityManager em;
    @Autowired
    public SpringConfiguration(EntityManager em) {
        this.em = em;
    }

    // 원래는 아래처럼 받는게 스펙
    //@PersistenceContext
    //private EntityManager em;

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    // 설정파일로 Bean을 스프링에 등록했을 때 좋은 점?? - 아래 Repository의 수정이 필요할 때 주석과 같이 코드 한줄만 수정해서 바꿀 수 있음
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
