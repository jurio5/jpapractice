package jpabook.relationship;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        Team team = new Team();
        Member addMember = Member.builder()
                .name("Tester1")
                .team(team)
                .build();

        team.addMember(addMember);

        List<Member> members = addMember.getTeam().getMembers();

        for (Member m : members) {
            System.out.println("m.getName() = " + m.getName());
        }
    }
}
