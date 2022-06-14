package jxx.odin.domain.member;


import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;


/**
 * MemberRepository
 * 멤버 저장, 조회, 수정
 */

@Repository
public class MemberRepository {

    private final Map<Long, Member> store = new ConcurrentHashMap<>();

    protected static Long sequence = 0L;

    public void save(Member member) {
        store.put(sequence++, member);
    }

    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    public Member findByMember(Member member) {
        return store.values().stream()
                .filter(m -> m.equals(member))
                .findFirst().get();
    }
    public Member findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findFirst().get();
    }
    public List<Member> findAll() {
        return store.values().stream().toList();
    }

    public Member update(Member member) {
        member.setName(member.getName());
        member.setPassword(member.getPassword());

        member.setCharacters(member.getCharacters());

        return member;
    }

    public void delete(Member member) {
        store.remove(member.getId());

    }

    public void clear() {
        store.clear();
        sequence = 0L;
    }
}
