package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    // Basic attribute type should not be a container > 이 에러는 @Embeddable 어노테이션 써서 내장될 수 있도록 해줘야 에러 안 나는 것 같음
    // @Embedded : 내장 타입을 포함했다
    @Embedded
    private Address address;

    // order 테이블에 있는 member 필드에 의해서 매핑됨
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}


