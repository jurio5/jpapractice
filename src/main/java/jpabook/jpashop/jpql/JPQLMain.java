package jpabook.jpashop.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jpabook.jpashop.domain.Member;

import java.util.List;

public class JPQLMain {

    EntityManager em;

    public static void main(String[] args) {
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
