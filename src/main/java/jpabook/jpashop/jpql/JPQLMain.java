package jpabook.jpashop.jpql;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;

import java.util.List;

public class JPQLMain {

    EntityManager em;

    public static void main(String[] args) {
        List<Member> result = em.createQuery("select m from Member m where m.name like '%kim%'", Member.class)
                .getResultList();
    }
}
