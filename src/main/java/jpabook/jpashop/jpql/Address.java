package jpabook.jpashop.jpql;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public final class Address {

    private String city;
    private String street;
    private String zipcode;

    @Builder(access = AccessLevel.PUBLIC)
    private Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
