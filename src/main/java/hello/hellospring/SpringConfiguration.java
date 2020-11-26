package hello.hellospring;

import hello.hellospring.Repository.*;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfiguration(MemberRepository memberRepository) {
        // 스프링에서 MemberRepository를 찾음, 근데 없음 -> 인터페이스로 구현된 SpringDataJpaMemberRepository의 구현체를 알아서 Injection 해줌
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
