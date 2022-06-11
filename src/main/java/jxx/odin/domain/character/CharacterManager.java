package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * CharacterManager
 * 멤버의 캐릭터 조회, 삭제 가능
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CharacterManager {

    private final MemberRepository memberRepository;

    public void create(Member member, Character character) {
        Member findMember = memberRepository.findByMember(member);

        findMember.getCharacters().add(character);
    }

    public List<Character> findAll(Member member) {
        Member findMember = memberRepository.findByMember(member);

        return findMember.getCharacters();
    }

    public Character findByIndex(Member member, Integer index) {
        Member findMember = memberRepository.findByMember(member);

        return findMember.getCharacters().get(index);
    }

    public void delete(Member member, Character character){
        findAll(member).remove(character);
    }



}
