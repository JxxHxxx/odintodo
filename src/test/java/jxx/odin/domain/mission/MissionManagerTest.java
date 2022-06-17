package jxx.odin.domain.mission;

import jxx.odin.domain.character.Character;

import jxx.odin.domain.character.CharacterRepository;
import jxx.odin.domain.member.Member;

import jxx.odin.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MissionManagerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    MissionRepository missionRepository;

    @BeforeEach
    void beforeEach() {
        Member member = new Member("memberA");

        memberRepository.save(member);

        Character character1 = new Character("포기안한다");
        Character character2 = new Character("명동역");
    }


    @DisplayName("미션 매니저를 이용해서 캐릭터에 미션을 넣을 수 있습니다.")
    @Test
    void create() {

        // 캐릭터 조회
        Member findMember = memberRepository.findById(0L);


        // 미션 생성
        Mission mission = new Mission(Content.TREASURE_BOX);
        // 캐릭터에 미션 생성


        // 캐릭터에 미션이 생성됐는지 확인

    }

    @DisplayName("미션 매니저로 캐릭터의 미션을 조회할 수 있습니다.")
    @Test
    void find() {


    }

    @DisplayName("미션 매니저로 캐릭터의 미션을 삭제할 수 있습니다.")
    @Test
    void delete() {
        // 캐릭터 조회



    }
}