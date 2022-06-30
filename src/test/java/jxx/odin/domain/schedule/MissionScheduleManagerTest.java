package jxx.odin.domain.schedule;


import jxx.odin.domain.character.CharacterRepository;

import jxx.odin.domain.member.MemberRepository;

import jxx.odin.domain.mission.MissionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MissionScheduleManagerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    MissionScheduleManager missionScheduleManager;

    @BeforeEach
    void beforeEach() {
    }

    @DisplayName("미션 스케줄러를 통해 미션을 초기화 할 수 있습니다. 초기화된 미션의 Complete 필드는 false 입니다.")
    @Test
    void schedulerTest() {

    }
}