package jpabook.jpashop.valuetype;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String city;
    @Column(name = "STREET")
    private String street;
    private String zipcode;
}
