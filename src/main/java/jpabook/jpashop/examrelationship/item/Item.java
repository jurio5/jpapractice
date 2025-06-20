package jpabook.jpashop.examrelationship.item;

import jakarta.persistence.*;
import jpabook.jpashop.examrelationship.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 서브타입 테이블 전략
@DiscriminatorColumn // 단일 테이블 전략 같은 경우 이 어노테이션이 없어도 자동으로 `DTYPE`을 생성, 항상 있는게 좋음
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
