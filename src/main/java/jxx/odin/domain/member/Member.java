package jxx.odin.domain.member;


import jxx.odin.domain.character.Character;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jxx.odin.domain.member.MemberRepository.sequence;


@Getter
@Setter
public class Member {

    private Long id;

    private String name;
    private String password;

    private List<Character> characters;

    public Member() {
        this.characters = new ArrayList<>();
    }

    public Member(String name) {
        this.id = sequence;
        this.name = name;
        this.characters = new ArrayList<>();
    }
}
