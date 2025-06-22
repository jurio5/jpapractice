package jpabook.jpashop.mapping.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("Movie")
@Getter
public class Movie extends Item {
    private String actor;
    private String director;
}
