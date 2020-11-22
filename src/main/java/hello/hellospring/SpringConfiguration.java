package hello.hellospring;

import hello.hellospring.Repository.JdbcMemberRepository;
import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfiguration {
    //@Autowired private DataSource dataSource;
    private DataSource dataSource;
    @Autowired
    public SpringConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    // 설정파일로 Bean을 스프링에 등록했을 때 좋은 점?? - 아래 Repository의 수정이 필요할 때 주석과 같이 코드 한줄만 수정해서 바꿀 수 있음
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
