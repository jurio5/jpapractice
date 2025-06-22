package jpabook.jpashop.mapping.item;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("Album")
public class Album extends Item {
    private String artist;
}
