package jpabook.jpashop.mapping.item;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Book")
public class Book extends Item {
    private String author;
    private String isbn;
}
