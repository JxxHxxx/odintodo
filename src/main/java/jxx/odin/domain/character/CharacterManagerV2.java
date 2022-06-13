package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * CharacterManagerV2
 * 멤버의 캐릭터 조회, 삭제 가능
 *
 * CharacterManagerV2는 CharacterManagerV1과 달리 MemberRepository에 의존하지 않습니다.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CharacterManagerV2 implements CharacterManager{

    @Override
    public void create(Member member, Character character) {
        member.getCharacters().add(character);
    }

    @Override
    public List<Character> findAll(Member member) {
        return member.getCharacters();
    }

    @Override
    public Character findByIndex(Member member, Integer index) {
        return member.getCharacters().get(index);
    }

    @Override
    public void delete(Member member, Character character){
        findAll(member).remove(character);
    }

}
