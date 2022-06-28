package jxx.odin.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    EntityManager entityManager;

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

    // 리파짓토리에서 예외 처리
    public Member findByEmail(String memberEmail) {
        Member member = null;
        try {
            String jpql = "select m from Member m where email = ?1";
            member = entityManager.createQuery(jpql, Member.class)
                         .setParameter(1, memberEmail)
                         .getSingleResult();

        } catch (NoResultException e) {
            log.info("이메일이 DB에 존재하지 않아 [{}] 발생", e.getClass());
        }

        return member;
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
