package jpabook.jpashop.cascade;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    // Hard Delete 방식, Review 가 있고, 그 Review 에 대해 좋아요 기능이 있다면, 리뷰가 삭제 시 그 리뷰의 좋아요도 함께 삭제되는 상황과 같을 때 사용
    // 그러나 단순하게 접근하는 것이 중요, Review 를 soft delete 한다고 가정할 때
    // 컬럼을 update 처리(변경)하고, 좋아요는 Delete(삭제)하는 것을 하나의 트랜잭션에 묶어서 처리하는 것 처럼
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
