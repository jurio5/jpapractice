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

        String sql = "update Member m set m.age = 20";
        // 이 때 flush() 호출 (DB 에는 age 컬럼이 20으로 변경되어 있음)
        int resultCount = em.createQuery(sql, Member.class)
                .executeUpdate();

        System.out.println("member1.getAge() = " + member1.getAge()); // 호출 시 age 는 0으로 출력
        // 이유는 쓰기 SQL 에서 DB 로 쿼리만 날렸을 뿐 영속성 컨텍스트 내부에 있는 age 값은 그대로임

        em.clear(); // 영속성 컨텍스트 초기화 후

        Member findMember = em.find(Member.class, member1.getId());
        System.out.println("findMember.getAge() = " + findMember.getAge()); // 다시 DB 에서 영속성 컨텍스트로 데이터를 가져온 뒤 조회 -> 20 정상 호출

        System.out.println("resultCount = " + resultCount);
    }

    private static void namedQuery() {
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

        List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("name", "회원1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }

    private static void pkKey() {
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
