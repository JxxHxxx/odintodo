package jxx.odin.domain.mission;

import lombok.Getter;

import static jxx.odin.domain.mission.MissionCycle.*;

/**
 * Mission 객체의 필드 인스턴스 중 immutable한 성격을 가지는 필드들을 Content type 으로 구분합니다.
 * Name - 미션 이름
 * Cycle - 미션 초기화 주기
 */

@Getter
public enum Content {

    UNDERGROUND_PRISON("지하 감옥", WEEKLY),
    DWARF_SECRET_PASSAGE("난쟁이 비밀 통로", DAILY),
    VOID_RUINS("공허의 유적", DAILY),
    TREASURE_BOX("보물 상자", ONE_TIME);

    private final String name;
    private final MissionCycle cycle;

    Content(String name, MissionCycle cycle) {
        this.name = name;
        this.cycle = cycle;
    }
}
