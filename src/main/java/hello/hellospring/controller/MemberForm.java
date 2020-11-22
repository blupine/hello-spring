package hello.hellospring.controller;

public class MemberForm {
    private String name; // /members/createMemberForm.html의 name 속성 값이 매칭되어서 해당 인스턴스가 만들어지고 Controller에게 넘어옴

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
