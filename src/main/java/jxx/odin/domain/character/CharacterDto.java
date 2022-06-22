package jxx.odin.domain.character;


import jxx.odin.domain.mission.MissionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharacterDto {

    private List<MissionDto> missions = new ArrayList<>();


}
