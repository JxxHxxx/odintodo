package jxx.odin.domain.schedule;

import jxx.odin.domain.character.Character;
import jxx.odin.domain.character.CharacterManagerV1;
import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import jxx.odin.domain.mission.Mission;
import jxx.odin.domain.mission.MissionCycle;
import jxx.odin.domain.mission.MissionManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static jxx.odin.domain.mission.Content.DWARF_SECRET_PASSAGE;
import static jxx.odin.domain.mission.Content.UNDERGROUND_PRISON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MissionScheduleManagerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CharacterManagerV1 characterManager;

    @Autowired
    MissionManager missionManager;

    @Autowired
    MissionScheduleManager missionScheduleManager;

    @BeforeEach
    void beforeEach() {
        Member member = new Member("memberA");

        memberRepository.save(member);
        Member findMember = memberRepository.findByMember(member);

        Character character1 = new Character("포기안한다");
        Character character2 = new Character("명동역");

        characterManager.create(findMember, character1);
        characterManager.create(findMember, character2);

        Mission mission1 = new Mission(DWARF_SECRET_PASSAGE, true);
        Mission mission2 = new Mission(UNDERGROUND_PRISON, true);

        missionManager.create(character1, mission1);
        missionManager.create(character1, mission2);

        memberRepository.update(findMember);

    }

    @DisplayName("미션 스케줄러를 통해 미션을 초기화 할 수 있습니다. 초기화된 미션의 Complete 필드는 false 입니다.")
    @Test
    void schedulerTest() {
        Member member = memberRepository.findById(0L);
        Character character = characterManager.findByIndex(member, 0);


        //초기화 전 일간 미션 Complete 상태
        assertThat(missionManager.find(character, 0).getComplete()).isTrue();
        assertThat(missionManager.find(character, 1).getComplete()).isTrue();

        // 일간 미션 초기화 진행
        missionScheduleManager.initMission(MissionCycle.DAY);

        //초기화 후 일간 미션 Complete 상태
        assertThat(missionManager.find(character, 0).getComplete()).isFalse();
        assertThat(missionManager.find(character, 1).getComplete()).isTrue();

        //주간 미션 초기화 진행
        missionScheduleManager.initMission(MissionCycle.WEEKEND);

        assertThat(missionManager.find(character, 0).getComplete()).isFalse();
        assertThat(missionManager.find(character, 1).getComplete()).isFalse();
    }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }
}