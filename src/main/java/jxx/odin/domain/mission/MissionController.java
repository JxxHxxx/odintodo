package jxx.odin.domain.mission;


import jxx.odin.domain.character.Character;
import jxx.odin.domain.character.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static jxx.odin.domain.mission.Content.*;

@Slf4j
@Controller
@RequestMapping("/odin/character")
@RequiredArgsConstructor
public class MissionController {

    private final MissionRepository missionRepository;
    private final CharacterRepository characterRepository;

    @ModelAttribute("contents")
    public List<Content> missions() {
        List<Content> contents = new ArrayList<>();
        contents.add(UNDERGROUND_PRISON);
        contents.add(DWARF_SECRET_PASSAGE);
        contents.add(VOID_RUINS);
        contents.add(TREASURE_BOX);

        return contents;
    }

    @GetMapping("/{characterId}/edit")
    public String addMission(@PathVariable("characterId") Long id, Model model) {

        Character character = characterRepository.findById(id);
        model.addAttribute("character", character);
        log.info("character [{}] 추가할 미션을 고르는 중입니다.", character.getName());

        model.addAttribute("mission", new Mission());

        return "/mission/addForm";
    }
    @PostMapping("/{characterId}/edit")
    public String addMission(@PathVariable("characterId") Long id, @ModelAttribute("mission") Mission mission) {
        Character character = characterRepository.findById(id);
        mission.setCharacter(character);
        Mission saveMission = missionRepository.save(mission);

        return "redirect:/odin/character/{characterId}";
    }
}
