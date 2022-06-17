package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import jxx.odin.domain.mission.Content;
import jxx.odin.domain.mission.Mission;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CharacterManagerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CharacterRepository characterRepository;

    @BeforeEach
    void beforeEach() {
        Member member = new Member("memberA");
        memberRepository.save(member);

        //캐릭터 생성

    }

    @Transactional
    @Rollback(value = false)
    @DisplayName("캐릭터 리파지토리 멤버 - 캐릭터 객체 양방향 연관관계 구현을 통해 DB층과 싱크를 맞춘다.")
    @Test
    void bidirectionalCharacterAndMember() {
        Character character = characterRepository.findById(1L);
        Member member = memberRepository.findById(1L);
        character.setMember(member);

        characterRepository.update(character.getId(), character);

        //반영 전 member.characters.size = 3
        assertThat(member.getCharacters().size()).isEqualTo(4);
    }

    @Transactional
    @DisplayName("캐릭터 리파지토리에서는 캐릭터를 삭제할 수 있어야 합니다.")
    @Test
    void checkThisMissions() {

        // 우선 연관관계를 해제하고

        // 이후 삭제해야 합니다.
    }

    @DisplayName("캐릭터매니저를 통해 멤버의 모든 캐릭터 조회가 가능합니다.")
    @Test
    void canViewCharacters() {

    }

    @DisplayName("캐릭터  통해 멤버가 가지고 있는 캐릭터를 삭제할 수 있습니다.")
    @Test
    void delete() {

    }
}






































