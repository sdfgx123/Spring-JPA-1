package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    // 타입 반드시 string으로 > 이렇게 해야 밀리는 거 없음
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP
}
