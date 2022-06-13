package jxx.odin.domain.mission;

import jxx.odin.domain.character.Character;
import jxx.odin.domain.character.CharacterManagerV1;
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
    CharacterManagerV1 characterManager;

    @Autowired
    MissionManager missionManager;

    @BeforeEach
    void beforeEach() {
        Member member = new Member("memberA");

        memberRepository.save(member);
        Member findMember = memberRepository.findByMember(member);

        Character character1 = new Character("포기안한다");
        Character character2 = new Character("명동역");

        characterManager.create(findMember, character1);
        characterManager.create(findMember, character2);

        memberRepository.update(findMember);
    }


    @DisplayName("미션 매니저를 이용해서 캐릭터에 미션을 넣을 수 있습니다.")
    @Test
    void create() {

        // 캐릭터 조회
        Member findMember = memberRepository.findById(0L);
        Character character = characterManager.findByIndex(findMember, 0);

        // 미션 생성
        Mission mission = new Mission(Content.TREASURE_BOX);
        // 캐릭터에 미션 생성
        missionManager.create(character, mission);

        // 캐릭터에 미션이 생성됐는지 확인
        assertThat(character.findMission(0).getContent()).isEqualTo(Content.TREASURE_BOX);

        // 저장이 제대로 반영 됐는지 확인
        Member updateMember = memberRepository.update(findMember);
        Character updateCharacter = characterManager.findByIndex(updateMember, 0);
        assertThat(updateCharacter.missionsSize()).isEqualTo(1);

    }

    @DisplayName("미션 매니저로 캐릭터의 미션을 조회할 수 있습니다.")
    @Test
    void find() {
        Member findMember = memberRepository.findById(0L);
        Character character = characterManager.findByIndex(findMember, 0);

        // 미션 생성
        Mission mission1 = new Mission(Content.TREASURE_BOX);
        Mission mission2 = new Mission(Content.DWARF_SECRET_PASSAGE);
        // 캐릭터에 미션 생성
        missionManager.create(character, mission1);
        missionManager.create(character, mission2);

        Assertions.assertThat(missionManager.find(character, 0).getContent()).isEqualTo(Content.TREASURE_BOX);

    }

    @DisplayName("미션 매니저로 캐릭터의 미션을 삭제할 수 있습니다.")
    @Test
    void delete() {
        // 캐릭터 조회
        Member findMember = memberRepository.findById(0L);
        Character character = characterManager.findByIndex(findMember, 0);

        // 미션 생성
        Mission mission = new Mission(Content.TREASURE_BOX);
        // 캐릭터에 미션 생성
        missionManager.create(character, mission);
        assertThat(missionManager.findAll(character).size()).isEqualTo(1);
        // 캐릭터가 가진 미션 삭제
        missionManager.delete(character, mission);
        assertThat(missionManager.findAll(character).size()).isEqualTo(0);


    }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }
}