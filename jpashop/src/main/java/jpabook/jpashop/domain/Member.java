package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    // JsonIgnore : 회원 조회시, order 정보들 노출하지 않아도 될 정보까지 뿌려주기 때문에, 이 정보가 노출되는 것을 막기 위해 사용하는 어노테이션
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}


