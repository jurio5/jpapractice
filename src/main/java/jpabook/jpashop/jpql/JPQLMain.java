package jpabook.jpashop.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jpabook.jpashop.domain.Member;

import java.util.List;

public class JPQLMain {

    EntityManager em;

    public static void main(String[] args) {
        Member member1 = Member.builder()
                .name("member" + i)
                .build();
        em.persist(member1);

        // concat
//        String sql = "select concat('a', 'b') from Member m";
//        String sql = "select 'a' || 'b' from Member m"; // hibernate 에서 지원하는 concat 방식

        // substring
//        String sql = "select substring(m.name, 2, 3) from Member m";

        // locate
//        String sql = "select locate('de','abcdef') from Member m"; // Integer 타입으로 반환해야 함, SQL 은 1-based 방식이라 1부터 시작

        // size
//        String sql = "select size(t.members) from Team t";
        em.createQuery(sql, String.class);
    }

    private static void nullif() {
        Member member1 = Member.builder()
                .name("member" + i)
                .build();
        em.persist(member1);

        String sql = "select nullif(m.name, '관리자') as username from Member m";
        em.createQuery(sql, String.class)
                .getResultList();
    }

    private static void coalesces() {
        Member member1 = Member.builder()
                .name("member" + i)
                .build();
        em.persist(member1);

        String sql = "select coalesce(m.name, '이름 없는 회원') as username from Member m";
        em.createQuery(sql, String.class)
                .getResultList();
    }

    private static void defaultCase() {
        Member member1 = Member.builder()
                .name("member" + i)
                .build();
        em.persist(member1);

        String sql = "select " +
                        "case when m.age <= 10 then '학생요금' " +
                        "when m.age >= 60 then '경로요금' " +
                        "else '일반요금' " +
                        "end " +
                "from Member m";

        em.createQuery(sql, String.class)
                .getResultList();
    }

    private static void jpqlType() {
        Member member1 = Member.builder()
                .name("member" + i)
                .build();
        em.persist(member1);

        // 기존 타입에 ENUM 을 추가하려면 패키지를 포함하기에 불편
//        em.createQuery("select m.name, 'HELLO', TRUE from Member m where m.memberType = jpabook.jpashop.jpql.MemberType.USER", Member.class);
        // 이름 기반 파라미터 바인딩을 사용
        em.createQuery("select m.name, 'HELLO', TRUE from Member m where m.memberType = :memberType", Member.class)
                .setParameter("memberType", MemberType.ADMIN)
                .getResultList();
    }

    private static void join() {
        Team team1 = Team.builder()
                .name("teamA")
                .build();
        em.persist(team1);

        Member member1 = Member.builder()
                .name("member" + i)
                .build();
        em.persist(member1);

        // where t.name = :name 이름 기반 바인딩을 사용하거나 내부 조인 시 select 부분을 t 로 받아 team 을 사용
        List<Member> result = em.createQuery("select t from Member m join m.team t", Member.class);
    }

    private static void pagingAPI() {
        for (int i = 1; i <= 100; i++) {
            Member member1 = Member.builder()
                    .name("member" + i)
                    .build();
            em.persist(member1);
        }

        List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

        System.out.println("result.size() = " + result.size());
        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    private static void newOperation() {
        Member member1 = Member.builder()
                .name("member1")
                .build();
        em.persist(member1);

        List<MemberDTO> result = em.createQuery("select new jpabook.jpashop.jpql.MemberDTO(m.name, m.age) from Member m", MemberDTO.class)
                .getResultList();

        MemberDTO memberDTO = result.get(0);
        System.out.println("memberDTO = " + memberDTO.getUsername());
        System.out.println("memberDTO = " + memberDTO.getAge());
    }

    private static void Embedded() {
        Member member1 = Member.builder()
                .name("member1")
                .build();
        em.persist(member1);

        em.createQuery("select o.address from Order o", Address.class);
    }

    private static void objectArray() {
        Member member1 = Member.builder()
                .name("member1")
                .build();
        em.persist(member1);

        List<Object[]> resultList = em.createQuery("select m.name, m.age from Member m", Member.class)
                .getResultList();

        Object[] result = resultList.get(0);
        System.out.println("username = " + result[0]);
    }

    private static void namedParameterBinding() {
        Member member1 = Member.builder()
                .name("member1")
                .build();
        em.persist(member1);

        TypedQuery<jpabook.jpashop.jpql.Member> query = em.createQuery("select m from Member m where m.name = :name", jpabook.jpashop.jpql.Member.class);
        query.setParameter("name", "member1");
        jpabook.jpashop.jpql.Member result = query.getSingleResult();
        System.out.println("result.getName() = " + result.getName());
    }

    private static void singleResult() {
        Member member1 = Member.builder()
                .name("member1")
                .build();
        em.persist(member1);

        TypedQuery<jpabook.jpashop.jpql.Member> query = em.createQuery("select m from Member m", jpabook.jpashop.jpql.Member.class);

        jpabook.jpashop.jpql.Member result = query.getSingleResult(); // PK를 통해 하나의 결과를 가져올 때 사용 (where id = :id)
    }

    private static void queryAndTypeQuery() {
        Member member1 = Member.builder()
                .name("member1")
                .build();
        em.persist(member1);

        TypedQuery<jpabook.jpashop.jpql.Member> query1 = em.createQuery("select m from Member m", jpabook.jpashop.jpql.Member.class); // 타입이 명확한 경우들
        TypedQuery<String> query2 = em.createQuery("select m.name from Member m", String.class); // 타입이 명확한 경우들
        Query query3 = em.createQuery("select m.name, m.age from Member m"); // 타입이 명확하지 않을 때
    }

    private static void nativeSQL() {
        em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER")
                .getResultList();
    }

    private static void criteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
        List<Member> resultList = em.createQuery(cq).getResultList();
    }

    private static void jpqlFind() {
        List<Member> result = em.createQuery("select m from Member m where m.name like '%kim%'", Member.class)
                .getResultList();
    }
}
