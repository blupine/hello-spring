package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 해당 Entity 관리
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 생성하도록 -> IDENTITY
    private Long id;

//    @Column(name = 'username') // DB의 컬럼 명이 필드 명과 다를 때 매핑 방
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
