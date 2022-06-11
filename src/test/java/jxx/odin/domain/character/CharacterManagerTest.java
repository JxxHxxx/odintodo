package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CharacterManagerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CharacterManager characterManager;

    @BeforeEach
    void beforeEach() {
        Member member = new Member("memberA");
        memberRepository.save(member);

        //캐릭터 생성

    }

    @DisplayName("캐릭터매니저를 통해 멤버의 캐릭터 생성할 수 있습니다. 생성된 캐릭터는 캐릭터 조회를 통해 확인할 수 있습니다.")
    @Test
    void create() {
        Member findMember = memberRepository.findById(0L);

        Character character = new Character("템포따라와");
        characterManager.create(findMember, character);

        List<Character> characters = characterManager.findAll(findMember);

        assertThat(characters).contains(character);

    }

    @DisplayName("캐릭터 매니저는 인덱스를 통해 캐릭터 조회가 가능합니다.")
    @Test
    void findByIndex() {
        Member findMember = memberRepository.findById(0L);
        Character character1 = new Character("템포따라와");
        Character character2 = new Character("이건힐입니다만");

        characterManager.create(findMember, character1);
        characterManager.create(findMember, character2);

        Character findCharacterByIndex = characterManager.findByIndex(findMember, 0);

        Assertions.assertThat(findCharacterByIndex.getName()).isEqualTo("템포따라와");


    }

    @DisplayName("캐릭터매니저를 통해 멤버의 모든 캐릭터 조회가 가능합니다.")
    @Test
    void canViewCharacters() {

        Member findMember = memberRepository.findById(0L);
        Character character1 = new Character("템포따라와");
        Character character2 = new Character("이건힐입니다만");

        characterManager.create(findMember, character1);
        characterManager.create(findMember, character2);

        Member updateMember = memberRepository.update(findMember);

        List<Character> characters = characterManager.findAll(updateMember);

        assertThat(characters).contains(character1);
    }

    @DisplayName("캐릭터 매니저를 통해 멤버가 가지고 있는 캐릭터를 삭제할 수 있습니다.")
    @Test
    void delete() {
        Member findMember = memberRepository.findById(0L);
        Character character1 = new Character("템포따라와");
        Character character2 = new Character("이건힐입니다만");

        characterManager.create(findMember, character1);
        characterManager.create(findMember, character2);

        int characterSizeBefore = characterManager.findAll(findMember).size();

        assertThat(characterSizeBefore).isEqualTo(2);

        // 템포따라와 삭제
        characterManager.delete(findMember, character1);

        int characterSizeAfter = characterManager.findAll(findMember).size();

        assertThat(characterSizeAfter).isEqualTo(1);

        assertThat(characterManager.findByIndex(findMember, 0).getName()).isEqualTo("이건힐입니다만");
    }

    @AfterEach()
    void afterEach() {
        memberRepository.clear();
    }
}






































