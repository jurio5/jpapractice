package jpabook.jpashop.jpql;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NamedQuery(
        name = "Member.findByUsername", // 관례 상 엔티티 명.XXX 로 사용
        query = "select m from Member m where m.name = :name"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Builder(access = AccessLevel.PUBLIC)
    private Member(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() { // toString 만들 때 연관관계(Team)는 지워줘야 함 (무한 루프 고려)
        return "Member{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
