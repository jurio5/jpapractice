package jpabook.relationship;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        Team team = new Team();
        Member addMember = Member.builder()
                .name("Tester1")
                .team(team)
                .build();

        team.addMember(addMember); // 지금은 JPA 에서 관리하는 엔티티 객체가 아니기에 null 값이 출력, 그러나 트랜잭션 내부에서 JPA 가 관리 시 제대로 PK 적용

        List<Member> members = addMember.getTeam().getMembers();

        for (Member m : members) {
            System.out.println("m.getId() = " + m.getId());
            System.out.println("m.getTeam().getId() = " + m.getTeam().getId());
        }
    }
}
