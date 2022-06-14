package jxx.odin;


import jxx.odin.domain.character.Character;
import jxx.odin.domain.character.CharacterManager;
import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class InitTestData {

    private final MemberRepository memberRepository;
    private final CharacterManager characterManager;

    @PostConstruct
    private void postConstruct() {
        Member memberA = new Member("memberA");
        Member memberB = new Member("memberB");

        memberA.setPassword("1234");
        memberB.setPassword("1234");

        memberRepository.save(memberA);
        memberRepository.save(memberB);

        Character character1 = new Character("템포따라와");
        Character character2 = new Character("포기안한다");

        characterManager.create(memberA, character1);
        characterManager.create(memberA, character2);

        memberRepository.update(memberA);


    }
}
