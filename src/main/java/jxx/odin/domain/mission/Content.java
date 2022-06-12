package jxx.odin.domain.mission;

import lombok.Getter;

/**
 * Mission 객체의 필드 인스턴스 중 immutable한 성격을 가지는 필드들을 Content type 으로 구분합니다.
 * Name - 미션 이름
 * Cycle - 미션 초기화 주기
 */

@Getter
public enum Content {

    UNDERGROUND_PRISON("지하 감옥", MissionCycle.WEEKEND),
    DWARF_SECRET_PASSAGE("난쟁이 비밀 통로", MissionCycle.DAY),
    VOID_RUINS("공허의 유적", MissionCycle.DAY),
    TREASURE_BOX("보물 상자", MissionCycle.ONLY_ONCE);

    private final String name;
    private final Integer cycle;


    Content(String name, Integer cycle) {
        this.name = name;
        this.cycle = cycle;
    }
}
