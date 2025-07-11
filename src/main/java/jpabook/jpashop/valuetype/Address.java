package jpabook.jpashop.valuetype;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public final class Address {
    private String city;
    @Column(name = "STREET")
    private String street;
    private String zipcode;

    @Builder(access = AccessLevel.PUBLIC, toBuilder = true)
    private Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public Address withCity(String city) {
        return new Address(city, this.street, this.zipcode);
    }
}
