package jpabook.jpashop.relationship;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "username")
    private String name;

    //    @Column(name = "TEAM_ID")
//    private Long teamId;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩의 경우 프록시 객체를 생성
    @JoinColumn(name = "TEAM_ID") // ManyToOne 으로 연관관계 설정 시 자동으로 JoinColumn 에서 'TEAM_ID'를 생성해주지만, 명시
    private Team team;

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    @Builder
    public Member(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public void addTeam(Team team) {
        this.team = team;
    }
}
