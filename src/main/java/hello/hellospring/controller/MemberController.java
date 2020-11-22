package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired // 해당 어노테이션이 있을 경우 파라미터로 전달되는 memberService를 스프링 컨테이너에 싱글톤으로 관리되던 인스턴스와 연결시켜줌(한번만 생성할 수 있는것)
    public MemberController(MemberService memberService) {
        // 컨트롤러가 실행되면 스프링에서 인스턴스로 가지고 있던 MemberService 인스턴스를 컨트롤러에 넣어주는 거랑 동일한 상황 = Dipendency Injection
        // 의존관계가 실행 중에 동적으로 변하는 경우는 아예 없으므로 생성자 주입이 권장
        this.memberService = memberService;
    }

    /* Field DI // 권장하지 않음
    @Autowired private final MemberService memberService2;
    */
    /* Setter DI // 이것도 권장하지 않음 - setter가 void로 선언되어서 아무나 세팅이 가능해짐
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
