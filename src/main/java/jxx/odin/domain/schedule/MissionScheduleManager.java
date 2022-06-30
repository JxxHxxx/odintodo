package jxx.odin.domain.schedule;


import jxx.odin.domain.mission.Mission;
import jxx.odin.domain.mission.MissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * MissionScheduler
 * initMission 메소드를 통해 미션 초기화 작업을 수행합니다.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionScheduleManager {


    private final MissionRepository missionRepository;

    /**
     * initMission
     *
     * field 설명
     * cycle : 초기화할 미션의 사이클
     * ex) 일간 미션을 초기화하려면 DayConstant.DAY
     *     주간 미션을 초기화하려면 DayConstant.WEEKEND
     */

    // JPA
    public void initMission(Integer cycle) {
        List<Mission> missions = missionRepository.findAll();

        missions.stream()
                .filter(mission -> cycleOf(mission).equals(cycle))
                .forEach(mission -> mission.setComplete(false));

        missions.forEach(mission -> missionRepository.update(mission.getId(), mission));
    }

    private Integer cycleOf(Mission mission) {
        return mission.getContent().getCycle();
    }
}
