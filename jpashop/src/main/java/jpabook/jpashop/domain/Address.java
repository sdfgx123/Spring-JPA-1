package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

// Embeddable : 내장될 수 있다
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
