package jpabook.jpashop.exam.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}
