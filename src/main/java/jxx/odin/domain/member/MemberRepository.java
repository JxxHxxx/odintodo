package jxx.odin.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager entityManager;

    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    public Member findById(Long memberId) {
        return entityManager.find(Member.class, memberId);
    }

    public Member findByName(String memberName) {
        String jpql = "select m from Member m where name = ?1";
        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        query.setParameter(1, memberName);

        return query.getSingleResult();
    }

    public Member findByEmail(String memberEmail) {
        String jpql = "select m from Member m where email = ?1";
        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        query.setParameter(1, memberEmail);

        return query.getSingleResult();
    }

    public List<Member> findAll() {
        String jpql = "select m from Member m";
        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        return query.getResultList();
    }

    public void update(Long memberId, Member member) {
        Member findMember = findById(memberId);

        findMember.setPassword(member.getPassword());
        findMember.setNickname(member.getNickname());
        findMember.setCharacters(member.getCharacters());
    }
}
