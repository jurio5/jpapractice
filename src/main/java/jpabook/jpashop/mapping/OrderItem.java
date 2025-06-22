package jpabook.jpashop.mapping;

import jakarta.persistence.*;
import jpabook.jpashop.mapping.item.Item;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private int price;

    private int quantity;
}
