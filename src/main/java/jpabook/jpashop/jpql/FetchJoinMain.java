package jpabook.jpashop.jpql;

import jakarta.persistence.EntityManager;

import java.util.List;

public class FetchJoinMain {

    EntityManager em;

    public static void main(String[] args) {
        Team teamA = new Team();
        teamA.setName("팀A");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("팀B");
        em.persist(teamB);

        Member member1 = new Member();
        member1.setTeam(teamA);
        em.persist(member1);

        Member member2 = new Member();
        member2.setTeam(teamB);
        em.persist(member2);

        em.flush();
        em.clear();

        String query = "select m from Member m where m = :member";

        Member findMember = em.createQuery(query, Member.class)
                .setParameter("member", member1)
                .getSingleResult();

        // 엔티티를 조건으로 걸어서 바인딩해도 SQL 에선 식별자로 변환하여 사용 (m = :member -> m.id = ?)
    }

    private static void distinct() {
        Team teamA = new Team();
        teamA.setName("팀A");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("팀B");
        em.persist(teamB);

        Member member1 = new Member();
        member1.setTeam(teamA);
        em.persist(member1);

        Member member2 = new Member();
        member2.setTeam(teamB);
        em.persist(member2);

        em.flush();
        em.clear();

        String query = "select distinct t from Team t join fetch t.members";

        List<Member> result = em.createQuery(query, Member.class).getResultList();

        for (Member member : result) {
            System.out.println("member.getName() + \",\" + member.getTeam() = " + member.getName() + "," + member.getTeam());
        }
    }

    private static void fetchJoin() {
        Team teamA = new Team();
        teamA.setName("팀A");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("팀B");
        em.persist(teamB);

        Member member1 = new Member();
        member1.setTeam(teamA);
        em.persist(member1);

        Member member2 = new Member();
        member2.setTeam(teamB);
        em.persist(member2);

        em.flush();
        em.clear();

        String query = "select m from Member m join fetch m.team";

        List<Member> result = em.createQuery(query, Member.class).getResultList();

        for (Member member : result) {
            System.out.println("member.getName() + \",\" + member.getTeam() = " + member.getName() + "," + member.getTeam());
        }
    }
}
