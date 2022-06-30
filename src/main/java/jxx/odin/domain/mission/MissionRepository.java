package jxx.odin.domain.mission;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class MissionRepository {

    private final EntityManager entityManager;

    public Mission save(Mission mission) {
        entityManager.persist(mission);
        return mission;
    }
    public Mission findById(Long missionId) {
        return entityManager.find(Mission.class, missionId);
    }

    public List<Mission> findAll() {
        String jpql = "select m from Mission m";
        TypedQuery<Mission> query = entityManager.createQuery(jpql, Mission.class);
        return query.getResultList();
    }

    public void update(Long missionId, Mission mission) {
        Mission findMission = findById(missionId);
        findMission.setComplete(mission.getComplete());
    }

}
