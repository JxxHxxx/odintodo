package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;

import java.util.List;

public interface CharacterManager {

    void create(Member member, Character character);

    List<Character> findAll(Member member);

    Character findByIndex(Member member, Integer index);

    void delete(Member member, Character character);

}
