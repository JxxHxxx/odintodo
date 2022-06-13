package jxx.odin.domain.schedule;

import jxx.odin.domain.character.CharacterManager;
import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import jxx.odin.domain.mission.Mission;
import jxx.odin.domain.mission.MissionManager;
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

    private final MissionManager missionManager;
    private final CharacterManager characterManager;
    private final MemberRepository memberRepository;

    /**
     * initMission
     *
     * field 설명
     * cycle : 초기화할 미션의 사이클
     * ex) 일간 미션을 초기화하려면 DayConstant.DAY
     *     주간 미션을 초기화하려면 DayConstant.WEEKEND
     */
    public void initMission(Integer cycle) {
        List<Member> members = memberRepository.findAll();

        members.forEach(member -> characterManager.findAll(member)
                .forEach(character -> missionManager.findAll(character)
                        .stream().filter(mission -> cycleOf(mission).equals(cycle))
                        .forEach(mission -> mission.setComplete(false))));
    }

    private Integer cycleOf(Mission mission) {
        return mission.getContent().getCycle();
    }
}
