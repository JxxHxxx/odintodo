package jxx.odin.domain.mission;

import jxx.odin.domain.character.Character;
import jxx.odin.domain.character.CharacterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
class MissionRepositoryTest {

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    CharacterRepository characterRepository;

    @Transactional
    //@Rollback(value = false)
    @DisplayName("미션 리파지토리 캐릭터 - 미션(주인) 순수 객체 양방향 연관관계를 구현한다.")
    @Test
    void bidirectionalMissionAndCharacter() {
        Mission mission = new Mission(Content.TREASURE_BOX);
        missionRepository.save(mission);

        Mission findMission = missionRepository.findById(4L);
        // character name = 'spring'
        Character character = characterRepository.findById(2L);

        findMission.setCharacter(character);
        missionRepository.update(findMission.getId(), findMission);

        Assertions.assertThat(character.getMissions().size()).isEqualTo(1);
    }


    @DisplayName("체크 박스가 반영되야 합니다.")
    @Test
    void checkTest() {
    }


    @DisplayName("미션 리파짓은 모든 미션을 볼 수 있습니다.")
    @Test
    void findAll() {
    }
}