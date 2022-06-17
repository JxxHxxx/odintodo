package jxx.odin.domain.character;

import jxx.odin.domain.member.Member;
import jxx.odin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static jxx.odin.domain.member.MemberController.SESSION_MEMBER_ID;

/**
 * 커밋 금지, 개발중
 *
 */

@Slf4j
@Controller
@RequestMapping("/odin/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterRepository characterRepository;
    private final MemberRepository memberRepository;

    @GetMapping()
    public String form(Model model) {

        model.addAttribute("character", new Character());

        return "/character/addForm";
    }

    @PostMapping()
    public String addCharacter(@SessionAttribute(SESSION_MEMBER_ID) Member sessionMember,
            @ModelAttribute("character") Character character, Model model) {

        Member member = memberRepository.findById(sessionMember.getId());

        Character saveCharacter = characterRepository.save(character);
        saveCharacter.setMember(member);

        characterRepository.update(saveCharacter.getId(), saveCharacter);

        model.addAttribute("loginMember", member);

        return "/main/loginHome";
    }

    @GetMapping("/{characterId}")
    public String missions(@PathVariable(name = "characterId") Long id, Model model)  {

        Character character = characterRepository.findById(id);

        model.addAttribute("character", character);

        return "/character/character";
    }

    @PostMapping("/{characterId}")
    public String editMissions(@PathVariable(name = "characterId") Long id, @ModelAttribute("character") Character character) {

        characterRepository.updateCharacterMission(id, character);

        log.info("캐릭터의 미션 완료 여부를 수정합니다.");
        character.getMissions()
                .forEach(mission -> log.info("미션 수행 여부 [{}] [{}]", mission.getContent(), mission.getComplete()));

        return "redirect:/odin/character/{characterId}";
    }

}
