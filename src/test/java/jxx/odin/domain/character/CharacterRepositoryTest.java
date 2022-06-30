package jxx.odin.domain.character;

import jxx.odin.domain.mission.Mission;
import jxx.odin.domain.mission.MissionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CharacterRepositoryTest {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    MissionRepository missionRepository;

    @Transactional
    @DisplayName("테스트")
    @Test
    void characterUpdate() {
        Character character = characterRepository.findById(3L);

        List<Mission> missions = character.getMissions();
        missions.get(0).setComplete(true);

        character.setMissions(missions);

        characterRepository.update(3L, character);
        Mission findMission = missionRepository.findById(6L);

        assertThat(findMission.getComplete()).isTrue();

    }
}