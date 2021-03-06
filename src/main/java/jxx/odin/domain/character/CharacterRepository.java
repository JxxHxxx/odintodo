package jxx.odin.domain.character;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class CharacterRepository {

    private final EntityManager entityManager;

    public Character save(Character character) {
        entityManager.persist(character);
        return character;
    }
    public Character findById(Long characterId) {
        return entityManager.find(Character.class, characterId);
    }

    //리팩토링 고려
    public void update(Long characterId, Character character) {
        Character findCharacter = findById(characterId);

        findCharacter.setMissions(character.getMissions());
        findCharacter.setName(character.getName());
        findCharacter.setMember(character.getMember());
    }

    public void updateCharacterMission(Long characterId, CharacterDto character) {
        Character findCharacter = findById(characterId);

        Queue<Boolean> completeQueue = makeMissionCompletesOf(character);

        findCharacter.getMissions()
                .forEach(mission -> mission.setComplete(completeQueue.poll()));
    }

    // Request 로 받은 complete value 를 담는 Queue
    private Queue<Boolean> makeMissionCompletesOf(CharacterDto character) {
        Queue<Boolean> queue = new LinkedList<>();

        character.getMissions()
                .forEach(missionDto -> queue.add(missionDto.getComplete()));

        return queue;
    }

}
