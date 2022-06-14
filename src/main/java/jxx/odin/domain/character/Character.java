package jxx.odin.domain.character;

import jxx.odin.domain.mission.Content;
import jxx.odin.domain.mission.Mission;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Character {

    private String name;

    private List<Mission> missions;

    public Character() {
        this.missions = new ArrayList<>();
    }
    public Character(String name) {
        this.name = name;
        this.missions = new ArrayList<>();
    }

    public Integer missionsSize() {
        return this.missions.size();
    }

    public Mission findMission(Integer index) {
        return this.missions.get(index);
    }
}
