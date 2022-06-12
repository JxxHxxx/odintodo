package jxx.odin.domain.mission;


import jxx.odin.domain.character.Character;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * MissionManager
 * 미션 매니저에서는 미션을 생성, 조회, 삭제합니다.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionManager {

    public void create(Character character, Mission mission) {
        character.getMissions().add(mission);
    }

    public Mission find(Character character, Integer index) {
        return character.getMissions().get(index);
    }

    public List<Mission> findAll(Character character) {
        return character.getMissions();
    }

    public void delete(Character character, Mission mission) {
        character.getMissions().remove(mission);
    }
}
